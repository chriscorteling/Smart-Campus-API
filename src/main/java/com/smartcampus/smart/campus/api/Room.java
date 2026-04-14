/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author chrisrehan
 */


public class Room {
    
    private String id;
    private String name;
    
    private int capacity;
    
    private List<String> sensorIds = new ArrayList<>();
    
    //Empty Constructor
    public Room(){
        
    }
    
    public Room(String id, String name, int capacity, List<String> sensorId){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.sensorIds = sensorId;
    }
    
    //Getters for attributes
    
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public List<String> getSensorIds(){
        return sensorIds;
    }
    
    //Setters for the attributes
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    
    public void setSensorIds(List<String> sensorIds){
        this.sensorIds = sensorIds;
    }
         
    
}
