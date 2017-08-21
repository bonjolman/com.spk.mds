/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.entity.lib;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WILDAN
 */
@Entity
@Table(name = "bobot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bobot.findAll", query = "SELECT b FROM Bobot b"),
    @NamedQuery(name = "Bobot.findByIdBobot", query = "SELECT b FROM Bobot b WHERE b.idBobot = :idBobot"),
    @NamedQuery(name = "Bobot.findByNamaBobot", query = "SELECT b FROM Bobot b WHERE b.namaBobot = :namaBobot"),
    @NamedQuery(name = "Bobot.findByValue1", query = "SELECT b FROM Bobot b WHERE b.value1 = :value1"),
    @NamedQuery(name = "Bobot.findByValue2", query = "SELECT b FROM Bobot b WHERE b.value2 = :value2"),
    @NamedQuery(name = "Bobot.findByValue3", query = "SELECT b FROM Bobot b WHERE b.value3 = :value3")})
public class Bobot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bobot")
    private String idBobot;
    @Basic(optional = false)
    @Column(name = "nama_bobot")
    private String namaBobot;
    @Basic(optional = false)
    @Column(name = "value_1")
    private double value1;
    @Basic(optional = false)
    @Column(name = "value_2")
    private double value2;
    @Basic(optional = false)
    @Column(name = "value_3")
    private double value3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bobot")
    private List<RelKriteriaAlternatif> relKriteriaAlternatifList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bobot")
    private List<RelKriteriaSiswa> relKriteriaSiswaList;

    public Bobot() {
    }

    public Bobot(String idBobot) {
        this.idBobot = idBobot;
    }

    public Bobot(String idBobot, String namaBobot, double value1, double value2, double value3) {
        this.idBobot = idBobot;
        this.namaBobot = namaBobot;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public String getIdBobot() {
        return idBobot;
    }

    public void setIdBobot(String idBobot) {
        this.idBobot = idBobot;
    }

    public String getNamaBobot() {
        return namaBobot;
    }

    public void setNamaBobot(String namaBobot) {
        this.namaBobot = namaBobot;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getValue3() {
        return value3;
    }

    public void setValue3(double value3) {
        this.value3 = value3;
    }

    @XmlTransient
    public List<RelKriteriaAlternatif> getRelKriteriaAlternatifList() {
        return relKriteriaAlternatifList;
    }

    public void setRelKriteriaAlternatifList(List<RelKriteriaAlternatif> relKriteriaAlternatifList) {
        this.relKriteriaAlternatifList = relKriteriaAlternatifList;
    }

    @XmlTransient
    public List<RelKriteriaSiswa> getRelKriteriaSiswaList() {
        return relKriteriaSiswaList;
    }

    public void setRelKriteriaSiswaList(List<RelKriteriaSiswa> relKriteriaSiswaList) {
        this.relKriteriaSiswaList = relKriteriaSiswaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBobot != null ? idBobot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bobot)) {
            return false;
        }
        Bobot other = (Bobot) object;
        if ((this.idBobot == null && other.idBobot != null) || (this.idBobot != null && !this.idBobot.equals(other.idBobot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.Bobot[ idBobot=" + idBobot + " ]";
    }
    
}
