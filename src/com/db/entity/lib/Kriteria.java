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
@Table(name = "kriteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kriteria.findAll", query = "SELECT k FROM Kriteria k"),
    @NamedQuery(name = "Kriteria.findByIdKriteria", query = "SELECT k FROM Kriteria k WHERE k.idKriteria = :idKriteria"),
    @NamedQuery(name = "Kriteria.findByNamaKriteria", query = "SELECT k FROM Kriteria k WHERE k.namaKriteria = :namaKriteria"),
    @NamedQuery(name = "Kriteria.findByKeterangan", query = "SELECT k FROM Kriteria k WHERE k.keterangan = :keterangan")})
public class Kriteria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kriteria")
    private String idKriteria;
    @Basic(optional = false)
    @Column(name = "nama_kriteria")
    private String namaKriteria;
    @Basic(optional = false)
    @Column(name = "keterangan")
    private String keterangan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKriteria")
    private List<VarKriteria> varKriteriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKriteria")
    private List<RelKriteriaAlternatif> relKriteriaAlternatifList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKriteria")
    private List<RelKriteriaSiswa> relKriteriaSiswaList;

    public Kriteria() {
    }

    public Kriteria(String idKriteria) {
        this.idKriteria = idKriteria;
    }

    public Kriteria(String idKriteria, String namaKriteria, String keterangan) {
        this.idKriteria = idKriteria;
        this.namaKriteria = namaKriteria;
        this.keterangan = keterangan;
    }

    public String getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(String idKriteria) {
        this.idKriteria = idKriteria;
    }

    public String getNamaKriteria() {
        return namaKriteria;
    }

    public void setNamaKriteria(String namaKriteria) {
        this.namaKriteria = namaKriteria;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @XmlTransient
    public List<VarKriteria> getVarKriteriaList() {
        return varKriteriaList;
    }

    public void setVarKriteriaList(List<VarKriteria> varKriteriaList) {
        this.varKriteriaList = varKriteriaList;
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
        hash += (idKriteria != null ? idKriteria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kriteria)) {
            return false;
        }
        Kriteria other = (Kriteria) object;
        if ((this.idKriteria == null && other.idKriteria != null) || (this.idKriteria != null && !this.idKriteria.equals(other.idKriteria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.Kriteria[ idKriteria=" + idKriteria + " ]";
    }
    
}
