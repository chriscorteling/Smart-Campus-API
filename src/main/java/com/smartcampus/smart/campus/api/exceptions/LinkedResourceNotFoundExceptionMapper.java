/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.exceptions;

import com.smartcampus.smart.campus.api.exceptions.LinkedResourceNotFoundException;
import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author chrisrehan
 */

//Part 5

@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException>{
    
    public Response toResponse(LinkedResourceNotFoundException exception){
        //Mapping the error
        Map<String, String> errorMap = new HashMap<>();
        
        //Adding the error message
        errorMap.put("error", "The referenced roomId does not exist in the system");
        
        //return the status code with the relevat error
        return Response.status(422).entity(errorMap).type(MediaType.APPLICATION_JSON).build();
    }
}