/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.model;

import com.jjorgemoura.hangmanz.utils.ZDDate;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;


/**
 *
 * @author jorge
 */
public class ZDHangmanGame {
    
    private static final int HANGMAN_NR_FAILS_TO_LOSE = 6;
    private static final String DB_COLLECTION_ID = "hangmangames";
    private final DB dbEngine;
    
    
    private final ZDCategory category;
    private ZDHangmanWord theWord;
    
    private long id;
    private String uniqueUUID;
    private ZDDate startDate;
    private ZDDate latestDate;
    private boolean finished;
    private boolean won;
    private int nrFails;
    
    private Map<Integer, ZDAlphabet> playsRecord;
    private Set<String> playedLettersRecord;
    
    
 
    //--------------------------------------------------------------------------------------------------
    //                            CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------
    public ZDHangmanGame(ZDCategory category, DB dbEngine) {
    
        this.dbEngine = dbEngine;
        this.category = category;
        this.finished = false;
        this.won = false;
        this.nrFails = 0;
        this.playsRecord = new HashMap<>();
        this.playedLettersRecord = new HashSet<>();
    }
    
    public ZDHangmanGame(DBObject x, DB dbEngine) {

        this.dbEngine = dbEngine;
        this.playsRecord = new HashMap<>();
        this.playedLettersRecord = new HashSet<>();
            
            
        this.uniqueUUID = (String)x.get("hm_uuid");
        this.category = ZDCategory.findByName((String)x.get("hm_category"));
        this.startDate = new ZDDate((String)x.get("hm_start_date"));
        this.latestDate = new ZDDate((String)x.get("hm_latest_date"));
        this.theWord = new ZDHangmanWord(1, (String)x.get("hm_the_word"), this.category);
        this.nrFails = 0;
        
        List jsonLettersPlayed = (List) x.get("hm_letters_played");


        for (Iterator iterator = jsonLettersPlayed.iterator(); iterator.hasNext();) {
            DBObject y = (DBObject)iterator.next();

            String jsonLetter = (String)y.get("hm_letter");
            int jsonOrder = (int)y.get("hm_letter_order");

            this.playsRecord.put(jsonOrder, ZDAlphabet.findByLetter(jsonLetter));
            this.playedLettersRecord.add(jsonLetter);
            
            if(this.theWord.getName().toLowerCase().contains(jsonLetter.toLowerCase())) {
                //do nothing
            }
            else {
            
                this.nrFails = this.nrFails + 1;
            }
        }
    }
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CLASS METHODS
    //--------------------------------------------------------------------------------------------------
    public static ZDHangmanGame loadByUUID(String uuid, DB dbEngine) {
    
        ZDHangmanGame theGame = null;
    
                
        if(dbEngine == null) {
        
            return null;
        }
        
        
        //Prepare Collection
        DBCollection dbCollection = dbEngine.getCollection(ZDHangmanGame.DB_COLLECTION_ID);
        
        BasicDBObject query = new BasicDBObject();
        query.put("hm_uuid", uuid);
        DBCursor cursor = dbCollection.find(query);
        
        while (cursor.hasNext()) {

            DBObject x = cursor.next();
            //System.out.println(x);
            
            theGame = new ZDHangmanGame(x, dbEngine);
        }
              
        return theGame;
    }
    
    
    //--------------------------------------------------------------------------------------------------
    //                            PUBLIC METHODS
    //--------------------------------------------------------------------------------------------------
    public void beginGame() {
    
        this.theWord = ZDHangmanWord.randomByCategory(this.category);
        this.startDate = new ZDDate();
        this.latestDate = new ZDDate();
        this.playsRecord.clear();
        this.playedLettersRecord.clear();
        this.finished = false;
        this.won = false;
        this.nrFails = 0;
        
        UUID randomUUID = UUID.randomUUID();
        this.uniqueUUID = randomUUID.toString();
    }
    
    
    public boolean letterIsDuplicated(ZDAlphabet letter) {
    
        boolean isDuplicated = false;
        
        if(this.playedLettersRecord.contains(letter.getLetter().toLowerCase())) {
        
            isDuplicated = true;
        }
        
        return isDuplicated;
    }
    
    
    public boolean guessLetter(ZDAlphabet letter) {
    
        boolean isInWord = false;
        

        //Avoid repeating letters
        if(this.playedLettersRecord.contains(letter.getLetter().toLowerCase())) {
        
            return false;
        }
    
        
        //Update the Date
        latestDate = new ZDDate();
        
        //Process the letter
        int pastNumberPlays = this.playedLettersRecord.size();
            
        this.playsRecord.put(pastNumberPlays + 1, letter);
        this.playedLettersRecord.add(letter.getLetter().toLowerCase());
            
            
        //Check if is a match
        if(this.theWord.getName().toLowerCase().contains(letter.getLetter().toLowerCase())) {
        
            isInWord = true;
        }
        
        
        //Decide state of the game
        if(isInWord) {
        
            //Check if won
            String currentGuessingWord = this.currentGuessingWord();
            if(!currentGuessingWord.contains("_")) {
            
                //it is done
                this.finished = true;
                this.won = true;
            }
        }
        else {
        
            //Check if is dead
            this.nrFails = this.nrFails + 1;
            
            if(this.nrFails == ZDHangmanGame.HANGMAN_NR_FAILS_TO_LOSE) {
            
                this.finished = true;
                this.won = false;
            }
        }
        
        
        return isInWord;
    }
    
    
    public String currentGuessingWord() {
    
        StringBuilder currentWord = new StringBuilder();
        
        
        for (int i = 0; i < this.theWord.getName().length(); i++) {
            
            
            
            String xLetter = this.theWord.getName().substring(i, i + 1).toLowerCase();
            
            if(this.playedLettersRecord.contains(xLetter)) {
            
                currentWord.append(xLetter);
            }
            else {
            
                if(xLetter.equals(" ")) {
                    currentWord.append(" ");
                }
                else {
                    currentWord.append("_");
                }
            }
        }
        
        return currentWord.toString();
    }
    
    
    public String toJson() {
   
        if(this.uniqueUUID == null) {
        
            return "";
        }
        
        StringWriter sw = new StringWriter();
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        JsonGenerator generator = factory.createGenerator(sw);
        generator.writeStartObject();
        generator.writeStartObject("hggame");
            
        //generator.write("id", this.id);
        generator.write("id", this.uniqueUUID);
        generator.write("startdate", this.startDate.toString());
        generator.write("latestDate", this.latestDate.toString());
        
        generator.write("latestDate", this.latestDate.toString());
        generator.write("nrErrors", this.nrFails);
        generator.write("isFinished", this.finished);
        generator.write("playerWon", this.won);
        
        //if fails, show the solution word
        if(this.finished && !this.won) {
            generator.write("currentGuessingWord", this.theWord.getName());
        }
        else {
            generator.write("currentGuessingWord", this.currentGuessingWord());
        }

        
        generator.writeStartArray("playedLetters");
        for(Iterator<String> it = this.playedLettersRecord.iterator(); it.hasNext(); ) {
        
            String x = it.next();
            
            generator.write(x);
        }
        generator.writeEnd();
        
        
        generator.writeStartArray("lettersStatus");
        for(Iterator<Integer> it = this.playsRecord.keySet().iterator(); it.hasNext(); ) {
        
            Integer i = it.next();
            ZDAlphabet x = this.playsRecord.get(i);
            
            generator.writeStartObject();
            if(this.theWord.getName().toLowerCase().contains(x.getLetter().toLowerCase())) {
            
                generator.write(x.getLetter(), "match");
            }
            else {
                
                generator.write(x.getLetter(), "unmatch");
            }
            generator.writeEnd();
        }
        generator.writeEnd();
        
        
        generator.writeEnd();
        generator.writeEnd();
        generator.close(); 
        
        return sw.toString();
    }
        
    
    public String letterStatusAsJson(String letter) {
    
        
        StringWriter sw = new StringWriter();
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        JsonGenerator generator = factory.createGenerator(sw);
        generator.writeStartObject();
        
        if(this.theWord.getName().toLowerCase().contains(letter.toLowerCase())) {
            
            generator.write(letter, "match");
        }
        else {
            
            generator.write(letter, "unmatch");
        }
   
        
        generator.writeEnd();
        generator.close(); 
        
        return sw.toString();
    }
    
    
    
    
    
