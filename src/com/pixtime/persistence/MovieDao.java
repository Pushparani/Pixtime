package com.pixtime.persistence;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.pixtime.entities.MovieEO;
import com.pixtime.exceptions.PixTimeException;
import com.pixtime.vo.Movie;

public class MovieDao {

	private static final Logger LOG = Logger.getLogger(MovieDao.class
			.getSimpleName());

	public MovieDao() {
		// TODO Auto-generated constructor stub
	}

	static {
		ObjectifyService.register(MovieEO.class);

	}

	public void insertMovies(Movie movie) throws PixTimeException {

		try {
			Objectify ofy = ObjectifyService.begin();

			String uniqueID = UUID.randomUUID().toString();

			String s = movie.getImage();

			URL url = new URL(s);

			movie.setMovieurl(movie.getMovieurl());

			movie.setEncodedImg(Base64.encodeBase64String(IOUtils
					.toByteArray(url.openStream())));

			MovieEO movieObj = new MovieEO(uniqueID, movie.getMovieName(),
					movie.getDescription(), movie.getTotal_reviews(),
					movie.getMovieurl(), movie.getWikipedia_url(),
					movie.getImage(), movie.getYearReleased(),
					movie.getGenre(), movie.getCurrent_Star_Rate(),
					movie.getUploaded_Date(), movie.getSite_display_status(),
					movie.getRating_End_Date(), movie.getSpecialNotes(),
					movie.getEncodedImg());

			ofy.put(movieObj);

		} catch (Exception e) {
			LOG.info("Exception Occured While inserting a Movie" + e.toString());
			throw new PixTimeException(
					"Exception Occured While inserting a Movie");
		}
	}

	public List<Movie> getMovies() throws PixTimeException {

		ArrayList<Movie> movieArr = new ArrayList<Movie>();
		try {
			Objectify ofy = ObjectifyService.begin();

			Iterator<MovieEO> movieEOItr = ofy.query(MovieEO.class).iterator();

			while (movieEOItr.hasNext()) {
				MovieEO movieEobj = movieEOItr.next();
				Movie movie = new Movie();
				movie.setMovieName(movieEobj.getMovieName());
				movie.setEncodedImg(movieEobj.getEncodedImg());
				movie.setCurrent_Star_Rate(movieEobj.getCurrent_Star_Rate());
				movie.setDescription(movieEobj.getDescription());
				movie.setGenre(movieEobj.getGenre());
				movie.setImage(movieEobj.getImage());
				movie.setMovieurl(movieEobj.getMovieurl());
				movie.setRating_End_Date(movieEobj.getRating_End_Date());
				movie.setSite_display_status(movieEobj.getSite_display_status());
				movie.setSpecialNotes(movieEobj.getSpecialNotes());
				movie.setTotal_reviews(movieEobj.getTotal_reviews());
				movie.setUploaded_Date(movieEobj.getUploaded_Date());
				movie.setWikipedia_url(movieEobj.getWikipedia_url());
				movie.setYearReleased(movieEobj.getYearRelease());
				movie.setMovieId(movieEobj.getId());
				movieArr.add(movie);

			}

		} catch (Exception e) {

			LOG.info("Exception Occured While getting movies from Big Table:"
					+ e.toString());

			throw new PixTimeException(
					"Exception Occured While getting movies from Big Table");

		}

		return movieArr;
	}

	public Movie findMovieById(String movieId) throws PixTimeException {
		Movie movie = null;
		try {
			Objectify ofy = ObjectifyService.begin();

			MovieEO movieEO = ofy.get(MovieEO.class, movieId);

			if (movieEO != null) {
				movie = new Movie();
				movie.setCurrent_Star_Rate(movieEO.getCurrent_Star_Rate());
				movie.setDescription(movieEO.getDescription());
				movie.setEncodedImg(movieEO.getEncodedImg());
				movie.setGenre(movieEO.getGenre());
				movie.setImage(movieEO.getImage());
				movie.setMovieName(movieEO.getMovieName());
				movie.setMovieurl(movieEO.getMovieurl());
				movie.setRating_End_Date(movieEO.getRating_End_Date());
				movie.setSite_display_status(movieEO.getSite_display_status());
				movie.setSpecialNotes(movieEO.getSpecialNotes());
				movie.setTotal_reviews(movieEO.getTotal_reviews());
				movie.setUploaded_Date(movieEO.getUploaded_Date());
				movie.setWikipedia_url(movieEO.getWikipedia_url());
				movie.setYearReleased(movieEO.getYearRelease());
				movie.setMovieId(movieEO.getId());

			}
		} catch (NotFoundException notFound) {

			LOG.info("Movie Not Found:" + notFound.toString());

		} catch (Exception e) {

			LOG.info("Exception Occured while getting Movie" + e.toString());
			throw new PixTimeException(
					"Exception Occured while getting Movie for id:" + movieId);
		}

		return movie;
	}
}
