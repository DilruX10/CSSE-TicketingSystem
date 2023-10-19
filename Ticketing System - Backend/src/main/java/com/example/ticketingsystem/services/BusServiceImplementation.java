/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Bus;
import com.example.ticketingsystem.repository.BusRepository;

@Service
public class BusServiceImplementation implements BusService {
	
	@Autowired
	private BusRepository busRepo;
	
	/**
	 * Overrides a interface method to return a list of all Bus entities from the database by using the findAll() method of the busRepo object
	 * 
	 * @return list of all Bus entities
	 */
	@Override
	public List<Bus> allBuses() {

		return busRepo.findAll();
	}
	
	/**
	 * Overrides a interface method to returns a Bus object from a repository based on the provided id.
	 * 
	 * @param String busId
	 * @return bus object
	 * @throws RecordNotFoundException 
	 */
	@Override
	public Bus oneBus(String id) throws RecordNotFoundException{
		
		try {
			return busRepo.findById(id).get();
			
		} catch (NoSuchElementException  e) {
			
			e.printStackTrace();			
			throw new RecordNotFoundException("Buses");
		} 
		
	}

	/**
	 *  Inserts a Bus object into a repository and returns a boolean value indicating success or failure.
	 *  
	 * @param bus object
	 * @return boolean value
	 */
	@Override
	public boolean insertBus(Bus bus) {

		try {
			busRepo.insert(bus);

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 *  updates a bus record in a repository.
	 *  It first checks if a bus with the given id exists in the repository.
	 *  If it does, it attempts to save the updated bus record to the repository
	 *  If an exception occurs during the save operation, it prints the stack trace and returns false. Otherwise, it returns true.
	 *  
	 * @param bus object
	 * @return boolean value
	 */
	@Override
	public boolean updateBus(Bus bus) {
		
		String id = bus.getBusId();
		boolean isValidBus = busRepo.existsById(id);
		
		if(isValidBus) {
			
			try {
				busRepo.save(bus);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
		return isValidBus;
	}
	
	
	/**
	 * This function checks if a bus with a given ID exists in the repository, then attempts to delete it. 
	 * If the deletion is successful, it returns true; otherwise, it returns false and logs any exceptions.
	 * 
	 * @param String busId
	 * @return boolean value 
	 */
	@Override
	public boolean deleteBus(String id) {

		boolean isValidBus = busRepo.existsById(id);

		if(isValidBus) {
			
			try {
				busRepo.deleteById(id);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}

		return isValidBus;
	}

}
