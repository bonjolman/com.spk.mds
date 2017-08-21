/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.model.lib;

import com.db.entity.lib.Config;
import com.db.helper.lib.PersistenceHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;

/**
 *
 * @author WILDAN
 */
public class ConfigModel extends Config{
    
    public String getPath(String obj) {
        EntityManager manager = PersistenceHelper.getEntityManagerFactory().createEntityManager();
        List<Config> ResultList = manager.createNamedQuery("Config.findAll").getResultList();
        
        for(Config a:ResultList) {
            if(a.getIdConfig().equals(obj)) {
                return a.getValue();
            }
        }
        return "";
    }
    
    
    private Properties props = new Properties();
    public void setConfig(String key, String value) {
        File configFile = new File("config.properties");
        try {
            props.setProperty(key, value);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "host settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
    
    public String getConfig(String key) {
        File configFile = new File("config.properties");
        try {
            FileReader reader = new FileReader(configFile);
            
            this.props.load(reader);
            String val = props.getProperty(key);
            reader.close();
            return val;
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
        return null;
    }
    
}
