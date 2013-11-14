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

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOutServlet extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(SignOutServlet.class
			.getSimpleName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// It'd be nice to do some XSRF validation here
		// AuthUtil.clearUserId(req);
		// System.out.println("Signed out");
		// resp.getWriter().write("You have been signed out.");
		//

		LOG.info("" + AuthUtil.getUserId(req));
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		LOG.info("Signing Out");
		req.getSession().invalidate();

		resp.sendRedirect("/index.html");
		// If you are logged into other services, clear their sessions here :)
	}
}
