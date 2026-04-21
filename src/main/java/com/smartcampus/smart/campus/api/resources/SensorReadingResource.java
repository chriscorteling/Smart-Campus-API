/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.resources;

import com.smartcampus.smart.campus.api.DataStore;
import com.smartcampus.smart.campus.api.exceptions.SensorUnavailableException;
import com.smartcampus.smart.campus.api.models.SensorReading;
import com.smartcampus.smart.campus.api.models.Sensor;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;

import java.util.List;

/**
 *
 * @author chrisrehan
 */
//Part 4
public class SensorReadingResource {

    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadings() {

        //Return all the sensorIds    
        List<SensorReading> readings = DataStore.sensorReadings.get(sensorId);

        //return the readings List
        return Response.ok(readings).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReadings(SensorReading reading) {

        Sensor sensor = DataStore.sensors.get(sensorId);

        //Check if sensor is not in DataStore
        if (sensor == null) {
            return Response.status(404).build();
        }

        //check if status is "MAINTENANCE" and throws exception 
        if (sensor.getStatus().equals("MAINTENANCE")) throw new SensorUnavailableException("Sensor is under maintenance");

        //Add reading to the List
        DataStore.sensorReadings.get(sensorId).add(reading);

        //Update parent sensor currentValue
        sensor.setCurrentValue(reading.getValue());

        //Return 201
        return Response.status(201).entity(reading).build();

    }
}
