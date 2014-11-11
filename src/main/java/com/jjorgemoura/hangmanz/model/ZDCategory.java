/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.model;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

/**
 *
 * @author jorge
 */
public class ZDCategory {
     
    private final int id;
    private final String name;
    private final int order;
    private String description;
    
   
    //--------------------------------------------------------------------------------------------------
    //                            CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------
    public ZDCategory(int id, String name, int order) {
    
        this.id = id;
        this.name = name;
        this.order = order;
    }
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CLASS METHODS
    //--------------------------------------------------------------------------------------------------
    public static List<ZDCategory> listAll() {
    
        List<ZDCategory> theList = new ArrayList<>();
    
        ZDCategory c1 = new ZDCategory(1, "Country", 1);
        ZDCategory c2 = new ZDCategory(2, "Bands", 2);
        
        theList.add(c1);
        theList.add(c2);
        
        return theList;
    }
    
    public static ZDCategory findByID(int id) {
    
        ZDCategory category = null;
        
        for (Iterator<ZDCategory> iterator = ZDCategory.listAll().iterator(); iterator.hasNext();) {
            
            ZDCategory x = iterator.next();
            
            if(x.id == id) {
            
                category = x;
            }
        }

        return category;
    }
    
    public static ZDCategory findByName(String name) {
    
        ZDCategory category = null;
        
        for (Iterator<ZDCategory> iterator = ZDCategory.listAll().iterator(); iterator.hasNext();) {
            
            ZDCategory x = iterator.next();
            
            if(x.name.equals(name)) {
            
                category = x;
            }
        }

        return category;
    }
    
    public static String listAllJson() {
        
        StringWriter sw = new StringWriter();
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        JsonGenerator generator = factory.createGenerator(sw);
        generator.writeStartObject();
        generator.writeStartArray("categories");
        
        
        //DB Setup
        List<ZDCategory> xAll = ZDCategory.listAll();
        
        for(Iterator<ZDCategory> it = xAll.iterator(); it.hasNext(); ) {
        
            ZDCategory x = it.next();
            
            //test
            String sss = x.toJson();
            
            generator.writeStartObject();
            generator.write("id", x.id);
            generator.write("name", x.name);
            generator.write("order", x.order);
            generator.writeEnd();
        }
   
        generator.writeEnd();
        generator.writeEnd();
        generator.close(); 
        
        return sw.toString();
    }
    
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            PUBLIC METHODS
    //--------------------------------------------------------------------------------------------------
    public String toJson() {
    
        List<ZDCategory> theList = ZDCategory.listAll();
    
        
        StringWriter sw = new StringWriter();
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        JsonGenerator generator = factory.createGenerator(sw);
        generator.writeStartObject();
            
        generator.write("id", this.id);
        generator.write("name", this.name);
        generator.write("order", this.order);

        
        generator.writeEnd();
        generator.close(); 
        
        return sw.toString();
    }
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GETTERS AND SETTERS
    //--------------------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
