package com.pixtime.vo;


public class Movie {

	 private String movieId;
   	 private String movieName;
	 private String description;
	 private String total_reviews;
	 private String movieurl;
	 private String wikipedia_url;
	 private String image;
//	 private String director;
//	 private String cast;
	 private String yearReleased;
	 private String genre;
	 private String current_Star_Rate;
	 private String uploaded_Date;
	 private String site_display_status;
	 private String rating_End_Date;
	 private String specialNotes;
	 private String encodedImg;
	 
	 
	 
	 public Movie(String id, String movieName, String description,
			String total_reviews, String movieurl, String wikipedia_url,
			String image, String yearReleased, String genre,
			String current_Star_Rate, String uploaded_Date,
			String site_display_status, String rating_End_Date,
			String specialNotes,String encodedImg) {
		super();
		this.movieId = id;
		this.movieName = movieName;
		this.description = description;
		this.total_reviews = total_reviews;
		this.movieurl = movieurl;
		this.wikipedia_url = wikipedia_url;
		this.image = image;
		this.yearReleased = yearReleased;
		this.genre = genre;
		this.current_Star_Rate = current_Star_Rate;
		this.uploaded_Date = uploaded_Date;
		this.site_display_status = site_display_status;
		this.rating_End_Date = rating_End_Date;
		this.specialNotes = specialNotes;
		this.encodedImg = encodedImg;
	}
	public String getEncodedImg() {
		return encodedImg;
	}
	public void setEncodedImg(String encodedImg) {
		this.encodedImg = encodedImg;
	}
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTotal_reviews() {
		return total_reviews;
	}
	public void setTotal_reviews(String total_reviews) {
		this.total_reviews = total_reviews;
	}
	public String getMovieurl() {
		return movieurl;
	}
	public void setMovieurl(String movieurl) {
		this.movieurl = movieurl;
	}
	public String getWikipedia_url() {
		return wikipedia_url;
	}
	public void setWikipedia_url(String wikipedia_url) {
		this.wikipedia_url = wikipedia_url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getYearReleased() {
		return yearReleased;
	}
	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCurrent_Star_Rate() {
		return current_Star_Rate;
	}
	public void setCurrent_Star_Rate(String current_Star_Rate) {
		this.current_Star_Rate = current_Star_Rate;
	}
	public String getUploaded_Date() {
		return uploaded_Date;
	}
	public void setUploaded_Date(String uploaded_Date) {
		this.uploaded_Date = uploaded_Date;
	}
	public String getSite_display_status() {
		return site_display_status;
	}
	public void setSite_display_status(String site_display_status) {
		this.site_display_status = site_display_status;
	}
	public String getRating_End_Date() {
		return rating_End_Date;
	}
	public void setRating_End_Date(String rating_End_Date) {
		this.rating_End_Date = rating_End_Date;
	}
	public String getSpecialNotes() {
		return specialNotes;
	}
	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}
	

}
