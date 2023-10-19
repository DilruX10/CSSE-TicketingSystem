/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Bus;

public interface BusService {
	
	public List<Bus> allBuses();
	
	public Bus oneBus(String id) throws RecordNotFoundException;
	
	public boolean insertBus(Bus bus);
	
	public boolean updateBus(Bus bus);
	
	public boolean deleteBus(String id);
}
