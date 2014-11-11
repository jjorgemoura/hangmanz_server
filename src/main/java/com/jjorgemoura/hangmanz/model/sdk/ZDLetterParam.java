/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.model.sdk;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author jorge
 */
public class ZDLetterParam {
    
    private String letter;
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------
    public ZDLetterParam(String letter) throws WebApplicationException {
    
        try {
            this.letter = letter;
        }
        catch (IllegalArgumentException e) {
            throw new WebApplicationException(
                Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Couldn't instantiate the letter: " + letter + " (" + e.getMessage() + ")")
                    .build()
      );
    }
    }
    
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GETTERS AND SETTERS
    //--------------------------------------------------------------------------------------------------
    public String getLetter() {
        return letter;
    }
    
    
}
