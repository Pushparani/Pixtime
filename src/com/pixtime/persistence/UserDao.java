package com.pixtime.persistence;

import java.util.logging.Logger;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.pixtime.entities.UserEO;
import com.pixtime.exceptions.PixTimeException;
import com.pixtime.vo.PixtimeUser;

public class UserDao {

	private static final Logger LOG = Logger.getLogger(UserDao.class
			.getSimpleName());

	static {

		System.out.println("Registerting UserEO");
		ObjectifyService.register(UserEO.class);

	}

	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	public void insertUser(PixtimeUser user) throws PixTimeException {

		try {
			Objectify ofy = ObjectifyService.begin();

			UserEO userEo = new UserEO(user.getId(), user.getEmail(),
					user.getVerified_email(), user.getName(),
					user.getGiven_name(), user.getFamily_name(),
					user.getLink(), user.getPicture(), user.getGender(),
					user.getLocale(), user.getAccessToken(),
					user.getRefreshToken(), user.getChannel(),
					user.getCurrentTimeStamp(),user.getUserName());

			ofy.put(userEo);

		} catch (Exception e) {

			throw new PixTimeException(
					"Exception Occured while inserting User: " + e.toString());
		}

	}

}
