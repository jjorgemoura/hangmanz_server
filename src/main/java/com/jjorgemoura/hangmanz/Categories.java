/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz;

import com.jjorgemoura.hangmanz.model.ZDAlphabet;
import com.jjorgemoura.hangmanz.model.ZDCategory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jorge
 */
@Path("v1")
public class Categories {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Categories
     */
    public Categories() {
    }

    /**
     * Retrieves representation of an instance of com.jjorgemoura.hangmanz.Categories
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoriesJson() {

        String categoriesJson = ZDCategory.listAllJson();
       
        return Response.ok(categoriesJson, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/alphabet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlphabetJson() {
       
        String alphabetJson = ZDAlphabet.listAllJson();
        
        return Response.ok(alphabetJson, MediaType.APPLICATION_JSON).build();
    }
}
