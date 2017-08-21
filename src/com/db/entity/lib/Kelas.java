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
@Table(name = "kelas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kelas.findAll", query = "SELECT k FROM Kelas k"),
    @NamedQuery(name = "Kelas.findByIdKelas", query = "SELECT k FROM Kelas k WHERE k.idKelas = :idKelas"),
    @NamedQuery(name = "Kelas.findByNamaKelas", query = "SELECT k FROM Kelas k WHERE k.namaKelas = :namaKelas"),
    @NamedQuery(name = "Kelas.findByNamaRuang", query = "SELECT k FROM Kelas k WHERE k.namaRuang = :namaRuang"),
    @NamedQuery(name = "Kelas.findByJumlahSiswa", query = "SELECT k FROM Kelas k WHERE k.jumlahSiswa = :jumlahSiswa")})
public class Kelas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kelas")
    private String idKelas;
    @Basic(optional = false)
    @Column(name = "nama_kelas")
    private String namaKelas;
    @Basic(optional = false)
    @Column(name = "nama_ruang")
    private String namaRuang;
    @Basic(optional = false)
    @Column(name = "jumlah_siswa")
    private int jumlahSiswa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKelas")
    private List<Siswa> siswaList;

    public Kelas() {
    }

    public Kelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public Kelas(String idKelas, String namaKelas, String namaRuang, int jumlahSiswa) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.namaRuang = namaRuang;
        this.jumlahSiswa = jumlahSiswa;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public int getJumlahSiswa() {
        return jumlahSiswa;
    }

    public void setJumlahSiswa(int jumlahSiswa) {
        this.jumlahSiswa = jumlahSiswa;
    }

    @XmlTransient
    public List<Siswa> getSiswaList() {
        return siswaList;
    }

    public void setSiswaList(List<Siswa> siswaList) {
        this.siswaList = siswaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKelas != null ? idKelas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kelas)) {
            return false;
        }
        Kelas other = (Kelas) object;
        if ((this.idKelas == null && other.idKelas != null) || (this.idKelas != null && !this.idKelas.equals(other.idKelas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.Kelas[ idKelas=" + idKelas + " ]";
    }
    
}
