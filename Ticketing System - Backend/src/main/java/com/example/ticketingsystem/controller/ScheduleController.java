/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketingsystem.common.CommonConstants;
import com.example.ticketingsystem.model.Schedule;
import com.example.ticketingsystem.services.ScheduleService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(CommonConstants.ENDPOINT_SCHEDULE)
public class ScheduleController {
	
	private ScheduleService scheduleService;
	private Gson gsonObj;
	
	/**
	 * Constructor for ScheduleController class
	 * 
	 * @param scheduleService
	 * @param gsonObj
	 */
	@Autowired 
	public ScheduleController(ScheduleService scheduleService) {
		
		this.scheduleService = scheduleService;
		this.gsonObj = new Gson();
	}
	
	/**
	 * This GET endpoint that return list of all schedules as a JSON object. 
	 * 
	 * @return responseEntity - A ResponseEntity containing list of all schedules and their details in JSON format and an HTTP status of OK.
	 * 
	 */
	@RequestMapping(value = CommonConstants.METHOD_VIEW_ALL, method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getAllSchedules() {
		
		// Uses the Google GSON library to convert the list of movies into a JSON string
		try {
			return new ResponseEntity<String>(gsonObj.toJson(scheduleService.allSchedules()), HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>(CommonConstants.MSG_ERROR_COMMON, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This method retrieve a specific schedule. 
	 * It is a GET endpoint that accepts a single path variable and produces JSON data.
	 * 
	 * @param id - given as a path variable
	 * @return responseEntity - A ResponseEntity containing the relevant schedule details in JSON format and an HTTP status of OK.
	 * 
	 */
	@RequestMapping(value = CommonConstants.METHOD_VIEW_ONE, method = RequestMethod.GET, produces="application/json")  
	public ResponseEntity<String> getOneSchedule(@PathVariable String id) {
		
		try {

			return new ResponseEntity<String>(gsonObj.toJson(scheduleService.oneSchedule(id)), HttpStatus.OK);
						 
		} catch (Exception e) {
			
			return new ResponseEntity<String>(CommonConstants.MSG_ERROR_COMMON, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * This method creates a new schedule. 
	 * It is a POST endpoint that consumes and produces JSON data.
	 * 
	 * @param movie - The schedule details provided in the request body.
	 * @return responseEntity - A ResponseEntity containing the created schedule details in JSON format and an HTTP status of OK.
	 * 
	 */
	@RequestMapping(value = CommonConstants.METHOD_NEW, method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule) {
		
		try {
			
			if(scheduleService.insertSchedule(schedule)) {
				
				return new ResponseEntity<String>(CommonConstants.MSG_SUCCESS_INSERT, HttpStatus.OK);
				
			} else {
				
				/*
				 * The 422 (Unprocessable Entity) status code means the server understands the content type of the request entity (hence a 415 (Unsupported Media Type) 
				 * status code is inappropriate), and the syntax of the request entity is correct (thus a 400 (Bad Request) status code is inappropriate) 
				 * but was unable to process the contained instructions. For example, this error condition may occur if an JSON request body contains 
				 * well-formed (i.e., syntactically correct), but semantically erroneous, data.
				 */				
				return new ResponseEntity<String>(CommonConstants.MSG_ERROR_INSERT, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		} catch (Exception e) {

			return new ResponseEntity<String>(CommonConstants.MSG_ERROR_INSERT, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This method is used to update the details of a schedule in the db.
	 * It is a PUT endpoint that consumes and produces JSON data.
	 *
	 * @param movie - The schedule object to be updated.
	 * @return responseEntity-  A ResponseEntity containing the updated schedule details in JSON format and an HTTP status of OK.
	 *
	 */
	@RequestMapping(value = CommonConstants.METHOD_UPDATE, method = RequestMethod.PUT, produces="application/json", consumes="application/json")
	public ResponseEntity<String> editSchedule(@RequestBody Schedule schedule) {
		
		try {
			
			if(scheduleService.updateSchedule(schedule)) {
				
				return new ResponseEntity<String>(CommonConstants.MSG_SUCCESS_UPDATE, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<String>(CommonConstants.MSG_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>(CommonConstants.MSG_ERROR_UPDATE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This method is used to delete a schedule by its ID.
	 * It is a DELETE endpoint that accepts a single path variable and produces JSON data.
	 *
	 * @param id - path variable
	 * @return responseEntity
	 *
	 */
	@RequestMapping(value = CommonConstants.METHOD_DELETE, method = RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<String> deleteSchedule(@PathVariable String id) {
		
		try {
			if(scheduleService.deleteSchedule(id)) {
				
				return new ResponseEntity<String>(CommonConstants.MSG_SUCCESS_DELETE, HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<String>(CommonConstants.MSG_NOT_FOUND, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<String>(CommonConstants.MSG_ERROR_DELETE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