    public void persist() {
    
        if(this.dbEngine == null) {
        
            return;
        }
        
        
        //Prepare JSON Document from Entity
        DBObject docX = this.createDBObject();
         
        //Prepare Collection
        DBCollection dbCollection = this.dbEngine.getCollection(ZDHangmanGame.DB_COLLECTION_ID);
        
        //Save - Write
        WriteResult dbInsertResult = dbCollection.insert(docX);
        
        
        //Finalise
        int n = dbInsertResult.getN();
        Object upsertedId = dbInsertResult.getUpsertedId();
        
        WriteConcern lastConcernObject = dbInsertResult.getLastConcern();
        
        String s = "ok";
    }
    
    
    public void update() {
        
        if(this.dbEngine == null) {
        
            return;
        }
        
        //Prepare Collection
        DBCollection dbCollection = this.dbEngine.getCollection(ZDHangmanGame.DB_COLLECTION_ID);

        //My doc to update
        BasicDBObject theDocument = new BasicDBObject();
        theDocument.append("hm_uuid", this.uniqueUUID);

        
        
        //The updates
        ZDAlphabet x = this.playsRecord.get(this.playsRecord.size());
        
        BasicDBObject lettersDocBuilder = new BasicDBObject();
        lettersDocBuilder.append("hm_letter", x.getLetter());
        lettersDocBuilder.append("hm_letter_order", this.playsRecord.size());
        
        BasicDBObject newData = new BasicDBObject();
        newData.append("$set", new BasicDBObject().append("hm_latest_date", this.latestDate.toString()));
        newData.append("$push", new BasicDBObject().append("hm_letters_played", lettersDocBuilder));

        
        //Save - Update
        WriteResult updateResult = dbCollection.update(theDocument, newData);
    }
    
