package com.db.entity.lib;

import com.db.entity.lib.RelAlternatifSiswa;
import com.db.entity.lib.RelKriteriaAlternatif;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-05-01T13:18:22")
@StaticMetamodel(Alternatif.class)
public class Alternatif_ { 

    public static volatile ListAttribute<Alternatif, RelKriteriaAlternatif> relKriteriaAlternatifList;
    public static volatile SingularAttribute<Alternatif, String> keterangan;
    public static volatile SingularAttribute<Alternatif, String> nama;
    public static volatile SingularAttribute<Alternatif, String> idAlternatif;
    public static volatile ListAttribute<Alternatif, RelAlternatifSiswa> relAlternatifSiswaList;

}