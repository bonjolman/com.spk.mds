/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.model.lib;

import com.db.entity.lib.Bobot;
import com.db.entity.lib.RelKriteriaSiswa;
import com.db.helper.lib.PersistenceHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class RatingKepentinganModel extends RelKriteriaSiswa{
    EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
    DefaultTableModel model = new DefaultTableModel();
    
    String title = "";
    public void setTitle(String nis) {
        this.title =  "Table Rating Kepentingan NIS : "+nis;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    
    public void showMessage(String msg) {
        javax.swing.JOptionPane.showMessageDialog(null, msg);
    }
    
    public void setTableModel(JTable table) {
        table.setModel(model);
        model.addColumn("Kriteria");
        model.addColumn("Rating Kepentingan");
    }
    
    public void insertData(Object data) {
        manager.getTransaction().begin();
        try {
            manager.persist(data);
            manager.getTransaction().commit();
            showMessage("Success insert into Table Bobot");
        }catch(Exception ex) {
            manager.getTransaction().rollback();
            showMessage(ex.getMessage());
        }finally {
            manager.close();
        }
    }
    
    public void removeData(String id) {
        manager.getTransaction().begin();
        
        Bobot a = new Bobot(id);
        Bobot am = manager.find(Bobot.class, a.getIdBobot());
        
        try {
            manager.remove(am);
            manager.flush();
            manager.getTransaction().commit();
        }catch(Exception ex) {
            System.out.println(ex);
            manager.getTransaction().rollback();
        }
    }
    
    public void refreshData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
       
    }
    
    public void getAllData() {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        
        for(RelKriteriaSiswa a:ResultList) {
            Object o[] = new Object[5];
            o[0] = a.getIdKriteria().getIdKriteria();
            o[1] = a.getBobot().getNamaBobot();
            setTitle(a.getNis().getNis());
            model.addRow(o);
        }    
    }
    
    public boolean checkNis(String nis) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                return true;
            }
        }
        return false;
    }
    
    public void getDataByNis(String nis) {
        
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                Object o[] = new Object[5];
                o[0] = a.getIdKriteria().getIdKriteria();
                o[1] = a.getBobot().getNamaBobot();
                setTitle(a.getNis().getNis());
                model.addRow(o);
            }
        }    
    }
    
    public String getIdByNis(String nis) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery("RelKriteriaSiswa.findAll").getResultList();
        
        for(RelKriteriaSiswa a:ResultList) {
            if(a.getNis().getNis().equals(nis)) {
                return a.getId();
            }
        }
        return "";
    }
    
    public void getAllData(String query) {
        List<RelKriteriaSiswa> ResultList = manager.createNamedQuery(query).getResultList();
        
        for(RelKriteriaSiswa a:ResultList) {
            Object o[] = new Object[5];
            o[0] = a.getIdKriteria().getIdKriteria();
            o[1] = a.getBobot().getNamaBobot();
            model.addRow(o);
        }    
    }
}
