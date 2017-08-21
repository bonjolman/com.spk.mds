/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.model.lib;

import com.db.entity.lib.Kelas;
import com.db.entity.lib.Siswa;
import com.db.helper.lib.PersistenceHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class SiswaModel extends Siswa{
    
    EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
    DefaultTableModel model = new DefaultTableModel();
    
    
    
    public void showMessage(String msg) {
        javax.swing.JOptionPane.showMessageDialog(null, msg);
    }
    
    public void setTableModel(JTable table) {
        table.setModel(model);
        model.addColumn("Nis");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Kelas");
        model.addColumn("Tahun Masuk");
        model.addColumn("Status");
    }
    
    public void insertData(Object data) {
        manager.getTransaction().begin();
        try {
            manager.persist(data);
            manager.getTransaction().commit();
            showMessage("Success insert into Table Siswa");
        }catch(Exception ex) {
            manager.getTransaction().rollback();
            showMessage(ex.getMessage());
        }finally {
            manager.close();
        }
    }
    
    public void removeData(String id) {
        manager.getTransaction().begin();
        
        Siswa a = new Siswa(id);
        Siswa am = manager.find(Siswa.class, a.getNis());
        
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
        List<Siswa> ResultList = manager.createNamedQuery("Siswa.findAll").getResultList();
        for(Siswa a:ResultList) {
            Object o[] = new Object[7];
            o[1] = a.getNama();
            o[2] = a.getJenkel();
            o[3] = a.getAlamat();
            o[4] = a.getIdKelas().getIdKelas();
            o[5] = a.getTahunMasuk();
            o[6] = a.getStatus();
            o[0] = a.getNis();
            model.addRow(o);
            
        }    
    }
    
    public void getAllData(String query) {
        List<Siswa> ResultList = manager.createNamedQuery(query).getResultList();
        
        for(Siswa a:ResultList) {
            Object o[] = new Object[3];
            o[1] = a.getNama();
            o[2] = a.getJenkel();
            o[0] = a.getNis();
            model.addRow(o);
        }    
    }
    
}
