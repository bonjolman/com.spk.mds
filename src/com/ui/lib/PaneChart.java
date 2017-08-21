/*
 * The MIT License
 *
 * Copyright 2017 WILDAN.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ui.lib;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Paint;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author WILDAN
 */
public class PaneChart{
    
   
   public PieDataset createDataset(String[] title, Double[] value) {
      System.out.println(title.length);
      DefaultPieDataset dataset = new DefaultPieDataset( );
      for(int i = 0; i < title.length - 1;i++) {
          System.out.println(title[i]);
          dataset.setValue( title[i] , value[i] ); 
      }
      return dataset;         
   }
   
   public JFreeChart createChart( PieDataset dataset , String title) {
      JFreeChart chart = ChartFactory.createPieChart(      
         title,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);
      
      return chart;
   }
   
   public JPanel getPanel(Dimension size, String title, String[] str, Double[] val) {
       
       JFreeChart chart = createChart(createDataset(str,val), title);
       
       JPanel pane = new ChartPanel(chart);
       pane.setSize(size);
       return pane;
   }
   
   public int JPEG;
   public int PNG;
   public void saveImage(int type, File file, String title, String[] str, Double[] val) {
        int width = 640;   /* Width of the image */
        int height = 480;  /* Height of the image */ 
        File pieChart = file;
        if(type == JPEG) {
            try {
                ChartUtilities.saveChartAsJPEG( pieChart , createChart(createDataset(str,val), title) , width , height );
            } catch (IOException ex) {
                Logger.getLogger(PaneChart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
}
