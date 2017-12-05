package com.gnt.appobjects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by sissy on 12/5/17.
 */

public class User implements Serializable {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private LocalDate birthdate;
    private String photo;
    private Integer age;
    private String token;

    private ArrayList<MovieFavorite> movieFavorites;
    private ArrayList<MovieReview> movieReviews;
    private ArrayList<ShowFavorite> showFavorites;
    private ArrayList<ShowReview> showReviews;

    public User() {
    }

    public User(Integer age, String email, String firstname, String lastname, String password, String username) {
        super();
        this.age = age;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<MovieFavorite> getMovieFavorites() {
        return this.movieFavorites;
    }

    public void setMovieFavorites(ArrayList<MovieFavorite> movieFavorites) {
        this.movieFavorites = movieFavorites;
    }

    public MovieFavorite addMovieFavorite(MovieFavorite movieFavorite) {
        getMovieFavorites().add(movieFavorite);
        movieFavorite.setUser(this);

        return movieFavorite;
    }

    public MovieFavorite removeMovieFavorite(MovieFavorite movieFavorite) {
        getMovieFavorites().remove(movieFavorite);
        movieFavorite.setUser(null);

        return movieFavorite;
    }

    public ArrayList<MovieReview> getMovieReviews() {
        return this.movieReviews;
    }

    public void setMovieReviews(ArrayList<MovieReview> movieReviews) {
        this.movieReviews = movieReviews;
    }

    public MovieReview addMovieReview(MovieReview movieReview) {
        getMovieReviews().add(movieReview);
        movieReview.setUser(this);

        return movieReview;
    }

    public MovieReview removeMovieReview(MovieReview movieReview) {
        getMovieReviews().remove(movieReview);
        movieReview.setUser(null);
        return movieReview;
    }

    public ArrayList<ShowFavorite> getShowFavorites() {
        return this.showFavorites;
    }

    public void setShowFavorites(ArrayList<ShowFavorite> showFavorites) {
        this.showFavorites = showFavorites;
    }

    public ShowFavorite addShowFavorite(ShowFavorite showFavorite) {
        getShowFavorites().add(showFavorite);
        showFavorite.setUser(this);

        return showFavorite;
    }

    public ShowFavorite removeShowFavorite(ShowFavorite showFavorite) {
        getShowFavorites().remove(showFavorite);
        showFavorite.setUser(null);
        return showFavorite;
    }

    public ArrayList<ShowReview> getShowReviews() {
        return this.showReviews;
    }

    public void setShowReviews(ArrayList<ShowReview> showReviews) {
        this.showReviews = showReviews;
    }

    public ShowReview addShowReview(ShowReview showReview) {
        getShowReviews().add(showReview);
        showReview.setUser(this);
        return showReview;
    }

    public ShowReview removeShowReview(ShowReview showReview) {
        getShowReviews().remove(showReview);
        showReview.setUser(null);
        return showReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
