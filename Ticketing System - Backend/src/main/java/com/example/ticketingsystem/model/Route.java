/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.model;

import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.ticketingsystem.common.CommonUtil;

@Document(collection = "Routes")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Route {
	
	@Id
	@Field(name="routeId")
	private String routeId;
	
	@Field(name="title") // route no.
	@Indexed(unique = true)
	String title; 
	
	@Field(name="origin")
	private String origin;
	
	@Field(name="destination")
	private String destination;

	/**
	 * Constructor for Route class
	 * 
	 * @param routeId
	 * @param origin
	 * @param destination
	 * @param stations
	 */
	public Route(String title, String origin, String destination) {
		this.routeId = CommonUtil.generateId("R");
		this.title = title;
		this.origin = origin;
		this.destination = destination;
	}
	
	/**
	 * getter for driverId
	 * 
	 * @return the driverId
	 */
	public String getRouteId() {
		return this.routeId;
	}
	
	/**
	 *  getter for origin
	 *  
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 *  getter for destination
	 *  
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
}
