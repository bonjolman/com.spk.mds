/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui.lib;

import com.db.entity.lib.Alternatif;
import com.db.entity.lib.RelAlternatifSiswa;
import com.db.entity.lib.RelKriteriaAlternatif;
import com.db.entity.lib.RelKriteriaSiswa;
import com.db.entity.lib.Siswa;
import com.db.helper.lib.PersistenceHelper;
import com.db.model.lib.ConfigModel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.ui.dialog.lib.Dialog_ALT;
import com.ui.form.lib.Add_RatingKecocokan;
import com.ui.form.lib.Add_RatingKepentingan;
import com.ui.form.lib.Form_Siswa;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class SPK extends javax.swing.JFrame {

    /**
     * Creates new form SPK
     */
    String nis = "4";
    Double[] valData;
    String[] titleData;
    EntityManager manager;
    public SPK() {
        setKoneksi("localhost","root","","com.spk.mds2");
        initComponents();
        
        
        setPane(nis);
        this.setTitle("NIS : "+nis);
        
        setStatusSiswa(nis);
        setKriteriaSiswa(nis);
        setTableRatingMasaDepan(nis);
        
    }
    
    public SPK(String nis) {
        this.nis = nis;
        initComponents();
        
        setPane(nis);
        setStatusSiswa(nis);
        setKriteriaSiswa(nis);
        setTableRatingMasaDepan(nis);
    }
    
    public void setPane(String nis) {
        PaneChart pc = new PaneChart();
        titleData = new String[10];
        valData = new Double[10];
        
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                
                switch (a.getIdKriteria().getIdKriteria()) {
                    case "C1":
                        titleData[0] = "C1";
                        valData[0] = a.getBobot().getValue2();
                        
                        break;
                    case "C2":
                        titleData[1] = "C2";
                        valData[1] = a.getBobot().getValue2();
                        break;
                    case "C3":
                        titleData[2] = "C3";
                        valData[2] = a.getBobot().getValue2();
                        break;
                    case "C4":
                        titleData[3] = "C4";
                        valData[3] = a.getBobot().getValue2();
                        break;
                    case "C5":
                        titleData[4] = "C5";
                        valData[4] = a.getBobot().getValue2();
                        break;
                    case "C6":
                        titleData[5] = "C6";
                        valData[5] = a.getBobot().getValue2();
                        break;
                    case "C7":
                        titleData[6] = "C7";
                        valData[6] = a.getBobot().getValue2();
                        break;
                    case "C8":
                        titleData[7] = "C8";
                        valData[7] = a.getBobot().getValue2();
                        break;
                    case "C9":
                        titleData[8] = "C9";
                        valData[8] = a.getBobot().getValue2();
                        break;
                }
                
            }
            
        } 
        JPanel p = pc.getPanel(pane.getSize(),"Rating Bobot Siswa",titleData,valData);
        pane.add(p);
    }
    
    public void setKoneksi(String url,String user,String pass, String db) {
        
        PersistenceHelper.setProperties(url,user,pass,db);
        manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        
    }
    
    public void refresh() {
        setStatusSiswa(nis);
        setKriteriaSiswa(nis);
        setTableRatingMasaDepan(nis);
    }
    
    public void setTableRatingMasaDepan(String nis) {
        
        List<RelAlternatifSiswa> ResultList = manager.createQuery("select r from RelAlternatifSiswa r order by "
                + "r.tidakOptimis asc, r.optimis desc, r.sangatOptimis desc").getResultList();
        Object[] col = {"Alternatif","Nama","C1","C2","C3","C4","C5","C6","C7","C8","C9",
            "Indeks Kecocokan Fuzzy","Tidak Optimis","Optimis","Sangat Optimis"};
        DefaultTableModel model = new DefaultTableModel(null,col);
        ResultList.stream().filter((a) -> (a.getNis().getNis().equals(nis))).map((a) -> {
            Object[] o = new Object[20];
            o[0] = a.getIdAlternatif().getIdAlternatif();
            o[1] = a.getIdAlternatif().getNama();
            o[2] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C1");
            o[3] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C2");
            o[4] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C3");
            o[5] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C4");
            o[6] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C5");
            o[7] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C6");
            o[8] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C7");
            o[9] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C8");
            o[10] = getBobotKecocokan(a.getIdAlternatif().getIdAlternatif(),"C9");
            o[11] = a.getIdxQ()+";  "+a.getIdxY()+";  "+a.getIdxZ();
            o[12] = a.getTidakOptimis();
            o[13] = a.getOptimis();
            o[14] = a.getSangatOptimis();
            return o;
        }).forEach((o) -> {
            model.addRow(o);
        });
        ratingMasaDepan.setModel(model);
        
        ratingMasaDepan.getColumnModel().getColumn(0).setResizable(false);
        ratingMasaDepan.getColumnModel().getColumn(0).setMaxWidth(70);
        for(int i = 2;i <= 10;i++) {
            ratingMasaDepan.getColumnModel().getColumn(i).setResizable(false);
            ratingMasaDepan.getColumnModel().getColumn(i).setMaxWidth(45);
        }
        ratingMasaDepan.setAutoCreateRowSorter(true);
      
    }
    
    public String getBobotKecocokan(String idAlternatif, String idKriteria) {
        
        List<RelKriteriaAlternatif> ResultList = manager.createNamedQuery("RelKriteriaAlternatif.findAll").getResultList();
        for(RelKriteriaAlternatif a: ResultList) {
            if(a.getIdAlternatif().getIdAlternatif().equals(idAlternatif)) {
                if(a.getIdKriteria().getIdKriteria().equals(idKriteria)) {
                    return a.getBobot().getIdBobot();
                }
            }
        }
        return "";
    }
    
    Image image;
    public void setStatusSiswa(String nis) {
        
        List<Siswa> ResultList = manager.createNamedQuery("Siswa.findAll").getResultList();
        ResultList.stream().filter((a) -> (a.getNis().equals(nis))).map((a) -> {
            status_nis.setText(a.getNis());
            return a;
        }).map((a) -> {
            status_nama.setText(a.getNama());
            return a;
        }).map((a) -> {
            status_jenkel.setText(a.getJenkel());
            return a;
        }).map((a) -> {
            ConfigModel cm = new ConfigModel();
            
            File file = new File(cm.getPath("path_image_profile")+a.getNis()+".jpg");
            if (!file.exists()){
                file = new File(cm.getPath("path_image_profile")+"default.jpg");
            }
            try {
                image = ImageIO.read(file);
                panelGambar1.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(SPK.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return a;
        }).forEach((a) -> {
            status_kelas.setText(a.getIdKelas().getNamaKelas());
        });
    }
    
    public void setKriteriaSiswa(String nis) {
        
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a: ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                switch (a.getIdKriteria().getIdKriteria()) {
                    case "C1":
                        c1.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C2":
                        c2.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C3":
                        c3.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C4":
                        c4.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C5":
                        c5.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C6":
                        c6.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C7":
                        c7.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C8":
                        c8.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                    case "C9":
                        c9.setText(a.getBobot().getNamaBobot()+"("+a.getBobot().getValue1()+", "+a.getBobot().getValue2()+", "+a.getBobot().getValue3()+")");
                        break;
                }
            }
        }
    }
    
    public double[] getValueKecocokan(String idAlternatif,String idKriteria) {
        
        List<RelKriteriaAlternatif> ResultList = manager.createNamedQuery("RelKriteriaAlternatif.findAll").getResultList();
        for(RelKriteriaAlternatif a: ResultList) {
            if(a.getIdAlternatif().getIdAlternatif().equals(idAlternatif)) {
                if(a.getIdKriteria().getIdKriteria().equals(idKriteria)) {
                    double[] val = {a.getBobot().getValue1(),a.getBobot().getValue2(),a.getBobot().getValue3()};
                    return val;
                }
            }
        }
        return null;
    }
    
    public double[] getValueKepentingan(String nis,String idKriteria) {
        
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a: ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                if(a.getIdKriteria().getIdKriteria().equals(idKriteria)) {
                    double[] val = {a.getBobot().getValue1(),a.getBobot().getValue2(),a.getBobot().getValue3()};
                    return val;
                }
            }
        }
        return null;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Indeks Kecocokan">    
    public double getValQ(String idAlternatif, String nis) {
        double[] kepC1 = getValueKepentingan(nis,"C1");
        double[] kepC2 = getValueKepentingan(nis,"C2");
        double[] kepC3 = getValueKepentingan(nis,"C3");
        double[] kepC4 = getValueKepentingan(nis,"C4");
        double[] kepC5 = getValueKepentingan(nis,"C5");
        double[] kepC6 = getValueKepentingan(nis,"C6");
        double[] kepC7 = getValueKepentingan(nis,"C7");
        double[] kepC8 = getValueKepentingan(nis,"C8");
        double[] kepC9 = getValueKepentingan(nis,"C9");
        
        double[] kecC1 = getValueKecocokan(idAlternatif,"C1");
        double[] kecC2 = getValueKecocokan(idAlternatif,"C2");
        double[] kecC3 = getValueKecocokan(idAlternatif,"C3");
        double[] kecC4 = getValueKecocokan(idAlternatif,"C4");
        double[] kecC5 = getValueKecocokan(idAlternatif,"C5");
        double[] kecC6 = getValueKecocokan(idAlternatif,"C6");
        double[] kecC7 = getValueKecocokan(idAlternatif,"C7");
        double[] kecC8 = getValueKecocokan(idAlternatif,"C8");
        double[] kecC9 = getValueKecocokan(idAlternatif,"C9");
        
        double val1 = (kepC1[0]*kecC1[0]) + (kepC2[0]*kecC2[0]) + (kepC3[0]*kecC3[0]) + (kepC4[0]*kecC4[0]);
        double val2 = (kepC5[0]*kecC5[0]) + (kepC6[0]*kecC6[0]) + (kepC7[0]*kecC7[0]) + (kepC8[0]*kecC8[0]) + (kepC9[0]*kecC9[0]);
        double val = (val1 + val2);
        return val /= 9;
    }
    
    public double getValY(String idAlternatif, String nis) {
        double[] kepC1 = getValueKepentingan(nis,"C1");
        double[] kepC2 = getValueKepentingan(nis,"C2");
        double[] kepC3 = getValueKepentingan(nis,"C3");
        double[] kepC4 = getValueKepentingan(nis,"C4");
        double[] kepC5 = getValueKepentingan(nis,"C5");
        double[] kepC6 = getValueKepentingan(nis,"C6");
        double[] kepC7 = getValueKepentingan(nis,"C7");
        double[] kepC8 = getValueKepentingan(nis,"C8");
        double[] kepC9 = getValueKepentingan(nis,"C9");
        
        double[] kecC1 = getValueKecocokan(idAlternatif,"C1");
        double[] kecC2 = getValueKecocokan(idAlternatif,"C2");
        double[] kecC3 = getValueKecocokan(idAlternatif,"C3");
        double[] kecC4 = getValueKecocokan(idAlternatif,"C4");
        double[] kecC5 = getValueKecocokan(idAlternatif,"C5");
        double[] kecC6 = getValueKecocokan(idAlternatif,"C6");
        double[] kecC7 = getValueKecocokan(idAlternatif,"C7");
        double[] kecC8 = getValueKecocokan(idAlternatif,"C8");
        double[] kecC9 = getValueKecocokan(idAlternatif,"C9");
        
        double val1 = (kepC1[1]*kecC1[1]) + (kepC2[1]*kecC2[1]) + (kepC3[1]*kecC3[1]) + (kepC4[0]*kecC4[1]);
        double val2 = (kepC5[1]*kecC5[1]) + (kepC6[1]*kecC6[1]) + (kepC7[1]*kecC7[1]) + (kepC8[0]*kecC8[1]) + (kepC9[1]*kecC9[1]);
        double val = (val1 + val2);
        return val /= 9;
    }
    
    public double getValZ(String idAlternatif, String nis) {
        double[] kepC1 = getValueKepentingan(nis,"C1");
        double[] kepC2 = getValueKepentingan(nis,"C2");
        double[] kepC3 = getValueKepentingan(nis,"C3");
        double[] kepC4 = getValueKepentingan(nis,"C4");
        double[] kepC5 = getValueKepentingan(nis,"C5");
        double[] kepC6 = getValueKepentingan(nis,"C6");
        double[] kepC7 = getValueKepentingan(nis,"C7");
        double[] kepC8 = getValueKepentingan(nis,"C8");
        double[] kepC9 = getValueKepentingan(nis,"C9");
        
        double[] kecC1 = getValueKecocokan(idAlternatif,"C1");
        double[] kecC2 = getValueKecocokan(idAlternatif,"C2");
        double[] kecC3 = getValueKecocokan(idAlternatif,"C3");
        double[] kecC4 = getValueKecocokan(idAlternatif,"C4");
        double[] kecC5 = getValueKecocokan(idAlternatif,"C5");
        double[] kecC6 = getValueKecocokan(idAlternatif,"C6");
        double[] kecC7 = getValueKecocokan(idAlternatif,"C7");
        double[] kecC8 = getValueKecocokan(idAlternatif,"C8");
        double[] kecC9 = getValueKecocokan(idAlternatif,"C9");
        
        double val1 = (kepC1[2]*kecC1[2]) + (kepC2[0]*kecC2[2]) + (kepC3[0]*kecC3[2]) + (kepC4[0]*kecC4[2]);
        double val2 = (kepC5[2]*kecC5[2]) + (kepC6[0]*kecC6[2]) + (kepC7[0]*kecC7[2]) + (kepC8[0]*kecC8[2]) + (kepC9[2]*kecC9[2]);
        double val = (val1 + val2);
        return val /= 9;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Optimisasi with integral">    
    public double getTidakOptimis(double a, double b, double c) {
       double mi = 0;
       double val1 = (c * mi) + b;
       double val2 = (1 - mi) * a;
       double val = val1 + val2;
       return val;
    }
    
    public double getOptimis(double a, double b, double c) {
       double mi = 0.5;
       double val1 = (c * mi) + b;
       double val2 = (1 - mi) * a;
       double val = val1 + val2;
       return val;
    }
    
    public double getSangatOptimis(double a, double b, double c) {
       double mi = 1;
       double val1 = (c * mi) + b;
       double val2 = (1 - mi) * a;
       double val = val1 + val2;
       return val;
    }
    //</editor-fold>
    
    public String toString(Double d) {
        if(d.toString().length() > 7) {
            return d.toString().substring(0, 6);
        }
        return d.toString();
    }
    
    public double toDouble(String s) {
        return Double.parseDouble(s);
    }
    
    public double slice(Double d) {
        String s;
        if(d.toString().length() > 7) {
            s = d.toString().substring(0, 6);
        }else {
            s = d.toString();
        }
        
        return Double.parseDouble(s);
    }
    
    public boolean cekAlternatifSiswa(String idAlternatif, String nis) {
        
        List<RelAlternatifSiswa> ResultList = manager.createNamedQuery("RelAlternatifSiswa.findAll").getResultList();
        return ResultList.stream().anyMatch((a) -> (a.getNis().getNis().equals(nis) && a.getIdAlternatif().getIdAlternatif().equals(idAlternatif)));
    }
    
    public String getIdRelAlternatifSiswa(String idAlternatif, String nis) {
        
        List<RelAlternatifSiswa> ResultList = manager.createNamedQuery("RelAlternatifSiswa.findAll").getResultList();
        for(RelAlternatifSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis) && a.getIdAlternatif().getIdAlternatif().equals(idAlternatif)) {
                return a.getId();
            }
        }
        return "";
    }
    
    public void removeAlternatifSiswa(String id) {
        EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        manager.getTransaction().begin();
        RelAlternatifSiswa a = new RelAlternatifSiswa(id);
        RelAlternatifSiswa am = manager.find(RelAlternatifSiswa.class, a.getId());
        
        try {
            manager.remove(am);
            manager.flush();
            manager.getTransaction().commit();
        }catch(Exception ex) {  
            manager.getTransaction().rollback();
        }
    }
    
    public void saveSettingAlternatif(String idAlternatif, String nis) {
        EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        manager.getTransaction().begin();
        try {
            RelAlternatifSiswa data = new RelAlternatifSiswa();
            data.setId(idAlternatif+"_"+nis);
            data.setIdAlternatif(new Alternatif(idAlternatif));
            data.setNis(new Siswa(nis));
            
            double q = getValQ(idAlternatif,nis);
            double y = getValY(idAlternatif,nis);
            double z = getValZ(idAlternatif,nis);
            data.setIdxQ(slice(q));
            data.setIdxY(slice(y));
            data.setIdxZ(slice(z));
            
            data.setTidakOptimis(slice(this.getTidakOptimis(q,y,z)));
            data.setOptimis(slice(this.getOptimis(q,y,z)));
            data.setSangatOptimis(slice(this.getSangatOptimis(q,y,z)));
            if(!cekAlternatifSiswa(idAlternatif,nis)) {
                System.out.println(idAlternatif+" - "+nis);
                manager.persist(data);
                manager.getTransaction().commit();
            }else {
                removeAlternatifSiswa(getIdRelAlternatifSiswa(idAlternatif,nis));
                manager.persist(data);
                manager.getTransaction().commit();
            }
            
        }catch(Exception ex) {
            manager.getTransaction().rollback();
        }finally {
            manager.close();
        }
    }
    
    public boolean copyFile(File tFile, File cFile) {
        
        try {
            FileInputStream in = new FileInputStream(tFile);
            FileOutputStream out = new FileOutputStream(cFile);
            byte[] buffer = new byte[4096];
            int byteRead;
            while((byteRead = in.read(buffer)) != -1) {
                out.write(buffer,0,byteRead);
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SPK.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(SPK.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        status_nis = new javax.swing.JLabel();
        status_nama = new javax.swing.JLabel();
        status_jenkel = new javax.swing.JLabel();
        status_kelas = new javax.swing.JLabel();
        panelGambar1 = new com.ui.lib.PanelGambar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ratingMasaDepan = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        paneKriteria = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        c1 = new javax.swing.JLabel();
        c2 = new javax.swing.JLabel();
        c3 = new javax.swing.JLabel();
        c4 = new javax.swing.JLabel();
        c5 = new javax.swing.JLabel();
        c6 = new javax.swing.JLabel();
        c7 = new javax.swing.JLabel();
        c8 = new javax.swing.JLabel();
        c9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pane = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistem Pendukung Keputusan");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1366, 720));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Sistem Pendukung Keputusan Masa Depan Siswa");

        jLabel3.setText("NIS");

        jLabel4.setText("Nama");

        jLabel5.setText("Jenis Kelamin");

        jLabel6.setText(":");

        jLabel7.setText(":");

        jLabel8.setText(":");

        jLabel9.setText("Kelas");

        jLabel10.setText(":");

        panelGambar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelGambar1.setMaximumSize(new java.awt.Dimension(120, 126));
        panelGambar1.setName(""); // NOI18N
        panelGambar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelGambar1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelGambar1Layout = new javax.swing.GroupLayout(panelGambar1);
        panelGambar1.setLayout(panelGambar1Layout);
        panelGambar1Layout.setHorizontalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        panelGambar1Layout.setVerticalGroup(
            panelGambar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelGambar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(status_kelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(status_jenkel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(status_nis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jSeparator1))
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_nis, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_jenkel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_kelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)))
                        .addGap(63, 79, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelGambar1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        ratingMasaDepan.setAutoCreateRowSorter(true);
        ratingMasaDepan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "null", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ratingMasaDepan.getTableHeader().setReorderingAllowed(false);
        ratingMasaDepan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ratingMasaDepanKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ratingMasaDepan);
        if (ratingMasaDepan.getColumnModel().getColumnCount() > 0) {
            ratingMasaDepan.getColumnModel().getColumn(0).setResizable(false);
            ratingMasaDepan.getColumnModel().getColumn(0).setPreferredWidth(30);
            ratingMasaDepan.getColumnModel().getColumn(1).setResizable(false);
            ratingMasaDepan.getColumnModel().getColumn(1).setPreferredWidth(60);
            ratingMasaDepan.getColumnModel().getColumn(2).setResizable(false);
            ratingMasaDepan.getColumnModel().getColumn(2).setPreferredWidth(500);
        }

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Keterangan :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("C : Kriteria");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("L : Low");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("M : Medium");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("H : High");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(40, 40, 40))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 77, Short.MAX_VALUE)))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 255));
        jLabel11.setText("C1 - Jarak Ke Sekolah");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setText("C2 - Komunikasi Di Sekolah");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 0));
        jLabel13.setText("C3 - Rata2 Nilai Teori");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("C4 - Rata2 Nilai Praktek");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("C5 - Rata2 Nilai Menghitung");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("C6 - Rata2 Nilai Menghafal");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("C7 - Sikap Dan Sopan Santun");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("C8 - Kepercayaan Diri");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("C9 - Absensi");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 255));
        jLabel20.setText(":");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText(":");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 204, 0));
        jLabel22.setText(":");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText(":");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText(":");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText(":");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText(":");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText(":");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText(":");

        c1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c1.setForeground(new java.awt.Color(51, 51, 255));
        c1.setText("jLabel29");

        c2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c2.setForeground(new java.awt.Color(0, 102, 102));
        c2.setText("jLabel30");

        c3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c3.setForeground(new java.awt.Color(0, 204, 0));
        c3.setText("jLabel31");

        c4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c4.setText("jLabel32");

        c5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c5.setText("jLabel33");

        c6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c6.setText("jLabel34");

        c7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c7.setText("jLabel35");

        c8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c8.setText("jLabel36");

        c9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        c9.setText("jLabel37");

        javax.swing.GroupLayout paneKriteriaLayout = new javax.swing.GroupLayout(paneKriteria);
        paneKriteria.setLayout(paneKriteriaLayout);
        paneKriteriaLayout.setHorizontalGroup(
            paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneKriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paneKriteriaLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneKriteriaLayout.setVerticalGroup(
            paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneKriteriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneKriteriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel28)
                    .addComponent(c9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-16.png"))); // NOI18N
        jButton2.setText("Set Bobot Alternatif");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-16.png"))); // NOI18N
        jButton4.setText("Set Bobot Siswa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(paneKriteria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(0, 0, 0)
                .addComponent(paneKriteria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Tabel Rating Masa Depan");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-30.png"))); // NOI18N
        jButton1.setText("Set Rating");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Refresh_30.png"))); // NOI18N
        jButton3.setText("Refresh (F5)");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleDescription("SPK - Masa Depan Siswa");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        manager.getTransaction().begin();
        List<Alternatif> ResultList = manager.createNamedQuery("Alternatif.findAll").getResultList();
        ResultList.stream().forEach((a) -> {
            saveSettingAlternatif(a.getIdAlternatif(),nis);
            
        });
        setTableRatingMasaDepan(nis);        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Dialog_ALT alt = new Dialog_ALT(this,true);
        alt.setVisible(true);
        if(!alt.getIdAlternatif().equals(""))  {
            Add_RatingKecocokan ar = new Add_RatingKecocokan(alt.getIdAlternatif());
            ar.setVisible(true);
            if(ar.getStat() == 1) {
                refresh();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Add_RatingKepentingan ar = new Add_RatingKepentingan(nis);
        ar.setVisible(true);
        if(ar.getStat() == 1) {
            refresh();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ratingMasaDepanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ratingMasaDepanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_F5) {
            refresh();
        }
    }//GEN-LAST:event_ratingMasaDepanKeyPressed

    private void panelGambar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGambar1MousePressed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|png|bmp", "jpg","png","bmp"));

        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            try {
                File nFile=chooser.getSelectedFile();
                File temp = new File(nFile+".temp");
                File file = new File(new ConfigModel().getPath("path_image_profile")+nis+".jpg");
                
               
                if(file.exists()) {
                    file.delete();
                    System.out.println("DELETE OLD FILE "+file);
                    if(copyFile(nFile,file)) {
                        System.out.println("UPLOAD FILE ("+file+")");
                    }
                }else {
                    if(copyFile(nFile,file)) {
                        System.out.println("UPLOAD FILE ("+file+")");
                    }
                }
                System.out.println("CREATE NEW FILE "+file);
            
                image = ImageIO.read(file);
                panelGambar1.setImage(image);
                
            } catch (IOException ex) {
                Logger.getLogger(Form_Siswa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_panelGambar1MousePressed

    
    
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
        java.awt.EventQueue.invokeLater(() -> {
            new SPK().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel c1;
    private javax.swing.JLabel c2;
    private javax.swing.JLabel c3;
    private javax.swing.JLabel c4;
    private javax.swing.JLabel c5;
    private javax.swing.JLabel c6;
    private javax.swing.JLabel c7;
    private javax.swing.JLabel c8;
    private javax.swing.JLabel c9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pane;
    private javax.swing.JPanel paneKriteria;
    private com.ui.lib.PanelGambar panelGambar1;
    private javax.swing.JTable ratingMasaDepan;
    private javax.swing.JLabel status_jenkel;
    private javax.swing.JLabel status_kelas;
    private javax.swing.JLabel status_nama;
    private javax.swing.JLabel status_nis;
    // End of variables declaration//GEN-END:variables
}
