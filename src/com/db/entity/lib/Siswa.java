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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "siswa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Siswa.findAll", query = "SELECT s FROM Siswa s"),
    @NamedQuery(name = "Siswa.findByNis", query = "SELECT s FROM Siswa s WHERE s.nis = :nis"),
    @NamedQuery(name = "Siswa.findByNama", query = "SELECT s FROM Siswa s WHERE s.nama = :nama"),
    @NamedQuery(name = "Siswa.findByJenkel", query = "SELECT s FROM Siswa s WHERE s.jenkel = :jenkel"),
    @NamedQuery(name = "Siswa.findByAlamat", query = "SELECT s FROM Siswa s WHERE s.alamat = :alamat"),
    @NamedQuery(name = "Siswa.findByTahunMasuk", query = "SELECT s FROM Siswa s WHERE s.tahunMasuk = :tahunMasuk"),
    @NamedQuery(name = "Siswa.findByStatus", query = "SELECT s FROM Siswa s WHERE s.status = :status")})
public class Siswa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nis")
    private String nis;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "jenkel")
    private String jenkel;
    @Basic(optional = false)
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "tahun_masuk")
    private int tahunMasuk;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @JoinColumn(name = "id_kelas", referencedColumnName = "id_kelas")
    @ManyToOne(optional = false)
    private Kelas idKelas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nis")
    private List<RelAlternatifSiswa> relAlternatifSiswaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nis")
    private List<RelKriteriaSiswa> relKriteriaSiswaList;

    public Siswa() {
    }

    public Siswa(String nis) {
        this.nis = nis;
    }

    public Siswa(String nis, String nama, String jenkel, String alamat, int tahunMasuk, String status) {
        this.nis = nis;
        this.nama = nama;
        this.jenkel = jenkel;
        this.alamat = alamat;
        this.tahunMasuk = tahunMasuk;
        this.status = status;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(int tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Kelas getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(Kelas idKelas) {
        this.idKelas = idKelas;
    }

    @XmlTransient
    public List<RelAlternatifSiswa> getRelAlternatifSiswaList() {
        return relAlternatifSiswaList;
    }

    public void setRelAlternatifSiswaList(List<RelAlternatifSiswa> relAlternatifSiswaList) {
        this.relAlternatifSiswaList = relAlternatifSiswaList;
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
        hash += (nis != null ? nis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Siswa)) {
            return false;
        }
        Siswa other = (Siswa) object;
        if ((this.nis == null && other.nis != null) || (this.nis != null && !this.nis.equals(other.nis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.Siswa[ nis=" + nis + " ]";
    }
    
}
