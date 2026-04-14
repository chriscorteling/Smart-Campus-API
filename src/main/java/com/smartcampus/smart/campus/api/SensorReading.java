package com.smartcampus.smart.campus.api;

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
        
    }
    
    public SensorReading(String id, long timestamp, double value){
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
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
