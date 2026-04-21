/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api;

import com.smartcampus.smart.campus.api.models.SensorReading;
import com.smartcampus.smart.campus.api.models.Sensor;
import com.smartcampus.smart.campus.api.models.Room;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 *
 * @author chrisrehan
 */
public class DataStore {
    
    public static Map<String, Room> rooms = new ConcurrentHashMap<>();
    public static Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    public static Map<String, List<SensorReading>> sensorReadings = new ConcurrentHashMap<>();
    
}
