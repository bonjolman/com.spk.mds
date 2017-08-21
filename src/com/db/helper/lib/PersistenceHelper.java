/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.helper.lib;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wildan
 */
public class PersistenceHelper {
    
    private static EntityManagerFactory factory;
    
    static {
        
        
  
    }
    
  
    
    public static void setProperties(String url, String user, String pass, String db) {
        Map<String, String> prop = new HashMap<String,String>();
        prop.put("javax.persistence.jdbc.url", "jdbc:mysql://"+url+"/"+db);
        prop.put("javax.persistence.jdbc.user", user);
        prop.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        prop.put("javax.persistence.jdbc.password", pass);
        
        
        factory = Persistence.createEntityManagerFactory("com.spk.mdsPU", prop);
        
        
        
    }
    
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }
}
