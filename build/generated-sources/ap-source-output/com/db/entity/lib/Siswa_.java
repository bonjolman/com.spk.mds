package com.db.entity.lib;

import com.db.entity.lib.Kelas;
import com.db.entity.lib.RelAlternatifSiswa;
import com.db.entity.lib.RelKriteriaSiswa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-05-01T13:18:22")
@StaticMetamodel(Siswa.class)
public class Siswa_ { 

    public static volatile SingularAttribute<Siswa, String> nama;
    public static volatile SingularAttribute<Siswa, byte[]> foto;
    public static volatile SingularAttribute<Siswa, String> nis;
    public static volatile SingularAttribute<Siswa, Integer> tahunMasuk;
    public static volatile SingularAttribute<Siswa, Kelas> idKelas;
    public static volatile SingularAttribute<Siswa, String> jenkel;
    public static volatile ListAttribute<Siswa, RelKriteriaSiswa> relKriteriaSiswaList;
    public static volatile ListAttribute<Siswa, RelAlternatifSiswa> relAlternatifSiswaList;
    public static volatile SingularAttribute<Siswa, String> alamat;
    public static volatile SingularAttribute<Siswa, String> status;

}