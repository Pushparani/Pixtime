package com.pixtime.entities;

import javax.persistence.Id;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Unindexed;
import com.googlecode.objectify.annotation.Indexed;


@Entity
public class MovieEO{
	@Id private String id;	
	
	@Unindexed private String movieName;
	@Unindexed private String description;
	@Unindexed private String total_reviews;
	@Unindexed private String movieurl;
	@Unindexed private String wikipedia_url;
	@Unindexed private String image;
//	@Unindexed private String director;
//	@Unindexed private String cast;
	@Unindexed private String yearReleased;
	@Unindexed private String genre;
	@Unindexed private int current_Star_Rate;
	@Unindexed private String uploaded_Date;
	@Unindexed private String site_display_status;
	@Unindexed private String rating_End_Date;
	@Unindexed private String specialNotes;
	@Unindexed private String encodedImg;
	
	
	

	public String getEncodedImg() {
		return encodedImg;
	}

	public void setEncodedImg(String encodedImg) {
		this.encodedImg = encodedImg;
	}

	public MovieEO(String id, String movieName, String description,
			String total_reviews, String movieurl, String wikipedia_url,
			String image, String yearRelease,
			String genre, int current_Star_Rate, String uploaded_Date,
			String site_display_status, String rating_End_Date,
			String specialNotes,String encodedImg) {
		super();
		this.id = id;
		this.movieName = movieName;
		this.description = description;
		this.total_reviews = total_reviews;
		this.movieurl = movieurl;
		this.wikipedia_url = wikipedia_url;
		this.image = image;
//		this.director = director;
//		this.cast = cast;
		this.yearReleased = yearRelease;
		this.genre = genre;
		this.current_Star_Rate = current_Star_Rate;
		this.uploaded_Date = uploaded_Date;
		this.site_display_status = site_display_status;
		this.rating_End_Date = rating_End_Date;
		this.specialNotes = specialNotes;
		this.encodedImg = encodedImg;
	}

	public MovieEO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public String getYearRelease() {
		return yearReleased;
	}

	public void setYearRelease(String yearRelease) {
		this.yearReleased = yearRelease;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getCurrent_Star_Rate() {
		return current_Star_Rate;
	}

	public void setCurrent_Star_Rate(int current_Star_Rate) {
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

	public static MovieEO findByName(String id){
		Objectify service = getService();
		return service.get(MovieEO.class, id);
	}

	private static Objectify getService() {
		return ObjectifyService.begin();
	}
	

	
	public void save(){
		Objectify service = getService();
		service.put(this);
	}

	
}
