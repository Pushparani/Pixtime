package com.pixtime.fb.auth;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pixtime.exceptions.PixTimeException;
import com.pixtime.persistence.UserDao;
import com.pixtime.utils.PixtimeUtils;
import com.pixtime.vo.PixtimeUser;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import facebook4j.auth.AccessToken;

public class CallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 6305643034487441839L;

	private static final Logger LOG = Logger.getLogger(CallbackServlet.class
			.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Facebook facebook = (Facebook) request.getSession().getAttribute(
					"facebook");

			
			String oauthCode = request.getParameter("code");

			AccessToken access = facebook.getOAuthAccessToken(oauthCode);
			System.out.println(access.getToken());

			User user = facebook.getMe();

			System.out.println("App: " + facebook.getOAuthAppAccessToken());

			PixtimeUser pixtimeUser = new PixtimeUser();
			pixtimeUser.setId(user.getId());
			pixtimeUser.setName(user.getFirstName() + user.getLastName());
			pixtimeUser.setGiven_name(user.getFirstName());
			pixtimeUser.setFamily_name(user.getLastName());
			pixtimeUser.setEmail(user.getEmail());
			pixtimeUser.setUserName(user.getUsername());
			pixtimeUser.setChannel("Facebook");
			pixtimeUser.setGender(user.getGender());
			pixtimeUser.setLink(user.getLink().getPath());
			pixtimeUser.setLocale(user.getLocale().getCountry());
			pixtimeUser.setPicture(facebook.getPictureURL(user.getId())
					.getPath());
			pixtimeUser.setAccessToken(access.getToken());
			pixtimeUser.setRefreshToken("NA");
			pixtimeUser.setCurrentTimeStamp(PixtimeUtils.getCurrentTimeStamp());

			UserDao userDao = new UserDao();
			try {
				userDao.insertUser(pixtimeUser);
			} catch (PixTimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOG.info("Exception Occured while inserting facebook user Details"+e.toString());
			}

		} catch (FacebookException e) {
			throw new ServletException(e);
		}
		response.sendRedirect(request.getContextPath() + "/reviewhome.html");
	}
}
