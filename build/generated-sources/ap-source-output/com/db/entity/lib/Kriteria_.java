package com.db.entity.lib;

import com.db.entity.lib.RelKriteriaAlternatif;
import com.db.entity.lib.RelKriteriaSiswa;
import com.db.entity.lib.VarKriteria;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-05-01T13:18:22")
@StaticMetamodel(Kriteria.class)
public class Kriteria_ { 

    public static volatile ListAttribute<Kriteria, RelKriteriaAlternatif> relKriteriaAlternatifList;
    public static volatile SingularAttribute<Kriteria, String> keterangan;
    public static volatile SingularAttribute<Kriteria, String> namaKriteria;
    public static volatile ListAttribute<Kriteria, VarKriteria> varKriteriaList;
    public static volatile SingularAttribute<Kriteria, String> idKriteria;
    public static volatile ListAttribute<Kriteria, RelKriteriaSiswa> relKriteriaSiswaList;

}