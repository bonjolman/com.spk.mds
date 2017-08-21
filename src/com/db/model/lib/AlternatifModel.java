/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.model.lib;

import com.db.entity.lib.Alternatif;
import com.db.helper.lib.PersistenceHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class AlternatifModel extends Alternatif {
    EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
    DefaultTableModel model = new DefaultTableModel();
    
    public void showMessage(String msg) {
        javax.swing.JOptionPane.showMessageDialog(null, msg);
    }
    
    public void setTableModel(JTable table) {
        table.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Keterangan");   
    }
    
    public void insertData(Object data) {
        manager.getTransaction().begin();
        try {
            manager.persist(data);
            manager.getTransaction().commit();
            showMessage("Success insert into Table Alternatif");
        }catch(Exception ex) {
            manager.getTransaction().rollback();
            showMessage(ex.getMessage());
        }finally {
            manager.close();
        }
    }
    
    public void removeData(String id) {
        manager.getTransaction().begin();
        
        Alternatif a = new Alternatif(id);
        Alternatif am = manager.find(Alternatif.class, a.getIdAlternatif());
        
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
        List<Alternatif> ResultList = manager.createNamedQuery("Alternatif.findAll").getResultList();
        
        for(Alternatif a:ResultList) {
            Object o[] = new Object[3];
            o[1] = a.getNama();
            o[2] = a.getKeterangan();
            o[0] = a.getIdAlternatif();
            
            model.addRow(o);
        }    
    }
    
    public void getAllData(String query) {
        List<Alternatif> ResultList = manager.createNamedQuery(query).getResultList();
        
        for(Alternatif a:ResultList) {
            Object o[] = new Object[3];
            o[1] = a.getNama();
            o[2] = a.getKeterangan();
            o[0] = a.getIdAlternatif();
            model.addRow(o);
        }    
    }
    
}
