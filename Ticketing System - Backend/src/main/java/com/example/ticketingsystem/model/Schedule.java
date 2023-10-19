/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.model;

import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.ticketingsystem.common.CommonUtil;

@Document(collection = "Schedule")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Schedule {
	
	@Id
	@Field(name="scheduleId")
	String scheduleId;
	
	@Field(name="routeId")
	String routeId;
	
	@Field(name="vin")
	String vin;
	
	@Field(name="arrival")
	String arrival;
	
	@Field(name="depature")
	String depature;

	/**
	 * Constructor for Schedule class
	 * 
	 * @param scheduleId
	 * @param routeId
	 * @param vin
	 * @param arrival
	 * @param depature
	 */
	public Schedule(String routeId, String vin, String arrival, String depature) {
		this.scheduleId = CommonUtil.generateId("S");
		this.routeId = routeId;
		this.vin = vin;
		this.arrival = arrival;
		this.depature = depature;
	}

	/**
	 * @return the scheduleId
	 */
	public String getScheduleId() {
		return scheduleId;
	}

	/**
	 * @return the routeId
	 */
	public String getRouteId() {
		return routeId;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @return the arrival
	 */
	public String getArrival() {
		return arrival;
	}

	/**
	 * @return the depature
	 */
	public String getDepature() {
		return depature;
	}
	

}
