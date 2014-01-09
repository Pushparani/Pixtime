package com.pixtime.entities;

import javax.persistence.Id;

public class ReviewEO {

	public ReviewEO() {
		// TODO Auto-generated constructor stub
	}
	
	private String reviewId;
	private String userId;
	private String reviewTs;
	private String starRating;
	private String comments;
	//private String movieId;
	@Id private String id;	
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the reviewId
	 */
	public String getReviewId() {
		return reviewId;
	}


	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the reviewTs
	 */
	public String getReviewTs() {
		return reviewTs;
	}


	/**
	 * @param reviewTs the reviewTs to set
	 */
	public void setReviewTs(String reviewTs) {
		this.reviewTs = reviewTs;
	}


	/**
	 * @return the starRating
	 */
	public String getStarRating() {
		return starRating;
	}


	/**
	 * @param starRating the starRating to set
	 */
	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}


	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the movieId
	 */
/*	public String getMovieId() {
		return movieId;
	}*/


	/**
	 * @param movieId the movieId to set
	 */
/*	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}*/


	public ReviewEO(String reviewId, String userId,String reviewTs,
			String starRating, String comments, String Id) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.reviewTs = reviewTs;
		this.starRating = starRating;
		this.comments = comments;
		this.id = Id;
	}
	

}
