/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.exceptions;

import java.util.logging.Logger;
import java.util.logging.Level;

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
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    
    // Field at class level
    private static final Logger logger = Logger.getLogger(GlobalExceptionMapper.class.getName());
    
    public Response toResponse(Throwable exception) {
        
        // Log the error server-side
        logger.log(Level.SEVERE, "Unexpected error occurred", exception);
        
        // Build clean error response
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "An unexpected error occurred. Please try again later");
        
        // Return 500
        return Response.status(500).entity(errorMap).type(MediaType.APPLICATION_JSON).build();
    }
}