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


@Data
@AllArgsConstructor 
@NoArgsConstructor
@Document(collection = "Drivers")
public class Driver {
	
	@Id
	@Field(name="driverId")
	String driverId;
	
	@Field(name="name")
	String name;

	@Field(name="email")
	String email;

	/**
	 * Constructor for Driver class
	 * 
	 * @param driverId
	 * @param name
	 * @param contactNo
	 */
	public Driver(String name, String email) {
		this.driverId = CommonUtil.generateId("D");
		this.name = name;
		this.email = email;
	}

	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return this.driverId;
	}
	
	/**
	 * @return the driverId
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the driverId
	 */
	public String getEmail() {
		return this.email;
	}

}
