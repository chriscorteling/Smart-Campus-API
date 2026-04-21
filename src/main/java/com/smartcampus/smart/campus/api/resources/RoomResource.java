/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.resources;

import com.smartcampus.smart.campus.api.DataStore;
import com.smartcampus.smart.campus.api.exceptions.RoomNotEmptyException;
import com.smartcampus.smart.campus.api.models.Room;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author chrisrehan
 */
//Question 2.1
@Path("/rooms")
public class RoomResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms() {

        //Saving room details in DataStore into roomList 
        List<Room> roomList = new ArrayList<>(DataStore.rooms.values());

        //return the status code
        return Response.ok(roomList).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room) {

        //Checking if id is null or empty if soo we generate an id
        if (room.getId() == null || room.getId().isEmpty()) {
            room.setId(UUID.randomUUID().toString());
        }

        //Save in DataStore
        DataStore.rooms.put(room.getId(), room);

        //return the status code
        return Response.status(201).entity(room).build();

    }

    @GET
    @Path("/{roomId}") //The id comes from the URL
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoomById(@PathParam("roomId") String roomId) {

        //Check the room details and if empty we give the status code
        Room room = DataStore.rooms.get(roomId);
        if (room == null) {
            return Response.status(404).build();
        }

        //return success status code
        return Response.ok(room).build();

    }

    //Question 2.2
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {

        //Check if the room is null or not
        Room room = DataStore.rooms.get(roomId);
        if (room == null) {
            return Response.status(404).build();
        }
        
        //Check if the room has sensor and throws an exception
        if (!room.getSensorIds().isEmpty()) throw new RoomNotEmptyException("Room still has sensors"); 
            
        
        
        //Delete the specific room usig roomId
        DataStore.rooms.remove(roomId);

        //return status code
        return Response.status(204).build();

    }

}
