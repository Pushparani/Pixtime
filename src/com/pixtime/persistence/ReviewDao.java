package com.pixtime.persistence;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.pixtime.entities.MovieEO;
import com.pixtime.entities.ReviewEO;
import com.pixtime.exceptions.PixTimeException;
import com.pixtime.vo.Movie;
import com.pixtime.vo.Review;

public class ReviewDao {

	private static final Logger LOG = Logger.getLogger(ReviewDao.class
			.getSimpleName());

	public static final String REVIEW_IND = "rev-";

	public ReviewDao() {
		// TODO Auto-generated constructor stub
	}

	static {

		System.out.println("Registerting ReviewEO");
		ObjectifyService.register(ReviewEO.class);

	}

	public void insertReview(Review review) {

		try {
			Objectify ofy = ObjectifyService.begin();

			String reviewId = REVIEW_IND + UUID.randomUUID().toString();

			ReviewEO reviewEo = new ReviewEO(reviewId, review.getUserId(),
					review.getReviewTs(), review.getStarRating(),
					review.getComments(), review.getMovieId());

			ofy.put(reviewEo);

		} catch (Exception e) {

		}

	}

	public void updateReview(Review review) {

	}

	public void deleteReview(Review review) {

	}

	public void getReviewsbyMovieId(Review review) throws PixTimeException {

		ArrayList<Review> reviewArr = new ArrayList<Review>();

		try {
			Objectify ofy = ObjectifyService.begin();

			Query<ReviewEO> reviewEOItr = ofy.query(ReviewEO.class);

			for (ReviewEO reviewObj : reviewEOItr) {

				Review reviewVO = new Review();
				reviewVO.setComments(reviewObj.getComments());
				reviewVO.setMovieId(reviewObj.getMovieId());
				reviewVO.setReviewId(reviewObj.getReviewId());
				reviewVO.setReviewTs(reviewObj.getReviewTs());
				reviewVO.setStarRating(reviewObj.getStarRating());
				reviewVO.setUserId(reviewObj.getUserId());
				reviewArr.add(reviewVO);

			}

		} catch (Exception e) {
			throw new PixTimeException(
					"Exception Occured Getting Review for Movie: "
							+ e.toString());

		}

	}

	public void getReviewByUserId(Review review) {

	}
}
