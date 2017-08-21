/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.model.lib;

import com.db.entity.lib.Bobot;
import com.db.helper.lib.PersistenceHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class BobotModel extends Bobot{
    EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
    DefaultTableModel model = new DefaultTableModel();
    
    public void showMessage(String msg) {
        javax.swing.JOptionPane.showMessageDialog(null, msg);
    }
    
    public void setTableModel(JTable table) {
        table.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Value 1");
        model.addColumn("Value 2");
        model.addColumn("Value 3");
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
        List<Bobot> ResultList = manager.createNamedQuery("Bobot.findAll").getResultList();
        
        for(Bobot a:ResultList) {
            Object o[] = new Object[5];
            o[1] = a.getNamaBobot();
            o[2] = a.getValue1();
            o[3] = a.getValue2();
            o[4] = a.getValue3();
            o[0] = a.getIdBobot();
            
            model.addRow(o);
        }    
    }
    
    public void getAllData(String query) {
        List<Bobot> ResultList = manager.createNamedQuery(query).getResultList();
        
        for(Bobot a:ResultList) {
            Object o[] = new Object[5];
            o[1] = a.getNamaBobot();
            o[2] = a.getValue1();
            o[3] = a.getValue2();
            o[4] = a.getValue3();
            o[0] = a.getIdBobot();
            model.addRow(o);
        }    
    }
    
}
