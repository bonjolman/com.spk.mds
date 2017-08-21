/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.entity.lib;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WILDAN
 */
@Entity
@Table(name = "var_kriteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VarKriteria.findAll", query = "SELECT v FROM VarKriteria v"),
    @NamedQuery(name = "VarKriteria.findById", query = "SELECT v FROM VarKriteria v WHERE v.id = :id"),
    @NamedQuery(name = "VarKriteria.findByValue1", query = "SELECT v FROM VarKriteria v WHERE v.value1 = :value1"),
    @NamedQuery(name = "VarKriteria.findByValue2", query = "SELECT v FROM VarKriteria v WHERE v.value2 = :value2"),
    @NamedQuery(name = "VarKriteria.findByValue3", query = "SELECT v FROM VarKriteria v WHERE v.value3 = :value3"),
    @NamedQuery(name = "VarKriteria.findByKeterangan", query = "SELECT v FROM VarKriteria v WHERE v.keterangan = :keterangan")})
public class VarKriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "value_1")
    private String value1;
    @Basic(optional = false)
    @Column(name = "value_2")
    private String value2;
    @Basic(optional = false)
    @Column(name = "value_3")
    private String value3;
    @Basic(optional = false)
    @Column(name = "keterangan")
    private String keterangan;
    @JoinColumn(name = "id_kriteria", referencedColumnName = "id_kriteria")
    @ManyToOne(optional = false)
    private Kriteria idKriteria;

    public VarKriteria() {
    }

    public VarKriteria(Integer id) {
        this.id = id;
    }

    public VarKriteria(Integer id, String value1, String value2, String value3, String keterangan) {
        this.id = id;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.keterangan = keterangan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Kriteria getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(Kriteria idKriteria) {
        this.idKriteria = idKriteria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VarKriteria)) {
            return false;
        }
        VarKriteria other = (VarKriteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.VarKriteria[ id=" + id + " ]";
    }
    
}
