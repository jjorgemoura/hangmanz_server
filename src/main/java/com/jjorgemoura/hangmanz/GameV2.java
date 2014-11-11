/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz;

import com.jjorgemoura.hangmanz.db.MongoDBManager;
import com.jjorgemoura.hangmanz.model.ZDCategory;
import com.jjorgemoura.hangmanz.model.ZDHangmanGame;
import com.jjorgemoura.hangmanz.model.sdk.ZDCategoryParam;
import com.jjorgemoura.hangmanz.model.sdk.ZDHangmanStatus;
import com.mongodb.DB;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 * This class contain the V2 of the main services of Hangman.
 *
 * @author jorge
 */
@Path("v2")
public class GameV2 {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GameV2
     */
    public GameV2() {
    }

    
    
    //--------------------------------------------------------------------------------------------------
    //                            GAMES
    //--------------------------------------------------------------------------------------------------
    /**
     * (POST) The service creates a new Hangman game.
     * @param category The category of the new game.
     * @return A instance of the Class ZDHangmanStatus. Instead of JSON, the service return a instance of the class to be easier the integration of the clients.
     * @throws java.net.URISyntaxException
     */
    @POST
    @Path("/games")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewGameJson(ZDCategoryParam category) throws URISyntaxException {
        
        //return instance of ZDHangmanStatus
        
        //NEW GAME
        if (category == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: query param category is missing.\"}").build();
        } 
        
        //Prepare DB
        DB myDB = MongoDBManager.db();
            
        
        //FIND CATEGORY
        ZDCategory theCategory = ZDCategory.findByName(category.getCategory());
        
        if (theCategory == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\" : \"Not Found: The query param category is not found or known.\"}").build();
        }
        
        //START GAME
        ZDHangmanGame theGame = new ZDHangmanGame(theCategory, myDB);
        theGame.beginGame();
        theGame.persist();
        
        
        //PREPARE JSON
        ZDHangmanStatus result = new ZDHangmanStatus(theGame);
        
        
        //URI
        URI theURI = new URI("v2/games/" + theGame.getUniqueUUID());
        
        return Response.created(theURI).entity(result).build();
    }
    
    
    

    
}
