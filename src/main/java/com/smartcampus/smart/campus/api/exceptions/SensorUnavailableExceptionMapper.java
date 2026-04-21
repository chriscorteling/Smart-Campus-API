/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.exceptions;

import com.smartcampus.smart.campus.api.exceptions.SensorUnavailableException;
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

//part 5

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException>{
    
    public Response toResponse(SensorUnavailableException exception){
        //Mapping the error
        Map<String, String> errorMap = new HashMap<>();
        
        //Adding the error message
        errorMap.put("error", "Sensor is currently under maintenance and cannot accept new readings");
        
        //return the status code with the relevat error
        return Response.status(403).entity(errorMap).type(MediaType.APPLICATION_JSON).build();
    }
}
