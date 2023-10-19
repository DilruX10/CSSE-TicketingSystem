package com.example.ticketingsystem.controllerTests;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.ticketingsystem.controller.DriverController;
import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Driver;
import com.example.ticketingsystem.services.DriverService;

@WebMvcTest(DriverController.class)
public class DriverControllerTest {
	
	 @MockBean
     DriverService driverService;
	 
     @Autowired
     MockMvc mockMvc;
     
     /**
      * This is positive test case intended to verify the functionality of retrieving all drivers.
      * It mocks a list of drivers and checks if the correct status and number of drivers is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(5)
     public void positiveTestCaseToGetAllDrivers() throws Exception {
    	 
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
 	    
       Mockito.when(driverService.allDrivers()).thenReturn(list);
       
       // test
       this.mockMvc.perform(get("/api/drivers/view/all"))
       		.andExpect(status().isOk()).andExpect(content()
       		.contentType(MediaType.APPLICATION_JSON))
       		.andExpect(jsonPath("$", Matchers.hasSize(4)));
       verify(driverService, times(1)).allDrivers();
     }
     
     /**
      * This is positive test case intended to verify the functionality of retrieving a specific driver.
      * It mocks a one driver record and checks if the correct status and media type is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(4)
     public void positiveTestCaseToGetOneDriver() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior

 	    Driver dOne = new Driver("Korra", "korra@mail.com");
 	    
       Mockito.when(driverService.oneDriver("D1234")).thenReturn(dOne);
       
       // test
       this.mockMvc.perform(get("/api/drivers/view/D1234"))
       				.andExpect(status().isOk())
       				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       				.andExpect(content().json("{'name':'Korra'}"));
       
       verify(driverService, times(1)).oneDriver("D1234");
     }
     
     /**
      * This is positive test case intended to verify the functionality of deleting a specific driver.
      * It mocks the behavior of the deleteDriver() and checks if the correct status and media type is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(3)
     public void psotiveTestCaseToDeleteDriver() throws Exception {
    	 
       // Create the mocks we need and set up their behavior
       Mockito.when(driverService.deleteDriver("D1234")).thenReturn(true);
       
       // test
       this.mockMvc.perform(delete("/api/drivers/delete/D1234"))
       		.andExpect(status().isOk())
       		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       		.andExpect(content().json("{'msgType':'Success'}"))
   			.andExpect(content().json("{'msg':'Deletion Successfull.!!'}"));;
       verify(driverService, times(1)).deleteDriver("D1234");
     }
     
     /**
      * This is negative test case intended to verify the functionality of getOneDriver() when given driver id is not in the db.
      * It mocks the behavior of the oneDriver() and checks if the correct status and media type is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(2)
     public void negativeTestCaseToGetOneDriver() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior 	    
       Mockito.when(driverService.oneDriver("D1234")).thenThrow(new RecordNotFoundException("Drivers"));
       
       // test
       this.mockMvc.perform(get("/api/drivers/view/D1234"))
       			.andExpect(status().isInternalServerError())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'msgType':'Error'}"));
       
       verify(driverService, times(1)).oneDriver("D1234");
     }
     
     /**
      * This is negative test case intended to verify the functionality of deleteDriver() when given driver id is not in the db.
      * It mocks the behavior of the driverService.deleteDriver() and checks if the correct status and media type is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(1)
     public void negativeTestCaseToDeleteDriver() throws Exception {
    	 
       // Create the mocks we need and set up their behavior
       Mockito.when(driverService.deleteDriver("D1234")).thenReturn(false);
       
       // test
       this.mockMvc.perform(delete("/api/drivers/delete/D1234"))
       		.andExpect(status().isUnprocessableEntity())
       		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       		.andExpect(content().json("{'msgType':'Error'}"))
   		    .andExpect(content().json("{'msg':'!!..Record Not Found'}"));;
       verify(driverService, times(1)).deleteDriver("D1234");
     }
}
