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
@Table(name = "alternatif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alternatif.findAll", query = "SELECT a FROM Alternatif a"),
    @NamedQuery(name = "Alternatif.findByIdAlternatif", query = "SELECT a FROM Alternatif a WHERE a.idAlternatif = :idAlternatif"),
    @NamedQuery(name = "Alternatif.findByNama", query = "SELECT a FROM Alternatif a WHERE a.nama = :nama"),
    @NamedQuery(name = "Alternatif.findByKeterangan", query = "SELECT a FROM Alternatif a WHERE a.keterangan = :keterangan")})
public class Alternatif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_alternatif")
    private String idAlternatif;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "keterangan")
    private String keterangan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlternatif")
    private List<RelAlternatifSiswa> relAlternatifSiswaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlternatif")
    private List<RelKriteriaAlternatif> relKriteriaAlternatifList;

    public Alternatif() {
    }

    public Alternatif(String idAlternatif) {
        this.idAlternatif = idAlternatif;
    }

    public Alternatif(String idAlternatif, String nama, String keterangan) {
        this.idAlternatif = idAlternatif;
        this.nama = nama;
        this.keterangan = keterangan;
    }

    public String getIdAlternatif() {
        return idAlternatif;
    }

    public void setIdAlternatif(String idAlternatif) {
        this.idAlternatif = idAlternatif;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @XmlTransient
    public List<RelAlternatifSiswa> getRelAlternatifSiswaList() {
        return relAlternatifSiswaList;
    }

    public void setRelAlternatifSiswaList(List<RelAlternatifSiswa> relAlternatifSiswaList) {
        this.relAlternatifSiswaList = relAlternatifSiswaList;
    }

    @XmlTransient
    public List<RelKriteriaAlternatif> getRelKriteriaAlternatifList() {
        return relKriteriaAlternatifList;
    }

    public void setRelKriteriaAlternatifList(List<RelKriteriaAlternatif> relKriteriaAlternatifList) {
        this.relKriteriaAlternatifList = relKriteriaAlternatifList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlternatif != null ? idAlternatif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alternatif)) {
            return false;
        }
        Alternatif other = (Alternatif) object;
        if ((this.idAlternatif == null && other.idAlternatif != null) || (this.idAlternatif != null && !this.idAlternatif.equals(other.idAlternatif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.Alternatif[ idAlternatif=" + idAlternatif + " ]";
    }
    
}
