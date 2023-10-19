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

@Document(collection = "Buses")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Bus {
	
	@Id
	@Field(name="vin")
	private String vin; // Vehicle Identification Number
	
	@Field(name="busType")
	private String busType;
	
	@Field(name="numOfSeats")
	private int numOfSeats; 
	
	@Field(name="securityCode")
	@Indexed(unique = true)
	private int securityCode;
	
	@Field(name="busDriverId")
	private String busDriverId;

	/**
	 * Constructor for Bus class
	 * 
	 * @param vin -> Vehicle Identification Number
	 * @param busType
	 * @param numOfSeats
	 * @param securityCode
	 * @param busDriverId
	 */
	public Bus(String vin, String busType, int numOfSeats, String busDriverId) {
		this.vin = vin;
		this.busType = busType;
		this.numOfSeats = numOfSeats;
		this.securityCode = CommonUtil.generateSecurityCode();
		this.busDriverId = busDriverId;
	}
	
	/**
	 * @return the busId
	 */
	public String getBusId() {
		return this.vin;
	}
    
}