/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class MongoDBManager {
    
    private static final int MONGODB_PORT = 27017;
    private static final String MONGODB_IP = "localhost";
    private static final String MONGODB_DB = "zdhangman";
    
    
    private static MongoClient mongoClient;
    
    
    
    
    //------------------------CONSTRUCTOR------------------------
    public MongoDBManager() {
    
    
    }
    
    
    //------------------------STATIC METHODS------------------------
    static {
    
        try {
            
            mongoClient = new MongoClient(MongoDBManager.MONGODB_IP, MongoDBManager.MONGODB_PORT);
            
            
        } catch (UnknownHostException ex) {
            
            Logger.getLogger(MongoDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RuntimeException ex) {
        
            Logger.getLogger(MongoDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static MongoClient engine() {
    
        if(mongoClient == null) {
        
            return null;
        }
    
        return mongoClient;
    }
    
    
    public static DB db() {
    
        MongoClient mc = MongoDBManager.engine();
        
        List<String> databaseNames = mc.getDatabaseNames();
        
        
        
        return mc.getDB(MONGODB_DB);
    }

}
