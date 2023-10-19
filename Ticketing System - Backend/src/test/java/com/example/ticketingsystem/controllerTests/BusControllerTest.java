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

import com.example.ticketingsystem.controller.BusController;
import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Bus;
import com.example.ticketingsystem.services.BusService;

@WebMvcTest(BusController.class)
public class BusControllerTest {
	
	 @MockBean
	 BusService busService;
	 
     @Autowired
     MockMvc mockMvc;
     
     /**
      * This is positive test case intended to verify the functionality of retrieving all buses.
      * It mocks a list of buses and checks if the correct status and number of buses is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(5)
     public void positiveTestCaseToGetAllBuses() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior
    	 List<Bus> list = new ArrayList<Bus>();
 	    Bus dOne = new Bus("CAS-7845", "Luxury", 50, "D1234");
 	    Bus dTwo = new Bus("CAS-8045", "Semi-Luxury", 100, "D1235");

 	    list.add(dOne);
 	    list.add(dTwo);
 	    
       Mockito.when(busService.allBuses()).thenReturn(list);
       
       // test
       this.mockMvc.perform(get("/api/buses/view/all"))
       			.andExpect(status().isOk())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(jsonPath("$", Matchers.hasSize(2)));
       verify(busService, times(1)).allBuses();
     }
     
     /**
      * This is positive test case intended to verify the functionality of retrieving a specific bus.
      * It mocks a one bus record and checks if the correct status, media type and content is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(4)
     public void positiveTestCaseToGetOneBus() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior

    	 Bus dOne = new Bus("CAS-7845", "Luxury", 50, "D1234");
 	    
    	 Mockito.when(busService.oneBus("CAS-7845")).thenReturn(dOne);
       
       // test
       this.mockMvc.perform(get("/api/buses/view/CAS-7845"))
       			.andExpect(status().isOk())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'vin':'CAS-7845'}"));
       
       verify(busService, times(1)).oneBus("CAS-7845");
     }
     
     /**
      * This is positive test case intended to verify the functionality of deleting a specific bus.
      * It mocks the behavior of the deleteBus() and checks if the correct status and media type is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(3)
     public void psotiveTestCaseToDeleteBus() throws Exception {
    	 
       // Create the mocks we need and set up their behavior
       Mockito.when(busService.deleteBus("CAS-1234")).thenReturn(true);
       
       // test
       this.mockMvc.perform(delete("/api/buses/delete/CAS-1234"))
       			.andExpect(status().isOk()).andExpect(content()
       			.contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'msgType':'Success'}"))
       			.andExpect(content().json("{'msg':'Deletion Successfull.!!'}"));
       verify(busService, times(1)).deleteBus("CAS-1234");
     }
     
     /**
      * This is negative test case intended to verify the functionality of getOneBus() when given driver id is not in the db.
      * It mocks the behavior of the oneBus() and checks if the correct status, media type, and content is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(2)
     public void negativeTestCaseToGetOneBus() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior 	    
       Mockito.when(busService.oneBus("CAS-1234")).thenThrow(new RecordNotFoundException("Buses"));
       
       // test
       this.mockMvc.perform(get("/api/buses/view/CAS-1234"))
       			.andExpect(status().isInternalServerError())
       			.andExpect(content().json("{'msgType':'Error'}"));
       
       verify(busService, times(1)).oneBus("CAS-1234");
     }
     
     /**
      * This is negative test case intended to verify the functionality of deleteBus() when given bus vin is not in the db.
      * It mocks the behavior of the busService.deleteBus() and checks if the correct status, media type and content is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(1)
     public void negativeTestCaseToDeleteBus() throws Exception {
    	 
       // Create the mocks we need and set up their behavior
       Mockito.when(busService.deleteBus("CAS-1234")).thenReturn(false);
       
       // test
       this.mockMvc.perform(delete("/api/buses/delete/CAS-1234"))
       			.andExpect(status().isUnprocessableEntity())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'msgType':'Error'}"))
       		    .andExpect(content().json("{'msg':'!!..Record Not Found'}"));
       verify(busService, times(1)).deleteBus("CAS-1234");
     }
}
