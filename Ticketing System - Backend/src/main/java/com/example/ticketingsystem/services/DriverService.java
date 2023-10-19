/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Driver;

public interface DriverService {
	
	public List<Driver> allDrivers();
	
	public boolean insertDriver(Driver driver);
	
	public Driver oneDriver(String id) throws RecordNotFoundException;
	
	public boolean updateDriver(Driver driver);
	
	public boolean deleteDriver(String id);
}