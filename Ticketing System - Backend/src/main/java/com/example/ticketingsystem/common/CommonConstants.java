/**
 * 
 */
package com.example.ticketingsystem.common;

/**
 * This file contains common constants for the application
 */
public class CommonConstants {
	
	// Response Messages
	public static final String MSG_ERROR_INSERT = "{\"msgType\": \"Error\", \"msg\": \"!!..Insert Failed.\"}";
	public static final String MSG_ERROR_UPDATE = "{\"msgType\": \"Error\", \"msg\": \"!!..Update Failed.\"}";
	public static final String MSG_ERROR_DELETE = "{\"msgType\": \"Error\", \"msg\": \"!!..Delete Failed.\"}";
	public static final String MSG_ERROR_COMMON = "{\"msgType\": \"Error\", \"msg\": \"!!..Operation Failed.\"}";
	public static final String MSG_NOT_FOUND = "{\"msgType\": \"Error\", \"msg\": \"!!..Record Not Found\"}";
	
	
	public static final String MSG_SUCCESS_INSERT = "{\"msgType\": \"Success\", \"msg\": \"Insert Successfull.!!\"}";
	public static final String MSG_SUCCESS_UPDATE = "{\"msgType\": \"Success\", \"msg\": \"Update Successfull.!!\"}";
	public static final String MSG_SUCCESS_DELETE = "{\"msgType\": \"Success\", \"msg\": \"Deletion Successfull.!!\"}";
	
	
	// Endpoints
	public static final String ENDPOINT_MOVIE="/api/movies";
	public static final String ENDPOINT_BUS="/api/buses";
	public static final String ENDPOINT_ROUTE="/api/routes";
	public static final String ENDPOINT_SCHEDULE="/api/schedules";
	public static final String ENDPOINT_DRIVER="/api/drivers";
	
	// CRUD Methods
	public static final String METHOD_VIEW_ALL="/view/all";
	public static final String METHOD_VIEW_ONE="/view/{id}";
	public static final String METHOD_NEW="/new";
	public static final String METHOD_UPDATE="/update";
	public static final String METHOD_DELETE="/delete/{id}";
}
