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
public class ZDAlphabet {
    
    private final int id;
    private final String letter;
    private final int order;
    private String description;
    
   
    //--------------------------------------------------------------------------------------------------
    //                            Constructor
    //--------------------------------------------------------------------------------------------------
    public ZDAlphabet(int id, String letter, int order) {
    
        this.id = id;
        this.letter = letter;
        this.order = order;
    }
    
    
    //--------------------------------------------------------------------------------------------------
    //                            Class Methods
    //--------------------------------------------------------------------------------------------------
    public static List<ZDAlphabet> listAll() {
    
        List<ZDAlphabet> theList = new ArrayList<>();
    
        ZDAlphabet l1 = new ZDAlphabet(1, "a", 1);
        ZDAlphabet l2 = new ZDAlphabet(2, "b", 2);
        ZDAlphabet l3 = new ZDAlphabet(3, "c", 3);
        ZDAlphabet l4 = new ZDAlphabet(4, "d", 4);
        ZDAlphabet l5 = new ZDAlphabet(5, "e", 5);
        ZDAlphabet l6 = new ZDAlphabet(6, "f", 6);
        ZDAlphabet l7 = new ZDAlphabet(7, "g", 7);
        ZDAlphabet l8 = new ZDAlphabet(8, "h", 8);
        ZDAlphabet l9 = new ZDAlphabet(9, "i", 9);
        ZDAlphabet l10 = new ZDAlphabet(10, "j", 10);
        ZDAlphabet l11 = new ZDAlphabet(11, "k", 11);
        ZDAlphabet l12 = new ZDAlphabet(12, "l", 12);
        ZDAlphabet l13 = new ZDAlphabet(13, "m", 13);
        ZDAlphabet l14 = new ZDAlphabet(14, "n", 14);
        ZDAlphabet l15 = new ZDAlphabet(15, "o", 15);
        ZDAlphabet l16 = new ZDAlphabet(16, "p", 16);
        ZDAlphabet l17 = new ZDAlphabet(17, "q", 17);
        ZDAlphabet l18 = new ZDAlphabet(18, "r", 18);
        ZDAlphabet l19 = new ZDAlphabet(19, "s", 19);
        ZDAlphabet l20 = new ZDAlphabet(20, "t", 20);
        ZDAlphabet l21 = new ZDAlphabet(21, "u", 21);
        ZDAlphabet l22 = new ZDAlphabet(22, "v", 22);
        ZDAlphabet l23 = new ZDAlphabet(23, "w", 23);
        ZDAlphabet l24 = new ZDAlphabet(24, "x", 24);
        ZDAlphabet l25 = new ZDAlphabet(25, "y", 25);
        ZDAlphabet l26 = new ZDAlphabet(26, "z", 26);
        
        theList.add(l1);
        theList.add(l2);
        theList.add(l3);
        theList.add(l4);
        theList.add(l5);
        theList.add(l6);
        theList.add(l7);
        theList.add(l8);
        theList.add(l9);
        theList.add(l10);
        theList.add(l11);
        theList.add(l12);
        theList.add(l13);
        theList.add(l14);
        theList.add(l15);
        theList.add(l16);
        theList.add(l17);
        theList.add(l18);
        theList.add(l19);
        theList.add(l20);
        theList.add(l21);
        theList.add(l22);
        theList.add(l23);
        theList.add(l24);
        theList.add(l25);
        theList.add(l26);
        
        return theList;
    }
    
    public static String listAllJson() {
         
        //Data to JSON
        List<ZDAlphabet> theList = ZDAlphabet.listAll();
        
        
        //JSON Setup
        StringWriter sw = new StringWriter();
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        JsonGenerator generator = factory.createGenerator(sw);
        
        generator.writeStartObject();
        generator.writeStartArray("alphabet");
        
        for(Iterator<ZDAlphabet> it = theList.iterator(); it.hasNext(); ) {
        
            ZDAlphabet x = it.next();
            
            //generator.writeStartObject();
            generator.write(x.letter);
            //generator.writeEnd();
        }
   
        generator.writeEnd();
        generator.writeEnd();
        generator.close(); 
        
        return sw.toString();
    }
    
    public static ZDAlphabet findByLetter(String letter) {
         
        //Data to JSON
        ZDAlphabet result = null;
        
        
        for(Iterator<ZDAlphabet> it = ZDAlphabet.listAll().iterator(); it.hasNext(); ) {
        
            ZDAlphabet x = it.next();
            
            if(x.getLetter().equals(letter)) {
            
                result = x;
            }
        }
   
        return result;
    }
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GETTERS AND SETTERS
    //--------------------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter;
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
