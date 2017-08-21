/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui.lib;

import com.db.helper.lib.PersistenceHelper;
import com.db.model.lib.ConfigModel;
import com.img.lib.ImageIndex;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aero.AeroToolBarUI;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.ui.data.lib.Data_Alternatif;
import com.ui.data.lib.Data_Bobot;
import com.ui.data.lib.Data_Kriteria;
import com.ui.data.lib.Data_Siswa;
import com.ui.dialog.lib.Dialog_ALT;
import com.ui.dialog.lib.Dialog_NIS;
import com.ui.dialog.lib.ServerDialog;
import com.ui.form.lib.Add_RatingKecocokan;
import com.ui.form.lib.Add_RatingKepentingan;
import com.ui.form.lib.Form_Siswa;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Beans;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.LookAndFeel;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Bonjolman
 */
public final class Index extends javax.swing.JFrame {

    /**
     * Creates new form Index
     */
    
    EntityManager manager;
    public Index() {
        initComponents();

        setProgress(pb_main);
        timer.start();
        ConfigModel cm = new ConfigModel();
        
        // set jframe icon
        ImageIcon icon = new ImageIcon(ImageIndex.class.getResource("User Male-16.png"));
        setIconImage(icon.getImage());
       
        
        try {
            setKoneksi(cm.getConfig("mysql_host")+":"+cm.getConfig("mysql_port"),
                    cm.getConfig("mysql_user"),cm.getConfig("mysql_pass"), cm.getConfig("mysql_db"));
        }catch(Exception e) {
            this.setVisible(false);
            JOptionPane.showMessageDialog(null, "Not Connected to Server");
            new ServerDialog(this, true).setVisible(true);
            
        }finally {
            
        }
    }
    
    public Index(EntityManager manager) {
        initComponents();

        setProgress(pb_main);
        timer.start();
        ConfigModel cm = new ConfigModel();
        
        this.manager = manager;
        // set jframe icon
        ImageIcon icon = new ImageIcon(ImageIndex.class.getResource("User Male-16.png"));
        setIconImage(icon.getImage());
       
        
        
    }
    
    public void showServerDialog(JFrame parent) {
        JDialog dg = new ServerDialog(parent, true);
            
        ImageIcon icD = new ImageIcon(ImageIndex.class.getResource("Settings-16.png"));
        dg.setIconImage(icD.getImage());
        dg.setTitle("Configuration Server");
        dg.setVisible(true);
    }
   
 

    public boolean cekKoneksi() {
        if(manager == null) {
            return false;
        }
        return true;
    }
    
    public void setKoneksi(String url,String user,String pass, String db) {
        
        PersistenceHelper.setProperties(url,user,pass,db);
        manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        
        
    }
    
    
    public static int countData = 50;
    public static Timer timer;
    public static int size;
    public static int time;
    int rec = 0;
    int limitRec = 20;
    
    String msg = "";
    public void setMessage(String msg) {
        this.msg = msg;
    }
    
    public String getMessage() {
        return this.msg;
    }
    
