/*
 * Copyright (C) 2013 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.pixtime.google.auth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.gson.Gson;
import com.pixtime.exceptions.PixTimeException;
import com.pixtime.persistence.UserDao;
import com.pixtime.utils.PixtimeUtils;
import com.pixtime.vo.Movie;
import com.pixtime.vo.PixtimeUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This servlet manages the OAuth 2.0 dance
 * 
 * @author Jenny Murphy - http://google.com/+JennyMurphy
 */
public class AuthServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(AuthServlet.class
			.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		// If something went wrong, log the error message.
		if (req.getParameter("error") != null) {

			LOG.severe("Something went wrong during auth: "
					+ req.getParameter("error"));
			res.setContentType("text/plain");
			res.getWriter()
					.write("Something went wrong during auth. Please check your log for details");
			return;
		}

		// If we have a code, finish the OAuth 2.0 dance
		if (req.getParameter("code") != null) {
			LOG.info("Got a code. Attempting to exchange for access token.");

			AuthorizationCodeFlow flow = AuthUtil.newAuthorizationCodeFlow();
			TokenResponse tokenResponse = flow
					.newTokenRequest(req.getParameter("code"))
					.setRedirectUri(WebUtil.buildUrl(req, "/oauth2callback"))
					.execute();

			// Extract the Google User ID from the ID token in the auth response
			String userId = ((GoogleTokenResponse) tokenResponse)
					.parseIdToken().getPayload().getUserId();

			URL url = new URL(
					"https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="
							+ tokenResponse.getAccessToken());

			BufferedReader rd = new BufferedReader((new InputStreamReader(
					url.openStream())));

			StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			System.out.println("Result" + result.toString());

			LOG.info("Code exchange worked. User " + userId + " logged in.");

			// Set it into the session
			AuthUtil.setUserId(req, userId);

			req.setAttribute("token", tokenResponse.getAccessToken());

			Gson gson = new Gson();
			PixtimeUser user = gson.fromJson(result.toString(),
					PixtimeUser.class);

			user.setAccessToken(tokenResponse.getAccessToken());
			user.setRefreshToken(tokenResponse.getRefreshToken());
			user.setChannel("Google");

			user.setCurrentTimeStamp(PixtimeUtils.getCurrentTimeStamp());
			user.setUserName("NA");
			// Redirect back to index

			UserDao userDao = new UserDao();
			try {
				userDao.insertUser(user);
			} catch (PixTimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			res.sendRedirect(WebUtil.buildUrl(req, "/reviewhome.html"));
			return;
		}

		// Else, we have a new flow. Initiate a new flow.
		LOG.info("No auth context found. Kicking off a new auth flow.");

		AuthorizationCodeFlow flow = AuthUtil.newAuthorizationCodeFlow();
		GenericUrl url = flow.newAuthorizationUrl().setRedirectUri(
				WebUtil.buildUrl(req, "/oauth2callback"));
		url.set("approval_prompt", "force");
		System.out.println("URL : " + url.build());
		res.sendRedirect(url.build());
	}

}
