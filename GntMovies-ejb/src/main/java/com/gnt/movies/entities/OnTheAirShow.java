package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the on_the_air_shows database table.
 *
 */
@Entity
@Table(name="on_the_air_shows")
@NamedQueries({
        @NamedQuery(name = "OnTheAirShow.findAll", query = "SELECT o FROM OnTheAirShow o"),
        @NamedQuery(name = "OnTheAirShow.findById", query = "SELECT o FROM OnTheAirShow o WHERE o.id = :id"),
        @NamedQuery(name = "OnTheAirShow.findByIdTmdb", query = "SELECT o FROM OnTheAirShow o WHERE o.idTmdb = :idTmdb"),
        @NamedQuery(name = "OnTheAirShow.findByShowId", query = "SELECT o FROM OnTheAirShow o WHERE o.show.id = :showId")
})
public class OnTheAirShow implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int idTmdb;

    @ManyToOne
    @JoinColumn(name="showId")
    private Show show;

    public OnTheAirShow() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTmdb() {
        return this.idTmdb;
    }

    public void setIdTmdb(int idTmdb) {
        this.idTmdb = idTmdb;
    }

    public Show getShow() {
        return this.show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

}