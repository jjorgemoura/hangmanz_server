/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.model.sdk;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorge
 */
@XmlRootElement
public class ZDCategoryParam {
    
    private String category;
    
    
    //--------------------------------------------------------------------------------------------------
    //                            CONSTRUCTOR
    //--------------------------------------------------------------------------------------------------
    public ZDCategoryParam(String category) throws WebApplicationException {
    
        try {
            this.category = category;
        }
        catch (IllegalArgumentException e) {
            throw new WebApplicationException(
                Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Couldn't instantiate the category: " + category + " (" + e.getMessage() + ")")
                    .build()
      );
    }
    }
    
    public ZDCategoryParam() {
    
        
    }
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GETTERS AND SETTERS
    //--------------------------------------------------------------------------------------------------
    @XmlElement(name="category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
