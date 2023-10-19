package com.example.ticketingsystem.exception;

public class RecordNotFoundException extends Exception { 

    public RecordNotFoundException(String collectionName) {  
    	super(collectionName + " Col.: !!..Record Not Found.");  
    }  
}  