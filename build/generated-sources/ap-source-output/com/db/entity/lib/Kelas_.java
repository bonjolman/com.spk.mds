package com.db.entity.lib;

import com.db.entity.lib.Siswa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-05-01T13:18:22")
@StaticMetamodel(Kelas.class)
public class Kelas_ { 

    public static volatile SingularAttribute<Kelas, String> namaRuang;
    public static volatile ListAttribute<Kelas, Siswa> siswaList;
    public static volatile SingularAttribute<Kelas, String> idKelas;
    public static volatile SingularAttribute<Kelas, String> namaKelas;
    public static volatile SingularAttribute<Kelas, Integer> jumlahSiswa;

}