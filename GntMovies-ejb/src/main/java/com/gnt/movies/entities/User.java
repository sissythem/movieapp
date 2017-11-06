package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name="users")
@NamedQueries({
        @NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
        @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
        @NamedQuery(name = "User.findByAge", query = "SELECT u FROM User u WHERE u.age = :age")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int age;

    private LocalDate birthdate;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    private String photo;

    private Timestamp registrationDate;

    private String username;

    @OneToMany(mappedBy="user")
    private List<MovieFavorite> movieFavorites;

    @OneToMany(mappedBy="user")
    private List<MovieReview> movieReviews;

    @OneToMany(mappedBy="user")
    private List<ShowFavorite> showFavorites;

    @OneToMany(mappedBy="user")
    private List<ShowReview> showReviews;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
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

    public Timestamp getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<MovieFavorite> getMovieFavorites() {
        return this.movieFavorites;
    }

    public void setMovieFavorites(List<MovieFavorite> movieFavorites) {
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

    public List<MovieReview> getMovieReviews() {
        return this.movieReviews;
    }

    public void setMovieReviews(List<MovieReview> movieReviews) {
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

    public List<ShowFavorite> getShowFavorites() {
        return this.showFavorites;
    }

    public void setShowFavorites(List<ShowFavorite> showFavorites) {
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

    public List<ShowReview> getShowReviews() {
        return this.showReviews;
    }

    public void setShowReviews(List<ShowReview> showReviews) {
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

}