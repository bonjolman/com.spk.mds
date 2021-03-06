/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui.data.lib;

import com.db.model.lib.RatingKecocokanModel;
import com.db.model.lib.RatingKepentinganModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class Rating_Kecocokan extends javax.swing.JFrame {

    /**
     * Creates new form Rating_Kepentingan
     */
    DefaultTableModel model;
    public static int countData;
    RatingKecocokanModel ratingKecocokanModel = new RatingKecocokanModel();
    
    String id = "A1";
    public Rating_Kecocokan() {
        initComponents();
        ratingKecocokanModel.setTableModel(rating_kecocokan);
        ratingKecocokanModel.refreshData();
        ratingKecocokanModel.getDataById(id);
        setTitle(ratingKecocokanModel.getTitle());
    }
    
    public Rating_Kecocokan(String id) {
        initComponents();
        ratingKecocokanModel.setTableModel(rating_kecocokan);
        ratingKecocokanModel.refreshData();
        ratingKecocokanModel.getDataById(id);
        setTitle(ratingKecocokanModel.getTitle());
        rating_kecocokan.setAutoCreateRowSorter(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsp_ratingKec = new javax.swing.JScrollPane();
        rating_kecocokan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        rating_kecocokan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rating_kecocokan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rating_kecocokanMouseClicked(evt);
            }
        });
        jsp_ratingKec.setViewportView(rating_kecocokan);
        if (rating_kecocokan.getColumnModel().getColumnCount() > 0) {
            rating_kecocokan.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jsp_ratingKec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jsp_ratingKec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rating_kecocokanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rating_kecocokanMouseClicked
        // TODO add your handling code here:

        String id = rating_kecocokan.getValueAt(rating_kecocokan.getSelectedRow(), 0).toString();
        System.out.println(id);
    }//GEN-LAST:event_rating_kecocokanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Rating_Kecocokan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rating_Kecocokan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rating_Kecocokan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rating_Kecocokan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Rating_Kecocokan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JScrollPane jsp_ratingKec;
    private javax.swing.JTable rating_kecocokan;
    // End of variables declaration//GEN-END:variables
}
