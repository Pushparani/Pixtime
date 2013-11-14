package com.pixtime.entities;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Unindexed;

@Entity
public class UserEO {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerified_email() {
		return verified_email;
	}

	public void setVerified_email(Boolean verified_email) {
		this.verified_email = verified_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public UserEO() {
		// TODO Auto-generated constructor stub
	}

	@Id
	private String id;
	@Unindexed
	private String email;
	@Unindexed
	private Boolean verified_email;
	@Unindexed
	private String name;
	@Unindexed
	private String given_name;
	@Unindexed
	private String family_name;
	@Unindexed
	private String link;
	@Unindexed
	private String picture;
	@Unindexed
	private String gender;
	@Unindexed
	private String locale;

	@Unindexed
	private String accessToken;
	@Unindexed
	private String refreshToken;
	@Unindexed
	private String channel;
	@Unindexed
	private String currentTimeStamp;
	@Unindexed
	private String userName;
	
	
	public UserEO(String id, String email, Boolean verified_email, String name,
			String given_name, String family_name, String link, String picture,
			String gender, String locale, String accessToken,
			String refreshToken, String channel,String currentTimeStamp,String userName) {
		super();
		this.id = id;
		this.email = email;
		this.verified_email = verified_email;
		this.name = name;
		this.given_name = given_name;
		this.family_name = family_name;
		this.link = link;
		this.picture = picture;
		this.gender = gender;
		this.locale = locale;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.channel = channel;
		this.currentTimeStamp = currentTimeStamp;
		this.userName = userName;
	}

	

}
