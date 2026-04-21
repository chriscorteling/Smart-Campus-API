/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.resources;

import com.smartcampus.smart.campus.api.DataStore;
import com.smartcampus.smart.campus.api.exceptions.LinkedResourceNotFoundException;
import com.smartcampus.smart.campus.api.models.Sensor;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

/**
 *
 * @author chrisrehan
 */
//Part 3
@Path("/sensors")
public class SensorResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSensors(Sensor sensor) {

        //Check if the roomId exists and throw
        if (!DataStore.rooms.containsKey(sensor.getRoomId())) throw new LinkedResourceNotFoundException("Room not found");

        //Generate ID if missing
        if (sensor.getId() == null || sensor.getId().isEmpty()) {
            sensor.setId(UUID.randomUUID().toString());
        }

        //Saving sensor
        DataStore.sensors.put(sensor.getId(), sensor);

        //Add sensor ID into the room list
        DataStore.rooms.get(sensor.getRoomId()).getSensorIds().add(sensor.getId());

        //Initialise empty readings list
        DataStore.sensorReadings.put(sensor.getId(), new ArrayList<>());

        //return status code
        return Response.status(201).entity(sensor).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors(@QueryParam("type") String type) {

        //return all the sensor data as a List
        List<Sensor> sensorList = new ArrayList<>(DataStore.sensors.values());

        //return all the sensors if type is null or empty
        if (type == null || type.isEmpty()) {
            return Response.ok(sensorList).build();

        } else {

            //if the type is not empty or null return the filtered sensors
            List<Sensor> filteredSensorList = new ArrayList<>();
            for (Sensor sensor : sensorList) {

                if (sensor.getType().equals(type)) {
                    filteredSensorList.add(sensor);
                }

            }

            //retrun status code 200 but inside parameters we gives the List name to retreive exact data
            return Response.ok(filteredSensorList).build();
        }
    }

    //Part 4 - Sub Resource Locator
    @Path("{sensorId}/readings")
    public SensorReadingResource getReadingsResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }

}
