/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz;

import com.jjorgemoura.hangmanz.db.MongoDBManager;
import com.jjorgemoura.hangmanz.model.ZDAlphabet;
import com.jjorgemoura.hangmanz.model.ZDCategory;
import com.jjorgemoura.hangmanz.model.ZDHangmanGame;
import com.mongodb.DB;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service.
 * This class contain the V1 of the main services of Hangman.
 *
 * @author jorge
 */
@Path("v1")
public class Game {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Game
     */
    public Game() {
    }

    
    //--------------------------------------------------------------------------------------------------
    //                            GAMES
    //--------------------------------------------------------------------------------------------------
    /**
     * (GET) Retrieves all the Hangman games.
     * @return A string with the JSON representation of all games.
     */
    @GET
    @Path("/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGamesJson() {
        //TODO return proper representation object
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("{\"error\" : \"Method not implemented\"}").build();
    }

    
    /**
     * (POST) The service creates a new Hangman game.
     * @param content The JSON with the category information. (Right now it's not used, instead, it's used a a query parameter).
     * @param category The category of the new game.
     * @return A string with the JSON representation of the game.
     * @throws java.net.URISyntaxException 
     */
    @POST
    @Path("/games") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNewGameJson(String content, @QueryParam("category") String category) throws URISyntaxException {
        
        //NEW GAME        
        if (category == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: query param category is missing.\"}").build();
        } 
        
        //Prepare DB
        DB myDB = MongoDBManager.db();
            
        
        //FIND CATEGORY
        ZDCategory theCategory = ZDCategory.findByName(category);
        
        if (theCategory == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\" : \"Not Found: The query param category is not found or known.\"}").build();
        }
        
        
        //START GAME
        ZDHangmanGame theGame = new ZDHangmanGame(theCategory, myDB);
        theGame.beginGame();
        theGame.persist();
        
        //PREPARE JSON
        String result = theGame.toJson();
        
        //URI
        URI theURI = new URI("v1/games/" + theGame.getUniqueUUID());
        
        return Response.created(theURI).entity(result).build();
    }
    
    
    
    
    
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GAME X
    //--------------------------------------------------------------------------------------------------
    /**
     * (GET) Retrieves the current representation (JSON) of the Hangman game referenced in the path.
     * @param gameID The game unique ID.
     * @return A string with the current JSON representation of the game.
     */
    @GET
    @Path("/games/{gameID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameStatusJson(@PathParam("gameID") String gameID) {
        
        
        if(gameID == null) {
        
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: path param gameID is missing.\"}").build();
        }
        
        
        //DB Setup
        DB myDB = MongoDBManager.db();
        
        //START GAME
        ZDHangmanGame theGame = ZDHangmanGame.loadByUUID(gameID, myDB);
        
        if(theGame == null) {
        
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\" : \"Not found: the path param gameID was not found.\"}").build();
        }
        
        
        //PREPARE JSON
        String result = theGame.toJson();
        
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    
    
    /**
     * (POST) Method for updating the instance of Game referenced. (Not implemented right now)
     * @param theJSON
     * @param gameID The game unique ID.
     * @return 
     */
    @POST
    @Path("/games/{gameID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postGameJson(final String theJSON, @PathParam("gameID") String gameID) {
        //TODO return proper representation object
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("{\"error\" : \"Method not implemented\"}").build();
    }
    
    /**
     * (DELETE) Method for delete the instance of Game referenced. (Not implemented right now)
     * @param theJSON
     * @param gameID The game unique ID.
     * @return 
     */
    @DELETE
    @Path("/games/{gameID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteGameJson(final String theJSON, @PathParam("gameID") String gameID) {
        //TODO return proper representation object
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("{\"error\" : \"Method not implemented\"}").build();
    }
    
    
    
    //--------------------------------------------------------------------------------------------------
    //                            GAME X LETTER
    //--------------------------------------------------------------------------------------------------
    /**
     * (GET) Retrieves a representation (JSON) indicating if the letter was already played or not.
     * @param gameID The game unique ID.
     * @param letter The letter to be tested.
     * @return A string with the JSON representation of the letter.
     */
    @GET
    @Path("/games/{gameID}/{letter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameLetterStatusJson(@PathParam("gameID") String gameID, @PathParam("letter") String letter) {
        
        
        if(gameID == null) {
        
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: path param gameID is missing.\"}").build();
        }
        
        if(letter == null) {
        
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: path param letter is missing.\"}").build();
        }
        
        if(letter.length() != 1) {
        
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{\"error\" : \"Not Acceptable: path param letter is not acceptable. Must be only a letter.\"}").build();
        }
        
        
        //DB Setup
        DB myDB = MongoDBManager.db();
        
        //START GAME
        ZDHangmanGame theGame = ZDHangmanGame.loadByUUID(gameID, myDB);
        
        if(theGame == null) {
        
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\" : \"Not found: the path param gameID was not found.\"}").build();
        }
        
        //PREPARE JSON
        String result = theGame.letterStatusAsJson(letter);
        
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    
    /**
     * (POST) method for updating or creating an instance of Game
     * @param theJSON Not used.
     * @param gameID The game unique ID.
     * @param letter The letter to be tested.
     * @return A string with the JSON representation of the game.
     */
    @POST
    @Path("/games/{gameID}/{letter}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postGameLetterPlayJson(final String theJSON, @PathParam("gameID") String gameID, @PathParam("letter") String letter) {
        
        
        if(gameID == null) {
        
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: path param gameID is missing.\"}").build();
        }
        
        if(letter == null) {
        
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Bad Request: path param letter is missing.\"}").build();
        }
        
        if(letter.length() != 1) {
        
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("{\"error\" : \"Not Acceptable: path param letter is not acceptable. Must be only a letter.\"}").build();
        }
        
        //DB Setup
        DB myDB = MongoDBManager.db();
        
        //START GAME
        ZDHangmanGame theGame = ZDHangmanGame.loadByUUID(gameID, myDB);  
        
        if(theGame == null) {
        
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\" : \"Not found: the path param gameID was not found.\"}").build();
        }
        
        //PLAY
        ZDAlphabet theLetter = ZDAlphabet.findByLetter(letter);
        
        if(theLetter == null) {
        
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\" : \"Not found: the path param letter was not found.\"}").build();
        }
                
        boolean letterIsDuplicated = theGame.letterIsDuplicated(theLetter);
        
        if(letterIsDuplicated) {
        
        }
        else {
            theGame.guessLetter(theLetter);

            //Update into DB
            theGame.update();
        }
        
        //PREPARE JSON
        String result = theGame.toJson();
        
        
        //URI
        String newURI = "v1/games/" + theGame.getUniqueUUID() + "/" + letter;
        URI theURI;
        try {
            theURI = new URI(newURI);
        } catch (URISyntaxException ex) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\" : \"ERROR: Internal server error.\"}").build();
        }
        
        return Response.created(theURI).entity(result).build();
    }
    
    
    /**
     * (PUT) This method can be user to test a complete solution word, instead of letter by letter. (Not implemented right now)
     * @param theJSON Not used.
     * @param gameID The game unique ID.
     * @param guessWord The solution to be tested.
     * @return Return a JSON indicating the current status of the game.
     */
    @PUT
    @Path("/games/{gameID}/{guessWord}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putGameGuessJson(final String theJSON, @PathParam("gameID") String gameID, @PathParam("guessWord") String guessWord) {
        //TODO return proper representation object
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("{\"error\" : \"Method not implemented\"}").build();
    }
}
