/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.model.sdk;

import com.jjorgemoura.hangmanz.model.ZDHangmanGame;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is a class that represents the Game status. This is used as a transport object, to be used also in the client SDK.
 * 
 * @author jorge
 */
@XmlRootElement
public class ZDHangmanStatus {
    
    private String category;
    private String theWord;
    
    private String uniqueUUID;
    private String startDate;
    private String latestDate;
    private boolean finished;
    private boolean won;
    private int nrFails;
    
    //private Map<Integer, ZDAlphabet> playsRecord;
    //private Set<String> playedLettersRecord;
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------
    public ZDHangmanStatus(ZDHangmanGame game) throws WebApplicationException {
    
        try {
            this.uniqueUUID = game.getUniqueUUID();
            this.startDate = game.getStartDate().toString();
            this.latestDate = game.getLatestDate().toString();
            this.category = game.getCategory().getName();
            this.theWord = game.currentGuessingWord();
            this.nrFails = game.getNrFails();
            this.finished = game.isFinished();
            this.won = game.isWon();
        }
        catch (IllegalArgumentException e) {
            throw new WebApplicationException(
                Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Couldn't instantiate the game status (" + e.getMessage() + ")")
                    .build()
      );
    }
    }
    
    public ZDHangmanStatus() {
    
    }
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GETTERS AND SETTERS
    //--------------------------------------------------------------------------------------------------

    public String getCategory() {
        return category;
    }

    public String getTheWord() {
        return theWord;
    }

    public String getUniqueUUID() {
        return uniqueUUID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLatestDate() {
        return latestDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isWon() {
        return won;
    }

    public int getNrFails() {
        return nrFails;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTheWord(String theWord) {
        this.theWord = theWord;
    }

    public void setUniqueUUID(String uniqueUUID) {
        this.uniqueUUID = uniqueUUID;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void setNrFails(int nrFails) {
        this.nrFails = nrFails;
    }
    
    
}
