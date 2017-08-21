/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui.form.lib;

import com.db.entity.lib.Bobot;
import com.db.entity.lib.Kriteria;
import com.db.entity.lib.RelKriteriaSiswa;
import com.db.entity.lib.Siswa;
import com.db.entity.lib.VarKriteria;
import com.db.helper.lib.PersistenceHelper;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class Add_RatingKepentingan extends javax.swing.JFrame {

    /**
     * Creates new form Add_RatingKepentingan
     */
    String nis = "";
    public Add_RatingKepentingan(String nis) {
        initComponents();
        
        this.nis = nis;
        setTitle("Rating Kepentingan NIS : "+nis);
        setStatusSiswa();
        if(cekRating(nis)) {
            add_rating.setVisible(false);
            pane_kriteria.setVisible(true);
        }else {
            add_rating.setVisible(true);
            pane_kriteria.setVisible(false);
        }
        
        
        setBobot(bobot_listC1,"C1");
        setBobot(bobot_listC2,"C2");
        setBobot(bobot_listC3,"C3");
        setBobot(bobot_listC4,"C4");
        setBobot(bobot_listC5,"C5");
        setBobot(bobot_listC6,"C6");
        setBobot(bobot_listC7,"C7");
        setBobot(bobot_listC8,"C8");
        setBobot(bobot_listC9,"C9");
        
        setSelectedBobot(bobot_listC1,"C1",txt_nis.getText());
        setSelectedBobot(bobot_listC2,"C2",txt_nis.getText());
        setSelectedBobot(bobot_listC3,"C3",txt_nis.getText());
        setSelectedBobot(bobot_listC4,"C4",txt_nis.getText());
        setSelectedBobot(bobot_listC5,"C5",txt_nis.getText());
        setSelectedBobot(bobot_listC6,"C6",txt_nis.getText());
        setSelectedBobot(bobot_listC7,"C7",txt_nis.getText());
        setSelectedBobot(bobot_listC8,"C8",txt_nis.getText());
        setSelectedBobot(bobot_listC9,"C9",txt_nis.getText());
        
        
    }
    
    public Add_RatingKepentingan() {
        initComponents();
        
        this.nis = "1";
        setTitle("Rating Kepentingan NIS : "+nis);
        setStatusSiswa();
        if(cekRating(nis)) {
            add_rating.setVisible(false);
            pane_kriteria.setVisible(true);
        }else {
            add_rating.setVisible(true);
            pane_kriteria.setVisible(false);
        }
        
        setBobot(bobot_listC1,"C1");
        setBobot(bobot_listC2,"C2");
        setBobot(bobot_listC3,"C3");
        setBobot(bobot_listC4,"C4");
        setBobot(bobot_listC5,"C5");
        setBobot(bobot_listC6,"C6");
        setBobot(bobot_listC7,"C7");
        setBobot(bobot_listC8,"C8");
        setBobot(bobot_listC9,"C9");
        
        setSelectedBobot(bobot_listC1,"C1",txt_nis.getText());
        setSelectedBobot(bobot_listC2,"C2",txt_nis.getText());
        setSelectedBobot(bobot_listC3,"C3",txt_nis.getText());
        setSelectedBobot(bobot_listC4,"C4",txt_nis.getText());
        setSelectedBobot(bobot_listC5,"C5",txt_nis.getText());
        setSelectedBobot(bobot_listC6,"C6",txt_nis.getText());
        setSelectedBobot(bobot_listC7,"C7",txt_nis.getText());
        setSelectedBobot(bobot_listC8,"C8",txt_nis.getText());
        setSelectedBobot(bobot_listC9,"C9",txt_nis.getText());
        
        
    }
    
    int stat = 0;
    public void setStat(int stat) {
        this.stat = stat;
    }
    public int getStat() {
        return stat;
    }
   
    EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
    public void setBobot(javax.swing.JComboBox cb, String bobot) {
        DefaultComboBoxModel cbmodel = new DefaultComboBoxModel();
        List<VarKriteria> ResultList = manager.createNamedQuery("VarKriteria.findAll").getResultList();
        
        for(VarKriteria a:ResultList) {
            if(a.getIdKriteria().getIdKriteria().equals(bobot)) {
                cbmodel.addElement(a.getValue1());
                cbmodel.addElement(a.getValue2());
                cbmodel.addElement(a.getValue3());
            }
        }
        cb.setModel(cbmodel);
    }
    
    public void setSelectedBobot(javax.swing.JComboBox cb, String idKriteria, String nis) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a:ResultList) {
            String id = getIdRelKriteriaSiswa(idKriteria,nis);
            if(id.equals(a.getId())) {   
                if(a.getBobot().getIdBobot().equals("L")) {
                    cb.setSelectedIndex(0);
                }else if(a.getBobot().getIdBobot().equals("M")) {
                    cb.setSelectedIndex(1);
                }else if(a.getBobot().getIdBobot().equals("H")) {
                    cb.setSelectedIndex(2);
                }
            }
        }
    }
    
    public void setStatusSiswa() {
        List<Siswa> ResultList = manager.createNamedQuery("Siswa.findAll").getResultList();
        for(Siswa a:ResultList) {
            if(a.getNis().equals(nis) && !nis.equals("")) {
                txt_nis.setText(a.getNis());
                txt_nama.setText(a.getNama());
                txt_kelas.setText(a.getIdKelas().getIdKelas());
            }
        }
    }
    
    public boolean cekRating(String nis) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean cekKriteriaSiswa(String idKriteria, String nis) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis) && a.getIdKriteria().getIdKriteria().equals(idKriteria)) {
                return true;
            }
        }
        return false;
    }
    
    public String getIdRelKriteriaSiswa(String idKriteria, String nis) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis) && a.getIdKriteria().getIdKriteria().equals(idKriteria)) {
                return a.getId();
            }
        }
        return "";
    }
    
    public void removeKriteriaSiswa(String id) {
        EntityManager m = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        m.getTransaction().begin();
        
        RelKriteriaSiswa a = new RelKriteriaSiswa(id);
        RelKriteriaSiswa am = m.find(RelKriteriaSiswa.class, a.getId());
        
        try {
            m.remove(am);
            m.flush();
            m.getTransaction().commit();
        }catch(Exception ex) {
            m.getTransaction().rollback();ex.printStackTrace();
        }
    }
    
    public void saveSettingKriteria(String idKriteria, String nis, int b) {
        EntityManager m = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        m.getTransaction().begin();
        try {
            RelKriteriaSiswa data = new RelKriteriaSiswa();
            data.setId(idKriteria+"_"+nis);
            data.setIdKriteria(new Kriteria(idKriteria));
            data.setNis(new Siswa(nis));
            String bobot = "";
            if(b == 0) {
                bobot = "L";
            }else if(b == 1) {
                bobot = "M";
            }else if(b == 2){
                bobot = "H";
            }
            data.setBobot(new Bobot(bobot));
            if(!cekKriteriaSiswa(idKriteria,nis)) {
                m.persist(data);
                m.getTransaction().commit();
            }else {
                removeKriteriaSiswa(getIdRelKriteriaSiswa(idKriteria,nis));
                m.persist(data);
                m.getTransaction().commit();
            }
        }catch(Exception ex) {
            m.getTransaction().rollback();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jarak_statusBG = new javax.swing.ButtonGroup();
        komunikasi_statusBG = new javax.swing.ButtonGroup();
        paneRatingKep = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nis = new javax.swing.JLabel();
        txt_nama = new javax.swing.JLabel();
        txt_kelas = new javax.swing.JLabel();
        pane_kriteria = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bobot_listC1 = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        bobot_listC2 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        bobot_listC3 = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        bobot_listC6 = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bobot_listC5 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        bobot_listC4 = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        bobot_listC7 = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        bobot_listC8 = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        bobot_listC9 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        add_rating = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Bobot Siswa");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("NIS");

        jLabel2.setText("Nama");

        jLabel3.setText("Kelas");

        jLabel4.setText(":");
        jLabel4.setToolTipText("");

        jLabel5.setText(":");
        jLabel5.setToolTipText("");

        jLabel6.setText(":");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_nis, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txt_nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txt_kelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))
                    .addComponent(txt_nis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_kelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );

        pane_kriteria.setBorder(javax.swing.BorderFactory.createTitledBorder("Kriteria"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("C1 - Jarak Ke Sekolah"));

        jLabel8.setText("Bobot :");

        bobot_listC1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC1, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("C2 - Komunikasi Di Sekolah"));

        jLabel10.setText("Bobot :");

        bobot_listC2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC2, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("C3 - Rata2 Nilai Teori"));

        jLabel11.setText("Bobot :");

        bobot_listC3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC3, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("C6 - Rata2 Nilai Menghafal"));

        jLabel12.setText("Bobot :");

        bobot_listC6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC6, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("C5 - Rata2 Nilai Menghitung"));

        jLabel13.setText("Bobot :");

        bobot_listC5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC5, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("C4 - Rata2 Nilai Praktek"));

        jLabel9.setText("Bobot :");

        bobot_listC4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bobot_listC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bobot_listC4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC4, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("C7 - Sikap Dan Sopan Santun"));

        jLabel15.setText("Bobot :");

        bobot_listC7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bobot_listC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bobot_listC7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC7, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("C8 - Kepercayaan Diri"));

        jLabel16.setText("Bobot :");

        bobot_listC8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC8, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("C9 - Absensi"));

        jLabel17.setText("Bobot :");

        bobot_listC9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bobot_listC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bobot_listC9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bobot_listC9, 0, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bobot_listC9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pane_kriteriaLayout = new javax.swing.GroupLayout(pane_kriteria);
        pane_kriteria.setLayout(pane_kriteriaLayout);
        pane_kriteriaLayout.setHorizontalGroup(
            pane_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_kriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pane_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pane_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane_kriteriaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        pane_kriteriaLayout.setVerticalGroup(
            pane_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane_kriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pane_kriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pane_kriteriaLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pane_kriteriaLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pane_kriteriaLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        add_rating.setText("Add Bobot");
        add_rating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_ratingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneRatingKepLayout = new javax.swing.GroupLayout(paneRatingKep);
        paneRatingKep.setLayout(paneRatingKepLayout);
        paneRatingKepLayout.setHorizontalGroup(
            paneRatingKepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneRatingKepLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneRatingKepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pane_kriteria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(paneRatingKepLayout.createSequentialGroup()
                        .addComponent(add_rating)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        paneRatingKepLayout.setVerticalGroup(
            paneRatingKepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneRatingKepLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_rating, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pane_kriteria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneRatingKep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneRatingKep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_ratingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_ratingActionPerformed
        // TODO add your handling code here:
        add_rating.setVisible(false);
        pane_kriteria.setVisible(true);
    }//GEN-LAST:event_add_ratingActionPerformed

    private void bobot_listC9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bobot_listC9ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_bobot_listC9ActionPerformed

    private void bobot_listC7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bobot_listC7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bobot_listC7ActionPerformed

    private void bobot_listC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bobot_listC4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bobot_listC4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int C1 = bobot_listC1.getSelectedIndex();
        int C2 = bobot_listC2.getSelectedIndex();
        int C3 = bobot_listC3.getSelectedIndex();
        int C4 = bobot_listC4.getSelectedIndex();
        int C5 = bobot_listC5.getSelectedIndex();
        int C6 = bobot_listC6.getSelectedIndex();
        int C7 = bobot_listC7.getSelectedIndex();
        int C8 = bobot_listC8.getSelectedIndex();
        int C9 = bobot_listC9.getSelectedIndex();
        
        saveSettingKriteria("C1",txt_nis.getText(),C1);
        saveSettingKriteria("C2",txt_nis.getText(),C2);
        saveSettingKriteria("C3",txt_nis.getText(),C3);
        saveSettingKriteria("C4",txt_nis.getText(),C4);
        saveSettingKriteria("C5",txt_nis.getText(),C5);
        saveSettingKriteria("C6",txt_nis.getText(),C6);
        saveSettingKriteria("C7",txt_nis.getText(),C7);
        saveSettingKriteria("C8",txt_nis.getText(),C8);
        saveSettingKriteria("C9",txt_nis.getText(),C9);
        setStat(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        LookAndFeel laf = new AeroLookAndFeel();
        try {
            Properties props = new Properties();
            props.put("logoString", "SPK - Masa Depan");
            AeroLookAndFeel.setCurrentTheme(props);
            javax.swing.UIManager.setLookAndFeel(laf);
            
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Form_Siswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_RatingKepentingan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton add_rating;
    private javax.swing.JComboBox bobot_listC1;
    private javax.swing.JComboBox bobot_listC2;
    private javax.swing.JComboBox bobot_listC3;
    private javax.swing.JComboBox bobot_listC4;
    private javax.swing.JComboBox bobot_listC5;
    private javax.swing.JComboBox bobot_listC6;
    private javax.swing.JComboBox bobot_listC7;
    private javax.swing.JComboBox bobot_listC8;
    private javax.swing.JComboBox bobot_listC9;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.ButtonGroup jarak_statusBG;
    private javax.swing.ButtonGroup komunikasi_statusBG;
    public static javax.swing.JPanel paneRatingKep;
    public static javax.swing.JPanel pane_kriteria;
    private javax.swing.JLabel txt_kelas;
    private javax.swing.JLabel txt_nama;
    private javax.swing.JLabel txt_nis;
    // End of variables declaration//GEN-END:variables
}
