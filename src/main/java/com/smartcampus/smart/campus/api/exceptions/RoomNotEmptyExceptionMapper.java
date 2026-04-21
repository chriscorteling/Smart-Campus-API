/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.exceptions;

import com.smartcampus.smart.campus.api.exceptions.RoomNotEmptyException;
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

@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException>{
    
    public Response toResponse(RoomNotEmptyException exception){
        //Mapping the error
        Map<String, String> errorMap = new HashMap<>();
        
        //Adding the error message
        errorMap.put("error", "Room cannot be deleted as it still has sensors assigned to it");
        
        //return the status code with the relevat error
        return Response.status(409).entity(errorMap).type(MediaType.APPLICATION_JSON).build();
    }
}
