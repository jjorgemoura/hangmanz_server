/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jorge
 */
public class ZDHangmanWord {
    
    private static final int RANDOM_MIN = 1;
    private static final int RANDOM_MAX = 40;
    
    private final int id;
    private final String name;
    private final ZDCategory category;
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------
    public ZDHangmanWord(int id, String name, ZDCategory category) {
    
        this.id = id;
        this.name = name;
        this.category = category;
    }
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CLASS METHODS
    //--------------------------------------------------------------------------------------------------
    public static ZDHangmanWord randomByCategory(ZDCategory category) {
        
        List<ZDHangmanWord> theList = new ArrayList<>();
    
        if(category.getId() == 1) {
        
            theList = ZDHangmanWord.listAllCategory1Aux();
        }
        
        if(category.getId() == 2) {
        
            theList = ZDHangmanWord.listAllCategory2Aux();
        }
        
        
        if(theList.size() != 40) {
        
            return null;
        }
        
        
        //Random number between 1 and 40
        Random rand = new Random();
        int x = rand.nextInt(RANDOM_MAX - RANDOM_MIN + 1) + 1;
        
        //Get the Word
        ZDHangmanWord theWord = theList.get(x - 1);
        
        return theWord;
    }
    
    public static List<ZDHangmanWord> listAllByCategory(ZDCategory category) {
    
        List<ZDHangmanWord> theList = new ArrayList<>();
    
        if(category.getId() == 1) {
        
            theList = ZDHangmanWord.listAllCategory1Aux();
        }
        
        if(category.getId() == 2) {
        
            theList = ZDHangmanWord.listAllCategory2Aux();
        }
        
        return theList;
    }
    
    
    public static List<ZDHangmanWord> listAllCategory1Aux() {
    
        ZDCategory categoryCountrys = ZDCategory.findByID(1);
        
        List<ZDHangmanWord> theList = new ArrayList<>();
    
        ZDHangmanWord c1 = new ZDHangmanWord(1, "United Kingdon", categoryCountrys);
        ZDHangmanWord c2 = new ZDHangmanWord(2, "United States", categoryCountrys);
        ZDHangmanWord c3 = new ZDHangmanWord(3, "France", categoryCountrys);
        ZDHangmanWord c4 = new ZDHangmanWord(4, "Japan", categoryCountrys);
        ZDHangmanWord c5 = new ZDHangmanWord(5, "China", categoryCountrys);
        ZDHangmanWord c6 = new ZDHangmanWord(6, "Australia", categoryCountrys);
        ZDHangmanWord c7 = new ZDHangmanWord(7, "Germany", categoryCountrys);
        ZDHangmanWord c8 = new ZDHangmanWord(8, "Sweden", categoryCountrys);
        ZDHangmanWord c9 = new ZDHangmanWord(9, "Angola", categoryCountrys);
        ZDHangmanWord c10 = new ZDHangmanWord(10, "Canada", categoryCountrys);
        ZDHangmanWord c11 = new ZDHangmanWord(11, "Norway", categoryCountrys);
        ZDHangmanWord c12 = new ZDHangmanWord(12, "Portugal", categoryCountrys);
        ZDHangmanWord c13 = new ZDHangmanWord(13, "New Zealand", categoryCountrys);
        ZDHangmanWord c14 = new ZDHangmanWord(14, "Albania", categoryCountrys);
        ZDHangmanWord c15 = new ZDHangmanWord(15, "Chile", categoryCountrys);
        ZDHangmanWord c16 = new ZDHangmanWord(16, "India", categoryCountrys);
        ZDHangmanWord c17 = new ZDHangmanWord(17, "Ethiopia", categoryCountrys);
        ZDHangmanWord c18 = new ZDHangmanWord(18, "Tanzania", categoryCountrys);
        ZDHangmanWord c19 = new ZDHangmanWord(19, "Morocco", categoryCountrys);
        ZDHangmanWord c20 = new ZDHangmanWord(20, "Mozambique", categoryCountrys);
        ZDHangmanWord c21 = new ZDHangmanWord(21, "Madagascar", categoryCountrys);
        ZDHangmanWord c22 = new ZDHangmanWord(22, "Senegal", categoryCountrys);
        ZDHangmanWord c23 = new ZDHangmanWord(23, "Hungary", categoryCountrys);
        ZDHangmanWord c24 = new ZDHangmanWord(24, "Moldova", categoryCountrys);
        ZDHangmanWord c25 = new ZDHangmanWord(25, "Bahrain", categoryCountrys);
        ZDHangmanWord c26 = new ZDHangmanWord(26, "Montenegro", categoryCountrys);
        ZDHangmanWord c27 = new ZDHangmanWord(27, "Liechtenstein", categoryCountrys);
        ZDHangmanWord c28 = new ZDHangmanWord(28, "Nicaragua", categoryCountrys);
        ZDHangmanWord c29 = new ZDHangmanWord(29, "Mongolia", categoryCountrys);
        ZDHangmanWord c30 = new ZDHangmanWord(30, "Jamaica", categoryCountrys);
        ZDHangmanWord c31 = new ZDHangmanWord(31, "Mauritius", categoryCountrys);
        ZDHangmanWord c32 = new ZDHangmanWord(32, "Panama", categoryCountrys);
        ZDHangmanWord c33 = new ZDHangmanWord(33, "Turkmenistan", categoryCountrys);
        ZDHangmanWord c34 = new ZDHangmanWord(34, "Finland", categoryCountrys);
        ZDHangmanWord c35 = new ZDHangmanWord(35, "Singapore", categoryCountrys);
        ZDHangmanWord c36 = new ZDHangmanWord(36, "Switzerland", categoryCountrys);
        ZDHangmanWord c37 = new ZDHangmanWord(37, "Honduras", categoryCountrys);
        ZDHangmanWord c38 = new ZDHangmanWord(38, "Argentina", categoryCountrys);
        ZDHangmanWord c39 = new ZDHangmanWord(39, "Spain", categoryCountrys);
        ZDHangmanWord c40 = new ZDHangmanWord(40, "Ghana", categoryCountrys);
        
        theList.add(c1);
        theList.add(c2);
        theList.add(c3);
        theList.add(c4);
        theList.add(c5);
        theList.add(c6);
        theList.add(c7);
        theList.add(c8);
        theList.add(c9);
        theList.add(c10);
        theList.add(c11);
        theList.add(c12);
        theList.add(c13);
        theList.add(c14);
        theList.add(c15);
        theList.add(c16);
        theList.add(c17);
        theList.add(c18);
        theList.add(c19);
        theList.add(c20);
        theList.add(c21);
        theList.add(c22);
        theList.add(c23);
        theList.add(c24);
        theList.add(c25);
        theList.add(c26);
        theList.add(c27);
        theList.add(c28);
        theList.add(c29);
        theList.add(c30);
        theList.add(c31);
        theList.add(c32);
        theList.add(c33);
        theList.add(c34);
        theList.add(c35);
        theList.add(c36);
        theList.add(c37);
        theList.add(c38);
        theList.add(c39);
        theList.add(c40);
        
        return theList;
    }
    
    
    public static List<ZDHangmanWord> listAllCategory2Aux() {
    
        ZDCategory categoryBands = ZDCategory.findByID(2);
        
        List<ZDHangmanWord> theList = new ArrayList<>();
        
        ZDHangmanWord b1 = new ZDHangmanWord(1, "Pink Floyd", categoryBands);
        ZDHangmanWord b2 = new ZDHangmanWord(2, "The Beatles", categoryBands);
        ZDHangmanWord b3 = new ZDHangmanWord(3, "The Police", categoryBands);
        ZDHangmanWord b4 = new ZDHangmanWord(4, "Led Zeppelin", categoryBands);
        ZDHangmanWord b5 = new ZDHangmanWord(5, "The Mars Volta", categoryBands);
        ZDHangmanWord b6 = new ZDHangmanWord(6, "Antemasque", categoryBands);
        ZDHangmanWord b7 = new ZDHangmanWord(7, "The Cure", categoryBands);
        ZDHangmanWord b8 = new ZDHangmanWord(8, "Genesis", categoryBands);
        ZDHangmanWord b9 = new ZDHangmanWord(9, "Bosnian Rainbows", categoryBands);
        ZDHangmanWord b10 = new ZDHangmanWord(10, "Bauhaus", categoryBands);
        ZDHangmanWord b11 = new ZDHangmanWord(11, "Joy Division", categoryBands);
        ZDHangmanWord b12 = new ZDHangmanWord(12, "Bjork", categoryBands);
        ZDHangmanWord b13 = new ZDHangmanWord(13, "Yes", categoryBands);
        ZDHangmanWord b14 = new ZDHangmanWord(14, "Red Hot Chili Peppers", categoryBands);
        ZDHangmanWord b15 = new ZDHangmanWord(15, "Queens of the Stone Age", categoryBands);
        ZDHangmanWord b16 = new ZDHangmanWord(16, "Moloko", categoryBands);
        ZDHangmanWord b17 = new ZDHangmanWord(17, "Seu Jorge", categoryBands);
        ZDHangmanWord b18 = new ZDHangmanWord(18, "David Bowie", categoryBands);
        ZDHangmanWord b19 = new ZDHangmanWord(19, "The Smiths", categoryBands);
        ZDHangmanWord b20 = new ZDHangmanWord(20, "Sonic Youth", categoryBands);
        ZDHangmanWord b21 = new ZDHangmanWord(21, "Nirvana", categoryBands);
        ZDHangmanWord b22 = new ZDHangmanWord(22, "Supertramp", categoryBands);
        ZDHangmanWord b23 = new ZDHangmanWord(23, "The Who", categoryBands);
        ZDHangmanWord b24 = new ZDHangmanWord(24, "The Doors", categoryBands);
        ZDHangmanWord b25 = new ZDHangmanWord(25, "Massive Attach", categoryBands);
        ZDHangmanWord b26 = new ZDHangmanWord(26, "Portishead", categoryBands);
        ZDHangmanWord b27 = new ZDHangmanWord(27, "Pixies", categoryBands);
        ZDHangmanWord b28 = new ZDHangmanWord(28, "New Order", categoryBands);
        ZDHangmanWord b29 = new ZDHangmanWord(29, "Stevie Wonder", categoryBands);
        ZDHangmanWord b30 = new ZDHangmanWord(30, "Marvin Gaye", categoryBands);
        ZDHangmanWord b31 = new ZDHangmanWord(31, "Marilyn Manson", categoryBands);
        ZDHangmanWord b32 = new ZDHangmanWord(32, "King Crimson", categoryBands);
        ZDHangmanWord b33 = new ZDHangmanWord(33, "Iron Maiden", categoryBands);
        ZDHangmanWord b34 = new ZDHangmanWord(34, "Gary Clark Jr", categoryBands);
        ZDHangmanWord b35 = new ZDHangmanWord(35, "Jack White", categoryBands);
        ZDHangmanWord b36 = new ZDHangmanWord(36, "Faith No More", categoryBands);
        ZDHangmanWord b37 = new ZDHangmanWord(37, "Depeche Mode", categoryBands);
        ZDHangmanWord b38 = new ZDHangmanWord(38, "Can", categoryBands);
        ZDHangmanWord b39 = new ZDHangmanWord(39, "Amy Winehouse", categoryBands);
        ZDHangmanWord b40 = new ZDHangmanWord(40, "Radiohead", categoryBands);
        
        theList.add(b1);
        theList.add(b2);
        theList.add(b3);
        theList.add(b4);
        theList.add(b5);
        theList.add(b6);
        theList.add(b7);
        theList.add(b8);
        theList.add(b9);
        theList.add(b10);
        theList.add(b11);
        theList.add(b12);
        theList.add(b13);
        theList.add(b14);
        theList.add(b15);
        theList.add(b16);
        theList.add(b17);
        theList.add(b18);
        theList.add(b19);
        theList.add(b20);
        theList.add(b21);
        theList.add(b22);
        theList.add(b23);
        theList.add(b24);
        theList.add(b25);
        theList.add(b26);
        theList.add(b27);
        theList.add(b28);
        theList.add(b29);
        theList.add(b30);
        theList.add(b31);
        theList.add(b32);
        theList.add(b33);
        theList.add(b34);
        theList.add(b35);
        theList.add(b36);
        theList.add(b37);
        theList.add(b38);
        theList.add(b39);
        theList.add(b40);
        
        return theList;
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

    public ZDCategory getCategory() {
        return category;
    }

}
