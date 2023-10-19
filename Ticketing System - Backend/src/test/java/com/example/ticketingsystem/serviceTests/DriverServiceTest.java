/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Driver;
import com.example.ticketingsystem.repository.DriverRepository;
import com.example.ticketingsystem.services.DriverServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {
	
	 @InjectMocks
	 private DriverServiceImplementation driverService;
	 
	 @Mock
	 private DriverRepository driverRepo;
	 
	 @BeforeEach
	  public void init() {
	    MockitoAnnotations.openMocks(this);
	    
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the allDrivers() method.
	  * Test case verifies that method correctly fetches all drivers from the repository and returns them in a list 
	  */
	 @Test
	 @Order(7)
	  void positiveTestToFindAllDrivers() {
		
		// Create the mocks we need and set up their behavior
	    List<Driver> list = new ArrayList<Driver>();
	    Driver dOne = new Driver("Korra", "korra@mail.com");
	    Driver dTwo = new Driver("Asami", "asami@mail.com");
	    Driver dThree = new Driver("Mako", "mako@mail.com");
	    Driver dFour = new Driver("Bolin", "bolin@mail.com");

	    list.add(dOne);
	    list.add(dTwo);
	    list.add(dThree);
	    list.add(dFour);

	    when(driverRepo.findAll()).thenReturn(list);

	    // test
	    List<Driver> driverList = driverService.allDrivers();
	    
	    assertNotNull(driverList);
	    assertEquals(4, driverList.size());
	    verify(driverRepo, times(1)).findAll();
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the insertDriver() method.
	  * Test case verifies that method correctly inserts the driver object to the database
	  */
	 @Test
	 @Order(6)
	  void positiveTestToCreateDriver() {
		 
	    Driver driver = new Driver("Korra", "korra@mail.com");

	    driverService.insertDriver(driver);
	        
	    verify(driverRepo, times(1)).insert(driver);
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the oneDriver() method.
	  * Test case verifies that method correctly fetches the matching driver record from the repository.
	  */
	 @Test
	 @Order(5)
	  void positiveTestToFindDriver() throws RecordNotFoundException {
		 
		// Create the mocks we need and set up their behavior
		Driver mockDriver = new Driver("Asami", "asami@mail.com");
		when(driverRepo.findById("D1234")).thenReturn(Optional.of(mockDriver));
		 
		// test
		Driver driver = driverService.oneDriver("D1234");
	    
		assertNotNull(driver);
	    assertEquals("Asami", driver.getName());
	    verify(driverRepo, times(1)).findById("D1234");
	    
	  }	
	 
	 /**
	  * This is positive test case that checks the normal behavior of the deleteDriver() method.
	  * Test case verifies that method correctly deletes the matching driver record from the repository.
	  */
	 @Test
	 @Order(4)
	  void positiveTestToDeleteDriver() {
		 
		// Create the mocks we need and set up their behavior		  
		when(driverRepo.existsById("D1234")).thenReturn(true);
		 
		// test
		driverService.deleteDriver("D1234");
	    
	    verify(driverRepo, times(1)).existsById("D1234");
	    verify(driverRepo, times(1)).deleteById("D1234");
	    
	  }
	 
	 /**
	  * This is negative test case that checks the normal behavior of the insertDriver() method when duplicate object is given.
	  * Test case verifies that method does not insert a new driver into the repository if a driver with the same name and email already exists.,
	  */
	 @Test
	 @Order(3)
	  void negativeTestToCreateDriver() {
		 
		// Create the mocks we need and set up their behavior	
		List<Driver> list = new ArrayList<Driver>();
		Driver mockDriver = new Driver("Asami", "asami@mail.com");
		list.add(mockDriver);
		
		when(driverRepo.getDriversByNameAndEmail("Asami", "asami@mail.com")).thenReturn(list);
		 
		// test
		Driver newDriver = new Driver("Asami", "asami@mail.com");
	    
		driverService.insertDriver(newDriver);
       
	    verify(driverRepo, times(0)).insert(newDriver); 
	  }
	 
	 /**
	  * This is negative test case that checks the behavior of the deleteDriver() method when invalid id is given.
	  * Test case verifies that method doesn't call the deleteById method if driver id is invalid.
	  */
	 @Test
	 @Order(2)
	  void negativeTestToDeleteDriver() {
		 
		// Create the mocks we need and set up their behavior		  
		when(driverRepo.existsById("D1234")).thenReturn(false);
		 
		// test
		driverService.deleteDriver("D1234");
	    
	    verify(driverRepo, times(1)).existsById("D1234");
	    verify(driverRepo, times(0)).deleteById("D1234");   
	  }
	 
	 /**
	  * This is negative test case that checks the behavior when given driver id is not in the db
	  * Test case verifies that method correctly throws custom exception "RecordNotFoundException" when given driver id is not in the db.
	  */
	 @Test
	 @Order(1)
	  void negativeTestToFindDriver() throws RecordNotFoundException {
		 
		// Create the mocks we need and set up their behavior
		when(driverRepo.findById("D1234")).thenThrow(new NoSuchElementException());
		 
		// test
		assertThrows(RecordNotFoundException.class, () -> driverService.oneDriver("D1234"), "Drivers Col.: !!..Record Not Found.");
	    verify(driverRepo, times(1)).findById("D1234");
	    
	  }	
}	
