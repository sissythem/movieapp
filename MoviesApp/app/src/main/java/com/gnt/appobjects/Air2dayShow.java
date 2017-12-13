package com.gnt.appobjects;

import java.io.Serializable;

/**
 * Created by sissy on 12/5/17.
 */

public class Air2dayShow implements Serializable {
    private Integer id;
    private Integer idTmdb;
    private Show show;

    public Air2dayShow() {
    }

    public Air2dayShow(Integer idTmdb) {
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
