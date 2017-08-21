/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.control.lib;

import com.db.entity.lib.Bobot;
import com.db.entity.lib.Kriteria;
import com.db.entity.lib.RelKriteriaSiswa;
import com.db.helper.lib.PersistenceHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class ControlFuzzy {
    
    EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
    DefaultTableModel model = new DefaultTableModel();
    
    double Q;
    double Y;
    double Z;
    
    double[] bobot_alt_jarak = {0,0.25,0.5};
    double[] bobot_alt_komunikasi = {0,0.25,0.5};
    double[] bobot_alt_mat_teori = {0.25,0.5,1};
    double[] bobot_alt_mat_praktek = {0,0.25,0.5};
    double[] bobot_alt_mat_hitung = {0.25,0.5,1};
    double[] bobot_alt_mat_hafal = {0,0.25,0.5};
    double[] bobot_alt_sikap = {0.25,0.5,1};;
    double[] bobot_alt_pd = {0,0.25,0.5};
    double[] bobot_alt_absensi = {0,0.25,0.5};
    
    double[] bobot_jarak = {0,0.25,0.5};
    double[] bobot_komunikasi = {0,0.25,0.5};
    double[] bobot_nilai_teori = {0.25,0.5,1};
    double[] bobot_nilai_praktek = {0,0.25,0.5};
    double[] bobot_mat_hitung = {0,0.25,0.5};
    double[] bobot_mat_hafal = {0.25,0.5,0.1};
    double[] bobot_sikap = {0,0.25,0.5};;
    double[] bobot_pd = {0.25,0.5,1};
    double[] bobot_absensi = {0,0.25,1};
    
    double[][] bobot_kepentingan = 
    {bobot_alt_jarak,bobot_alt_komunikasi,bobot_alt_mat_teori,bobot_alt_mat_praktek,bobot_alt_mat_hitung,bobot_alt_mat_hafal,
    bobot_alt_sikap,bobot_alt_pd,bobot_alt_absensi};
    double[][] bobot_kecocokan =
    {bobot_jarak,bobot_komunikasi,bobot_nilai_teori,bobot_nilai_praktek,bobot_mat_hitung,bobot_mat_hafal,
    bobot_sikap,bobot_pd,bobot_absensi};
   
 
    
    
    public ControlFuzzy() {
        getData();
        print("bobot_jarak = "+bobot_jarak[0]+", "+bobot_jarak[1]+", "+bobot_jarak[2]);
        print("bobot_komunikasi = "+bobot_komunikasi[0]+", "+bobot_komunikasi[1]+", "+bobot_komunikasi[2]);
        print("bobot_nilai_teori = "+bobot_nilai_teori[0]+", "+bobot_nilai_teori[1]+", "+bobot_nilai_teori[2]);
        print("bobot_nilai_praktek = "+bobot_nilai_praktek[0]+", "+bobot_nilai_praktek[1]+", "+bobot_nilai_praktek[2]);
    }
    
    public ControlFuzzy(double[][] a, double[][] b, int c) {
        insertData(a,b,c);
    }
    
    public void getData() {
        List<Kriteria> ResultList = manager.createNamedQuery("Kriteria.findAll").getResultList();
        
        
    }
    
    public void insertData(double[][] bobot_kepentingan,double[][] bobot_kecocokan,int k) {
        double Q = getQ(bobot_kepentingan,bobot_kecocokan,k);
        double Y = getY(bobot_kepentingan,bobot_kecocokan,k);
        double Z = getZ(bobot_kepentingan,bobot_kecocokan,k);
        System.out.println("Q = "+Q);
        System.out.println("Y = "+Y);
        System.out.println("Z = "+Z);
        
        print("");
        
        print("0 = "+selectionAlternatif_TIDAKOPTIMIS(Q,Y,Z));
        print("0.5 = "+selectionAlternatif_OPTIMIS(Q,Y,Z));
        print("1 = "+selectionAlternatif_SANGATOPTIMIS(Q,Y,Z));
        
        saveData(Q,Y,Z);
    }
    
    public void saveData(double val1,double val2,double val3) {
        double[][] data = {{0.0,0.0,0.0}};
        if(data.length > 1) {
            for(int i = 0;i < data.length;i++) {
                for(int j = 0;j < data[i].length;j++) {
                    print(data[i][j]);
                }
            }
        }else {
            data[0][0] = val1;
            data[0][1] = val2;
            data[0][2] = val3;
            print(""+data[0][0]+","+data[0][1]+","+data[0][2]);
        }
    }
    
    void print(Object msg) {
        System.out.println(msg);
    }
    
    
    double hitBobot(double a, double b) {
        return a * b;
    }
    
    public double getQ(double[][] bobotKepentingan,double[][] bobotKecocokan,int k) {
        double subs = 
                (
                hitBobot(bobotKepentingan[0][0],bobotKecocokan[0][0])+
                hitBobot(bobotKepentingan[1][0],bobotKecocokan[1][0])+
                hitBobot(bobotKepentingan[2][0],bobotKecocokan[2][0])+
                hitBobot(bobotKepentingan[3][0],bobotKecocokan[3][0])+
                hitBobot(bobotKepentingan[4][0],bobotKecocokan[4][0])+
                hitBobot(bobotKepentingan[5][0],bobotKecocokan[5][0])+
                hitBobot(bobotKepentingan[6][0],bobotKecocokan[6][0])+
                hitBobot(bobotKepentingan[7][0],bobotKecocokan[7][0])+
                hitBobot(bobotKepentingan[8][0],bobotKecocokan[8][0])
                ) / k
                
                ;
        
        return subs;
    }
    
    public double getY(double[][] bobotKepentingan,double[][] bobotKecocokan,int k) {
        double subs = 
                (
                hitBobot(bobotKepentingan[0][1],bobotKecocokan[0][1])+
                hitBobot(bobotKepentingan[1][1],bobotKecocokan[1][1])+
                hitBobot(bobotKepentingan[2][1],bobotKecocokan[2][1])+
                hitBobot(bobotKepentingan[3][1],bobotKecocokan[3][1])+
                hitBobot(bobotKepentingan[4][1],bobotKecocokan[4][1])+
                hitBobot(bobotKepentingan[5][1],bobotKecocokan[5][1])+
                hitBobot(bobotKepentingan[6][1],bobotKecocokan[6][1])+
                hitBobot(bobotKepentingan[7][1],bobotKecocokan[7][1])+
                hitBobot(bobotKepentingan[8][1],bobotKecocokan[8][1])
                ) / k
                
                ;
        
        return subs;
    }
    
    public double getZ(double[][] bobotKepentingan,double[][] bobotKecocokan,int k) {
        double subs = 
                (
                hitBobot(bobotKepentingan[0][2],bobotKecocokan[0][2])+
                hitBobot(bobotKepentingan[1][2],bobotKecocokan[1][2])+
                hitBobot(bobotKepentingan[2][2],bobotKecocokan[2][2])+
                hitBobot(bobotKepentingan[3][2],bobotKecocokan[3][2])+
                hitBobot(bobotKepentingan[4][2],bobotKecocokan[4][2])+
                hitBobot(bobotKepentingan[5][2],bobotKecocokan[5][2])+
                hitBobot(bobotKepentingan[6][2],bobotKecocokan[6][2])+
                hitBobot(bobotKepentingan[7][2],bobotKecocokan[7][2])+
                hitBobot(bobotKepentingan[8][2],bobotKecocokan[8][2])
                ) / k
                
                ;
        
        return subs;
    }
    
    
    public static void main(String[] args) {
        
        new ControlFuzzy();
    }
    
    public double selectionAlternatif_TIDAKOPTIMIS(double Q, double Y, double Z) {
        double select = ((0) * (Z) + (Y) + (1 - 0) * (Q));
        return select / 2;
    }
    public double selectionAlternatif_OPTIMIS(double Q, double Y, double Z) {
        double select = ((0.5) * (Z) + (Y) + (1 - 0.5) * (Q));
        return select / 2;
    }
    public double selectionAlternatif_SANGATOPTIMIS(double Q, double Y, double Z) {
        double select = ((1) * (Z) + (Y) + (1 - 1) * (Q));
        return select / 2;
    }
}
