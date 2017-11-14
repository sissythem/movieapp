package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the air_2day_shows database table.
 *
 */
@Entity
@Table(name="air_2day_shows")
@NamedQueries({
        @NamedQuery(name = "Air2dayShow.findAll", query = "SELECT a FROM Air2dayShow a"),
        @NamedQuery(name = "Air2dayShow.findById", query = "SELECT a FROM Air2dayShow a WHERE a.id = :id"),
        @NamedQuery(name = "Air2dayShow.findByIdTmdb", query = "SELECT a FROM Air2dayShow a WHERE a.idTmdb = :idTmdb"),
        @NamedQuery(name = "Air2dayShow.findByShowId", query = "SELECT a FROM Air2dayShow a WHERE a.show.id= :showId"),
        @NamedQuery(name = "Air2dayShow.getAllIdTmdb", query = "SELECT a.idTmdb FROM Air2dayShow a"),
})
public class Air2dayShow implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int idTmdb;

    @OneToOne(targetEntity=Show.class,mappedBy="air2dayShow")
    private Show show;

    public Air2dayShow() {
    }
    
    public Air2dayShow(int idTmdb) {
    	this.idTmdb = idTmdb;
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