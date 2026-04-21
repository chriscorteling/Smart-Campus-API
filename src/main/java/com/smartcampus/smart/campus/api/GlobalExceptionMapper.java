/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api;

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
public class GlobalExceptionMapper implements ExceptionMapper<Throwable>{
    public Response toResponse(Throwable exception){
          //Mapping the error
        Map<String, String> errorMap = new HashMap<>();
        
        //Adding the error message
        errorMap.put("error", "An unexpected error occurred. Please try again later");
        
        //return the status code with the relevat error
        return Response.status(500).entity(errorMap).type(MediaType.APPLICATION_JSON).build();
    }
}