    public void setProgress(JProgressBar pb) {
        size = countData;
        timer = new Timer(size, new ActionListener() {
        
            public void actionPerformed(ActionEvent evt) {
                time++;
                pb.setValue(time);
                pb.setStringPainted(true);
                
                if(time == 50) {
                    timer.setDelay(10);
                    timer.start();
                }else if(time == 100) {
                    timer.stop();
                }
                
                if(pb.getPercentComplete() == 1) {
                    
                    
                    if(cekKoneksi()) {
                        pb.setString("Connected");
                        setMessage("Connecting Successfully...");
                    }else {
                        pb.setString("Reconnecting Failed....!!!!");
                        
                        setMessage("Please check connection to server!!!!");
                    }
                    notif.setText(getMessage());
                }
            }
                
        });
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        contentPane = new javax.swing.JPanel();
        panelBackground1 = new com.ui.lib.PanelBackground();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        pb_main = new javax.swing.JProgressBar();
        notif = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        tb_newData = new javax.swing.JButton();
        tb_openData = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        m_formSiswa = new javax.swing.JMenuItem();
        m_dataSiswa = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        set_ratingKep = new javax.swing.JMenuItem();
        set_ratingKec = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Aplikasi Sistem Pendukung Keputusan - Masa Depan Siswa");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        contentPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        contentPane.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 339, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
            .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
            .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("SPK - Masa Depan Siswa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(notif, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pb_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pb_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(notif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        jToolBar1.setRollover(true);

        tb_newData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/File-16.png"))); // NOI18N
        tb_newData.setToolTipText("New Data");
        tb_newData.setFocusable(false);
        tb_newData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_newData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_newData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_newDataActionPerformed(evt);
            }
        });
        jToolBar1.add(tb_newData);

        tb_openData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Open Folder-16.png"))); // NOI18N
        tb_openData.setToolTipText("Open Data");
        tb_openData.setFocusable(false);
        tb_openData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tb_openData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tb_openData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_openDataActionPerformed(evt);
            }
        });
        jToolBar1.add(tb_openData);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jMenu1.setText("File");

        m_formSiswa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        m_formSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/File-16.png"))); // NOI18N
        m_formSiswa.setText("New Data...");
        m_formSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_formSiswaActionPerformed(evt);
            }
        });
        jMenu1.add(m_formSiswa);

        m_dataSiswa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        m_dataSiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Open Folder-16.png"))); // NOI18N
        m_dataSiswa.setText("New Data...");
        m_dataSiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_dataSiswaActionPerformed(evt);
            }
        });
        jMenu1.add(m_dataSiswa);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Edit");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Contacts-16.png"))); // NOI18N
        jMenuItem1.setText("SPK");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-16.png"))); // NOI18N
        jMenu2.setText("Setting");

        set_ratingKep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-16.png"))); // NOI18N
        set_ratingKep.setText("Rating Kepentingan");
        set_ratingKep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_ratingKepActionPerformed(evt);
            }
        });
        jMenu2.add(set_ratingKep);

        set_ratingKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-16.png"))); // NOI18N
        set_ratingKec.setText("Rating Kecocokan");
        set_ratingKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_ratingKecActionPerformed(evt);
            }
        });
        jMenu2.add(set_ratingKec);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/lib/Settings-16.png"))); // NOI18N
        jMenuItem2.setText("Server");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void m_formSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_formSiswaActionPerformed
        // TODO add your handling code here:
        Form_Siswa fs = new Form_Siswa();
        ImageIcon icD = new ImageIcon(ImageIndex.class.getResource("File-16.png"));
        fs.setIconImage(icD.getImage());
        fs.setTitle("Form New File Siswa");
        fs.setVisible(true);
        
    }//GEN-LAST:event_m_formSiswaActionPerformed

    private void set_ratingKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_ratingKecActionPerformed
        // TODO add your handling code here:
        Dialog_ALT dalt = new Dialog_ALT(this,true);
        ImageIcon icD = new ImageIcon(ImageIndex.class.getResource("Settings-16.png"));
        dalt.setIconImage(icD.getImage());
        dalt.setTitle("ID Alternatif - Setting Bobot Masa Depan");
        dalt.setVisible(true);
        String id = dalt.getIdAlternatif();
        this.setTitle("ID ALternatif : "+id);
        
        
        Add_RatingKecocokan add = new Add_RatingKecocokan(id);
        add.setVisible(true);
        add.setIconImage(icD.getImage());
    }//GEN-LAST:event_set_ratingKecActionPerformed

    private void set_ratingKepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_ratingKepActionPerformed
        // TODO add your handling code here:
        Dialog_NIS dnis = new Dialog_NIS(this,true);
        ImageIcon icD = new ImageIcon(ImageIndex.class.getResource("Settings-16.png"));
        dnis.setIconImage(icD.getImage());
        dnis.setTitle("NIS - Setting Bobot Siswa");
        dnis.setVisible(true);
        String vnis = dnis.getNis();
        this.setTitle("NIS : "+vnis);
        
        Add_RatingKepentingan add = new Add_RatingKepentingan(vnis);
        add.setVisible(true);
        add.setIconImage(icD.getImage());
    }//GEN-LAST:event_set_ratingKepActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int a = JOptionPane.showOptionDialog
        (new JFrame(), "Kamu yakin ingi keluar aplikasi ?", "Keluar", 
                JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION,null,null,null);
        if(a == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Dialog_NIS dnis = new Dialog_NIS(this,true);
        ImageIcon icD = new ImageIcon(ImageIndex.class.getResource("User Male-16.png"));
        dnis.setIconImage(icD.getImage());
        dnis.setTitle("No Induk Siswa - SPK");
        dnis.setVisible(true);
        String vnis = dnis.getNis();
        
        SPK spk = new SPK(vnis);
        ImageIcon i = new ImageIcon(ImageIndex.class.getResource("Contacts-16.png"));
        spk.setIconImage(i.getImage());
        spk.setTitle("SPK - Masa Depan Siswa NIS : "+vnis);
        spk.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void tb_openDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_openDataActionPerformed
        // TODO add your handling code here:
        contentPane.removeAll();
        Data_Siswa ds = new Data_Siswa();
        Component pane = Data_Siswa.jsp_siswa;
        pane.setSize(contentPane.getWidth(), contentPane.getHeight());
        contentPane.add(pane);
    }//GEN-LAST:event_tb_openDataActionPerformed

    private void tb_newDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_newDataActionPerformed
        // TODO add your handling code here:
        new Form_Siswa().setVisible(true);
    }//GEN-LAST:event_tb_newDataActionPerformed

    private void m_dataSiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_dataSiswaActionPerformed
        // TODO add your handling code here:
        contentPane.removeAll();
        Data_Siswa ds = new Data_Siswa();
        Component pane = Data_Siswa.jsp_siswa;
        pane.setSize(contentPane.getWidth(), contentPane.getHeight());
        contentPane.add(pane);
    }//GEN-LAST:event_m_dataSiswaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        showServerDialog(this);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel contentPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem m_dataSiswa;
    private javax.swing.JMenuItem m_formSiswa;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel notif;
    private com.ui.lib.PanelBackground panelBackground1;
    public static javax.swing.JProgressBar pb_main;
    private javax.swing.JMenuItem set_ratingKec;
    private javax.swing.JMenuItem set_ratingKep;
    private javax.swing.JButton tb_newData;
    private javax.swing.JButton tb_openData;
    // End of variables declaration//GEN-END:variables
    

}
