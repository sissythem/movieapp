package com.gnt.movies.dto;

import java.io.Serializable;

import com.gnt.movies.entities.User;

public class UserSessionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String firstname;

	private String password; // ram server/call sql(id(indexed))

	private String photo;

	private String username;

	public UserSessionDto() {
		super();
	}

	public UserSessionDto(Integer id, String firstname, String password, String photo, String username) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.password = password;
		this.photo = photo;
		this.username = username;
	}

	public UserSessionDto(User user) {
		super();
		this.id = user.getId();
		this.firstname = user.getFirstname();
		this.password = user.getPassword();
		this.photo = user.getPhoto();
		this.username = user.getUsername();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSessionDto [id=");
		builder.append(id);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", password=");
		builder.append(password);
		builder.append(", photo=");
		builder.append(photo);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
}
