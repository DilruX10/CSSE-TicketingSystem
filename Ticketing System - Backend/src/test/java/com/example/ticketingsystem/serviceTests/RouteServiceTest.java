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
import com.example.ticketingsystem.model.Route;
import com.example.ticketingsystem.repository.RouteRepository;
import com.example.ticketingsystem.services.RouteServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {
	
	 @InjectMocks
	 private RouteServiceImplementation routeService;
	 
	 @Mock
	 private RouteRepository routeRepo;
	 
	 @BeforeEach
	  public void init() {
	    MockitoAnnotations.openMocks(this);
	    
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the allRoutes() method.
	  * Test case verifies that method correctly fetches all routes from the repository and returns them in a list 
	  */
	 @Test
	 @Order(7)
	  void positiveTestToFindAllRoutes() {
		
		// Create the mocks we need and set up their behavior
	    List<Route> list = new ArrayList<Route>();
	    Route dOne = new Route("164", "Salmal Uyana", "Purahala");
	    Route dTwo = new Route("177", "Kaduwela", "Kollupitiya");

	    list.add(dOne);
	    list.add(dTwo);

	    when(routeRepo.findAll()).thenReturn(list);

	    //test
	    List<Route> routeList = routeService.allRoutes();
	    
	    assertNotNull(routeList);
	    assertEquals(2, routeList.size());
	    verify(routeRepo, times(1)).findAll();
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the insertRoute() method.
	  * Test case verifies that method correctly inserts the route object to the database
	  */
	 @Test
	 @Order(6)
	  void positiveTestToCreateRoute() {
		 
		 Route route = new Route("164", "Salmal Uyana", "Purahala");

		 routeService.insertRoute(route);
	        
	     verify(routeRepo, times(1)).insert(route);
	  }
	 
	 /**
	  * This is positive test case that checks the normal behavior of the oneDriver() method.
	  * Test case verifies that method correctly fetches the matching driver record from the repository.
	  */
	 @Test
	 @Order(5)
	  void positiveTestToFindRoute() throws RecordNotFoundException{
		 
		// Create the mocks we need and set up their behavior
		Route mockRoute = new Route("164", "Salmal Uyana", "Purahala");
		when(routeRepo.findById("R1234")).thenReturn(Optional.of(mockRoute));
		 
		// test
		Route route = routeService.oneRoute("R1234");
	    
		assertNotNull(route);
	    assertEquals("Salmal Uyana", route.getOrigin());
	    verify(routeRepo, times(1)).findById("R1234");
	    
	  }	
	 
	 /**
	  * This is positive test case that checks the normal behavior of the deleteRoute() method.
	  * Test case verifies that method correctly deletes the matching route record from the repository.
	  */
	 @Test
	 @Order(4)
	  void positiveTestToDeleteRoute() {
		 
		// Create the mocks we need and set up their behavior		  
		when(routeRepo.existsById("R1234")).thenReturn(true);
		 
		// test
		routeService.deleteRoute("R1234");
	    
	    verify(routeRepo, times(1)).existsById("R1234");
	    verify(routeRepo, times(1)).deleteById("R1234");
	    
	  }
	 
	 /**
	  * This is negative test case that checks the normal behavior of the insertRoute() method when duplicate object is given.
	  * Test case verifies that method does not insert a new route into the repository if a route with the same name and email already exists.,
	  */
	 @Test
	 @Order(3)
	  void negativeTestToCreateRoute() {
		 
		// Create the mocks we need and set up their behavior	
		List<Route> list = new ArrayList<Route>();
		Route mockRoute = new Route("164", "Salmal Uyana", "Purahala");
		list.add(mockRoute);
		
		when(routeRepo.getRoutesByOriginAndDestination("Salmal Uyana", "Purahala")).thenReturn(list);
		 
		// test
		Route newRoute = new Route("164", "Salmal Uyana", "Purahala");
	    
		routeService.insertRoute(newRoute);
       
	    verify(routeRepo, times(0)).insert(newRoute); 
	  }
	 
	 /**
	  * This is negative test case that checks the behavior of the deleteRoute() method when invalid id is given.
	  * Test case verifies that method doesn't call the deleteById method if route id is invalid.
	  */
	 @Test
	 @Order(2)
	  void negativeTestToDeleteRoute() {
		 
		// Create the mocks we need and set up their behavior		  
		when(routeRepo.existsById("R1234")).thenReturn(false);
		 
		// test
		routeService.deleteRoute("R1234");
	    
	    verify(routeRepo, times(1)).existsById("R1234");
	    verify(routeRepo, times(0)).deleteById("R1234");   
	  }
	 
	 /**
	  * This is negative test case that checks the behavior when given route id is not in the db
	  * Test case verifies that method correctly throws custom exception "RecordNotFoundException" when given route id is not in the db.
	  *
	  * @throws RecordNotFoundException
	  */
	 @Test
	 @Order(1)
	  void negativeTestToFindRoute() throws RecordNotFoundException {
		 
		// Create the mocks we need and set up their behavior
		when(routeRepo.findById("R1234")).thenThrow(new NoSuchElementException());
		 
		// test
		assertThrows(RecordNotFoundException.class, () -> routeService.oneRoute("R1234"), "Routes Col.: !!..Record Not Found.");
	    verify(routeRepo, times(1)).findById("R1234");
	    
	  }	
}
