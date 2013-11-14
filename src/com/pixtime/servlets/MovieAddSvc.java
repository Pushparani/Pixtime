package com.pixtime.servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.pixtime.persistence.MovieDao;
import com.pixtime.utils.PixTimeConstants;
import com.pixtime.vo.Movie;

/**
 * We want all get operations related to movie to be handled by this servlet
 * 
 */

public class MovieAddSvc extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(MovieAddSvc.class
			.getSimpleName());

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String pixtimeStatusDesc = "Movie Added";
		String pixtimeStatus = "SUCCESS";
		Gson gson = new Gson();

		try {

			Reader reader = new InputStreamReader(req.getInputStream());

			Movie movie = gson.fromJson(reader, Movie.class);
			System.out.println(movie.getDescription());

			MovieDao mvDao = new MovieDao();
			mvDao.insertMovies(movie);
			resp.setStatus(201);

		} catch (Exception e) {
			pixtimeStatusDesc = "internal Error Occured. Please check with us Later";
			pixtimeStatus = "FAILED";
			resp.setStatus(500);
			LOG.info("Movie Add Exception Occured" + e.toString());

		}

		resp.setContentType(PixTimeConstants.CONTENT_TYPE);
		resp.addHeader(PixTimeConstants.PIXTIME_STATUS, pixtimeStatus);
		resp.addHeader(PixTimeConstants.PIXTIME_DESC, pixtimeStatusDesc);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Gson gson = new Gson();
		List<Movie> movieArr = null;
		String responsePayload = null;
		String pixtimeStatusDesc = "Retrieved Movie List Successfully";
		String pixtimeStatus = "SUCCESS";
		MovieDao mvDao = new MovieDao();
		try {
			String reqPath = req.getRequestURI().trim();

			System.out.println("REQ:" + reqPath);

			String[] pathParts = reqPath.split("/");

			System.out.println(pathParts.length);
			System.out.println(pathParts[0]);
			System.out.println(pathParts[1]);
			System.out.println(pathParts[2]);

			// if we get a "movies" in the url path then it is considered as a
			// request to retrieve a list of movies
			// else it is a request to retrieve a single movie based on the uuid
			// in the url

			if (reqPath.equalsIgnoreCase("/pixtime/movies")) {

				movieArr = mvDao.getMovies();
				responsePayload = gson.toJson(movieArr);
				resp.setStatus(200);
			} else {

				String movieId = pathParts[3].trim();

				LOG.info("Movie Id :" + movieId);
				if (movieId != null && movieId.length() > 0) {
					System.out.println("Movie Id :" + movieId);

					Movie movie = mvDao.findMovieById(movieId.trim());
					if (movie != null) {
						responsePayload = gson.toJson(movie);
						resp.setStatus(200);
					} else {

						LOG.info("Movie id does not Exist");
						resp.setStatus(404);
						pixtimeStatus = "MOVIE_NOT_FOUND";
						pixtimeStatusDesc = "Movie id does not exist";
						responsePayload = "";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("Get Movies Exception Occured" + e.toString());
			pixtimeStatus = "FAILED";
			pixtimeStatusDesc = "Unable to Retrieve Movie(s)";
			resp.setStatus(500);
			responsePayload = "";

		}

		PrintWriter pw = resp.getWriter();
		resp.setContentType(PixTimeConstants.CONTENT_TYPE);
		resp.addHeader(PixTimeConstants.ACCESS_CONTROL, "*");
		resp.addHeader(PixTimeConstants.PIXTIME_STATUS, pixtimeStatus);
		resp.addHeader(PixTimeConstants.PIXTIME_DESC, pixtimeStatusDesc);
		pw.write(responsePayload);
	}

}
