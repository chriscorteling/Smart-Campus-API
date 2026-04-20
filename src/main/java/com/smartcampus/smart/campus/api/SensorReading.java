package com.smartcampus.smart.campus.api;

import java.util.UUID;

/**
 *
 * @author chrisrehan
 */
public class SensorReading {
    
    private String id;
    private long timestamp;
    
    private double value;
    
    
    //Empty Constructor
    public SensorReading(){
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }
    
    public SensorReading(double value){
        this.value = value;
             
        //Auto generations
        this.id = UUID.randomUUID().toString(); 
        this.timestamp = System.currentTimeMillis();
        
    }
        
    //Getters for the attributes
    public String getId(){
        return id;
    }
    
    public long getTimestamp(){
        return timestamp;
    }
    
    public double getValue(){
        return value;
    }
    
    //Setters for the attributes
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }
    
    public void setValue(double value){
        this.value = value;
   
    }
    
}