    //------------------------PRIVATE METHODS------------------------   
    private DBObject createDBObject() {
        
        List lettersList = new ArrayList();
        
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        BasicDBObjectBuilder lettersDocBuilder;  
        
        
        int theOrder = 1;
        for(Iterator<Integer> it = this.playsRecord.keySet().iterator(); it.hasNext();) {
        
            Integer i = it.next();
            ZDAlphabet x = this.playsRecord.get(i);
            
            lettersDocBuilder = BasicDBObjectBuilder.start();
            lettersDocBuilder.append("hm_letter", x.getLetter());
            lettersDocBuilder.append("hm_letter_order", theOrder);
            lettersList.add(lettersDocBuilder.get());
            theOrder++;
        }
        
        
        
        
        docBuilder.append("hm_uuid", this.uniqueUUID);
        docBuilder.append("hm_start_date", this.startDate.toString());
        docBuilder.append("hm_latest_date", this.latestDate.toString());
        docBuilder.append("hm_the_word", this.theWord.getName());
        docBuilder.append("hm_category", this.category.getName());
        docBuilder.append("hm_letters_played", lettersList);
        
        return docBuilder.get();
    }
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GETTERS AND SETTERS
    //--------------------------------------------------------------------------------------------------
   

    public ZDCategory getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }

    public ZDDate getStartDate() {
        return startDate;
    }

    public ZDDate getLatestDate() {
        return latestDate;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getUniqueUUID() {
        return uniqueUUID;
    }

    public int getNrFails() {
        return nrFails;
    }
    
}
