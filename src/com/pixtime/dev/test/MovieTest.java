package com.pixtime.dev.test;

import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

import com.pixtime.vo.Movie;

public class MovieTest {

	public MovieTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]){
		
		
		
		String id = UUID.randomUUID().toString();
		
		
		String movieName = "Anbeva";
		String description= "Classic hit of MGR";
		String total_reviews = "50";
		String movieurl = "https://imayampixs.appspot.com";
		String wikipedia_url="http://www.aikipedia.org";
		String image = "https://imayampixs.appspot.com/img/anbeva.jpg";
		String yearReleased= "1977";
		String genre="romance";
		String current_Star_Rate="5 Stars";
	    String uploaded_Date = "11/03/2013";
		String site_display_status = "Active";
		String rating_End_Date = "NA";
		String specialNotes = "A Memorable one for all people in Tamilnadu";
		String encodedImg = "OrI+B6fu4ZUuI0GWpcnppf3";
		
		Movie movie = new Movie(id,movieName,description,total_reviews,movieurl,wikipedia_url,image,yearReleased,genre,current_Star_Rate,uploaded_Date,site_display_status,rating_End_Date,specialNotes,encodedImg);
		
		ObjectMapper objMapper = new ObjectMapper();
		try{
			String movieReq = objMapper.writeValueAsString(movie);
		Movie movie1 = objMapper.readValue(movieReq, Movie.class);
			System.out.println(movie1.getCurrent_Star_Rate());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
