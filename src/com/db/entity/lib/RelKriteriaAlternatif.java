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
@Table(name = "rel_kriteria_alternatif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelKriteriaAlternatif.findAll", query = "SELECT r FROM RelKriteriaAlternatif r"),
    @NamedQuery(name = "RelKriteriaAlternatif.findById", query = "SELECT r FROM RelKriteriaAlternatif r WHERE r.id = :id")})
public class RelKriteriaAlternatif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @JoinColumn(name = "bobot", referencedColumnName = "id_bobot")
    @ManyToOne(optional = false)
    private Bobot bobot;
    @JoinColumn(name = "id_kriteria", referencedColumnName = "id_kriteria")
    @ManyToOne(optional = false)
    private Kriteria idKriteria;
    @JoinColumn(name = "id_alternatif", referencedColumnName = "id_alternatif")
    @ManyToOne(optional = false)
    private Alternatif idAlternatif;

    public RelKriteriaAlternatif() {
    }

    public RelKriteriaAlternatif(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bobot getBobot() {
        return bobot;
    }

    public void setBobot(Bobot bobot) {
        this.bobot = bobot;
    }

    public Kriteria getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(Kriteria idKriteria) {
        this.idKriteria = idKriteria;
    }

    public Alternatif getIdAlternatif() {
        return idAlternatif;
    }

    public void setIdAlternatif(Alternatif idAlternatif) {
        this.idAlternatif = idAlternatif;
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
        if (!(object instanceof RelKriteriaAlternatif)) {
            return false;
        }
        RelKriteriaAlternatif other = (RelKriteriaAlternatif) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.RelKriteriaAlternatif[ id=" + id + " ]";
    }
    
}
