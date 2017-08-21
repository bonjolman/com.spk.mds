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
@Table(name = "rel_alternatif_siswa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelAlternatifSiswa.findAll", query = "SELECT r FROM RelAlternatifSiswa r"),
    @NamedQuery(name = "RelAlternatifSiswa.findById", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.id = :id"),
    @NamedQuery(name = "RelAlternatifSiswa.findByIdxQ", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.idxQ = :idxQ"),
    @NamedQuery(name = "RelAlternatifSiswa.findByIdxY", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.idxY = :idxY"),
    @NamedQuery(name = "RelAlternatifSiswa.findByIdxZ", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.idxZ = :idxZ"),
    @NamedQuery(name = "RelAlternatifSiswa.findByTidakOptimis", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.tidakOptimis = :tidakOptimis"),
    @NamedQuery(name = "RelAlternatifSiswa.findByOptimis", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.optimis = :optimis"),
    @NamedQuery(name = "RelAlternatifSiswa.findBySangatOptimis", query = "SELECT r FROM RelAlternatifSiswa r WHERE r.sangatOptimis = :sangatOptimis")})
public class RelAlternatifSiswa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "idx_q")
    private double idxQ;
    @Basic(optional = false)
    @Column(name = "idx_y")
    private double idxY;
    @Basic(optional = false)
    @Column(name = "idx_z")
    private double idxZ;
    @Basic(optional = false)
    @Column(name = "tidak_optimis")
    private double tidakOptimis;
    @Basic(optional = false)
    @Column(name = "optimis")
    private double optimis;
    @Basic(optional = false)
    @Column(name = "sangat_optimis")
    private double sangatOptimis;
    @JoinColumn(name = "nis", referencedColumnName = "nis")
    @ManyToOne(optional = false)
    private Siswa nis;
    @JoinColumn(name = "id_alternatif", referencedColumnName = "id_alternatif")
    @ManyToOne(optional = false)
    private Alternatif idAlternatif;

    public RelAlternatifSiswa() {
    }

    public RelAlternatifSiswa(String id) {
        this.id = id;
    }

    public RelAlternatifSiswa(String id, double idxQ, double idxY, double idxZ, double tidakOptimis, double optimis, double sangatOptimis) {
        this.id = id;
        this.idxQ = idxQ;
        this.idxY = idxY;
        this.idxZ = idxZ;
        this.tidakOptimis = tidakOptimis;
        this.optimis = optimis;
        this.sangatOptimis = sangatOptimis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getIdxQ() {
        return idxQ;
    }

    public void setIdxQ(double idxQ) {
        this.idxQ = idxQ;
    }

    public double getIdxY() {
        return idxY;
    }

    public void setIdxY(double idxY) {
        this.idxY = idxY;
    }

    public double getIdxZ() {
        return idxZ;
    }

    public void setIdxZ(double idxZ) {
        this.idxZ = idxZ;
    }

    public double getTidakOptimis() {
        return tidakOptimis;
    }

    public void setTidakOptimis(double tidakOptimis) {
        this.tidakOptimis = tidakOptimis;
    }

    public double getOptimis() {
        return optimis;
    }

    public void setOptimis(double optimis) {
        this.optimis = optimis;
    }

    public double getSangatOptimis() {
        return sangatOptimis;
    }

    public void setSangatOptimis(double sangatOptimis) {
        this.sangatOptimis = sangatOptimis;
    }

    public Siswa getNis() {
        return nis;
    }

    public void setNis(Siswa nis) {
        this.nis = nis;
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
        if (!(object instanceof RelAlternatifSiswa)) {
            return false;
        }
        RelAlternatifSiswa other = (RelAlternatifSiswa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.RelAlternatifSiswa[ id=" + id + " ]";
    }
    
}
