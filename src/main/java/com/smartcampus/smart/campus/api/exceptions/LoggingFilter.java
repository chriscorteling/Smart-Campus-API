/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.exceptions;

import javax.ws.rs.ext.Provider;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

import java.util.logging.Logger;

/**
 *
 * @author chrisrehan
 */
//Part 5

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    //Logger instance for this class
    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    //Logs every incoming request - HTTP method and URI
    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.info("Request: " + requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri());
    }

    //Logs every outgoing response - HTTP status code
    @Override 
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        logger.info("Response Status: " + responseContext.getStatus());
    }
}  
