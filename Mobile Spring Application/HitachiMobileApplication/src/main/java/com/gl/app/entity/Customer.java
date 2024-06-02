package com.gl.app.entity;
// Convert  Customer class as Java17 Record with required fields
public record Customer (long customer_id, String dateOfBirth, String emailAddress, String firstName, String lastName, String idType, String address, String state){
    
}