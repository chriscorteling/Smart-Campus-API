/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.smart.campus.api.exceptions;

/**
 *
 * @author chrisrehan
 */

//Part 5
public class SensorUnavailableException extends RuntimeException{
    public SensorUnavailableException(String message){
        super(message);
    }
}
