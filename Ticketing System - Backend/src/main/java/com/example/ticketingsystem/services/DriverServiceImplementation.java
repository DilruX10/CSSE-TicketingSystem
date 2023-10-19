/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Driver;
import com.example.ticketingsystem.repository.DriverRepository;

@Service
public class DriverServiceImplementation implements DriverService {

	@Autowired 
	private DriverRepository driverRepo;
	
	/**
	 * Overrides a interface method to return a list of all drivers entities from the database by using the findAll() method of the busRepo object
	 * 
	 * @return list of all driver entities
	 */
	@Override
	public List<Driver> allDrivers() {
		
		return driverRepo.findAll();	
	}
	
	/**
	 * Overrides a interface method to returns a driver object from a repository based on the provided id.
	 * 
	 * @param String driverId
	 * @return driver object
	 * @throws RecordNotFoundException 
	 */	
	@Override
	public Driver oneDriver(String id) throws RecordNotFoundException {

		try {
			return driverRepo.findById(id).get();
			
		} catch (NoSuchElementException  e) {
			
			e.printStackTrace();
			throw new RecordNotFoundException("Drivers");
		} 
	}
	
	/**
	 * checks if a driver exists in the system based on their name and email. 
	 * It returns true if the driver does not exist (for insert/"i" operation) or 
	 * if the driver exists with the same ID (for update/"u" operation), and false otherwise.	
	 * 
	 * @param driver
	 * @param ops -> operation
	 * @return boolean
	 */
	public boolean checkDriversByNameAndEmail(Driver driver, String ops) {
		
		String id = driver.getDriverId();
		String name = driver.getName();
		String mail = driver.getEmail();
		List<Driver> driversList = driverRepo.getDriversByNameAndEmail(name, mail);
		
		boolean isEmpty = driversList.isEmpty();		

		if(ops.equalsIgnoreCase("i"))
			return (isEmpty);
		
		if(ops.equalsIgnoreCase("u"))
			return (isEmpty)? true : (driversList.get(0).getDriverId().equalsIgnoreCase(id));
		
		return false;
		
	}
	
	/**
	 *  Inserts a driver object into a repository and returns a boolean value indicating success or failure.
	 *  
	 * @param driver object
	 * @return boolean value
	 */
	@Override
	public boolean insertDriver(Driver driver) {
		
		boolean isValidDriver = checkDriversByNameAndEmail(driver, "i");
		
		if(isValidDriver){			
			driverRepo.insert(driver);
		} 
		
		return isValidDriver;
	}
	
	/**
	 *  updates a driver record in a repository.
	 *  It first checks if a driver with the given id exists in the repository.
	 *  If it does, it attempts to save the updated driver record to the repository
	 *  If an exception occurs during the save operation, it prints the stack trace and returns false. Otherwise, it returns true.
	 *  
	 * @param driver object
	 * @return boolean value
	 */
	@Override
	public boolean updateDriver(Driver driver) {
		
		String id = driver.getDriverId();
		boolean isValidDriver = (driverRepo.existsById(id) && checkDriversByNameAndEmail(driver, "u"));

		if(isValidDriver)
				driverRepo.save(driver);

		return isValidDriver;
	}
	
	/**
	 * This function checks if a driver with a given ID exists in the repository, then attempts to delete it. 
	 * If the deletion is successful, it returns true; otherwise, it returns false and logs any exceptions.
	 * 
	 * @param String driverId
	 * @return boolean value 
	 */
	@Override
	public boolean deleteDriver(String id) {
		
		boolean isValidDriver = driverRepo.existsById(id);
				
		if(isValidDriver) 			
			driverRepo.deleteById(id);

		return isValidDriver;
	}

}
