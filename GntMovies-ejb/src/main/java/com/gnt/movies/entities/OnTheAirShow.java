package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "on_the_air_shows")
@NamedQueries({ @NamedQuery(name = "OnTheAirShow.findAll", query = "SELECT o FROM OnTheAirShow o"),
		@NamedQuery(name = "OnTheAirShow.findById", query = "SELECT o FROM OnTheAirShow o WHERE o.id = :id"),
		@NamedQuery(name = "OnTheAirShow.findByIdTmdb", query = "SELECT o FROM OnTheAirShow o WHERE o.idTmdb = :idTmdb"),
		@NamedQuery(name = "OnTheAirShow.getAllIdTmdb", query = "SELECT o.idTmdb FROM OnTheAirShow o"),
		@NamedQuery(name = "OnTheAirShow.findByShowId", query = "SELECT o FROM OnTheAirShow o WHERE o.show.id = :showId"),
		@NamedQuery(name = "OnTheAirShow.deleteByIdTmdb", query = "DELETE FROM OnTheAirShow o WHERE o.idTmdb = :idTmdb") })
public class OnTheAirShow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer idTmdb;

	@OneToOne
	@JoinColumn(name = "showId")
	private Show show;

	public OnTheAirShow() {
	}

	public OnTheAirShow(Integer idTmdb) {
		this.idTmdb = idTmdb;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdTmdb() {
		return this.idTmdb;
	}

	public void setIdTmdb(Integer idTmdb) {
		this.idTmdb = idTmdb;
	}

	public Show getShow() {
		return this.show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

}