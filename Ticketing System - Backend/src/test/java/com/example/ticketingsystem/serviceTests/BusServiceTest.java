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
import com.example.ticketingsystem.model.Bus;
import com.example.ticketingsystem.repository.BusRepository;
import com.example.ticketingsystem.services.BusServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class BusServiceTest {
	
	 @InjectMocks
	 private BusServiceImplementation busService;
	 
	 @Mock
	 private BusRepository busRepo;
	 
	 @BeforeEach
	  public void init() {
	    MockitoAnnotations.openMocks(this);
	    
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the allBuses() method.
	  * Test case verifies that method correctly fetches all buses from the repository and returns them in a list 
	  */
	 @Test
	 @Order(6)
	  void positiveTestToFindAllBuses() {
		
		// Create the mocks we need and set up their behavior
	    List<Bus> list = new ArrayList<Bus>();
	    Bus dOne = new Bus("CAS-7845", "Luxury", 50, "D1234");
	    Bus dTwo = new Bus("CAS-8045", "Semi-Luxury", 100, "D1235");

	    list.add(dOne);
	    list.add(dTwo);

	    when(busRepo.findAll()).thenReturn(list);

	    // test
	    List<Bus> busList = busService.allBuses();
	    
	    assertNotNull(busList);
	    assertEquals(2, busList.size());
	    verify(busRepo, times(1)).findAll();
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the insertBus() method.
	  * Test case verifies that method correctly inserts the bus object to the database
	  */
	 @Test
	 @Order(5)
	  void positiveTestToCreateBus() {
		 
	    Bus bus = new Bus("CAS-7845", "Luxury", 50, "D1234");

	    busService.insertBus(bus);
	        
	    verify(busRepo, times(1)).insert(bus);
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the oneBus() method.
	  * Test case verifies that method correctly fetches the matching bus record from the repository.
	  */
	 @Test
	 @Order(4)
	  void positiveTestToFindBus() throws RecordNotFoundException{
		 
		// Create the mocks we need and set up their behavior
		Bus mockBus = new Bus("CAS-7845", "Luxury", 50, "D1234");
		when(busRepo.findById("CAS-7845")).thenReturn(Optional.of(mockBus));
		 
		// test
		Bus bus = busService.oneBus("CAS-7845");
	    
		assertNotNull(bus);
	    assertEquals("CAS-7845", bus.getBusId());
	    verify(busRepo, times(1)).findById("CAS-7845");
	    
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the deleteBus() method.
	  * Test case verifies that method correctly deletes the matching bus record from the repository.
	  */
	 @Test
	 @Order(3)
	  void positiveTestToDeleteBus() {
		 
		// Create the mocks we need and set up their behavior		  
		when(busRepo.existsById("CAS-7845")).thenReturn(true);
		 
		// test
		busService.deleteBus("CAS-7845");
	    
	    verify(busRepo, times(1)).existsById("CAS-7845");
	    verify(busRepo, times(1)).deleteById("CAS-7845");
	    
	  }
	 
	 /**
	  * This is negative test case that checks the behavior of the deleteBus() method when invalid id is given.
	  * Test case verifies that method doesn't call the deleteById method if bus id is invalid.
	  */
	 @Test
	 @Order(2)
	  void negativeTestToDeleteBus() {
		 
		// Create the mocks we need and set up their behavior		  
		when(busRepo.existsById("CAS-7845")).thenReturn(false);
		 
		// test
		busService.deleteBus("CAS-7845");
	    
	    verify(busRepo, times(1)).existsById("CAS-7845");
	    verify(busRepo, times(0)).deleteById("CAS-7845");   
	  }
	 
	 /**
	  * This is negative test case that checks the behavior when given vin is not in the db
	  * Test case verifies that method correctly throws custom exception "RecordNotFoundException" when given vin is not in the db.
	  */
	 @Test
	 @Order(1)
	  void negativeTestToFindBus() throws RecordNotFoundException {
		 
		// Create the mocks we need and set up their behavior
		when(busRepo.findById("CAS-7845")).thenThrow(new NoSuchElementException());
		 
		// test
		assertThrows(RecordNotFoundException.class, () -> busService.oneBus("CAS-7845"), "Bus Col.: !!..Record Not Found.");
	    verify(busRepo, times(1)).findById("CAS-7845");
	    
	  }	
}
