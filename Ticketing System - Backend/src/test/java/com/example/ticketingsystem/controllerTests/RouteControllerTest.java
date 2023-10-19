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

import com.example.ticketingsystem.controller.RouteController;
import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Route;
import com.example.ticketingsystem.services.RouteService;

@WebMvcTest(RouteController.class)
public class RouteControllerTest {
	 @MockBean
	 RouteService routeService;
	 
     @Autowired
     MockMvc mockMvc;
     
     /**
      * This is positive test case intended to verify the functionality of retrieving all routes.
      * It mocks a list of buses and checks if the correct status and number of buses is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(5)
     public void positiveTestCaseToGetAllRoutes() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior
    	 List<Route> list = new ArrayList<Route>();
    	 Route dOne = new Route("164", "Station A", "Station B");
 	    Route dTwo = new Route("165", "Station C", "Station D");

 	    list.add(dOne);
 	    list.add(dTwo);
 	    
       Mockito.when(routeService.allRoutes()).thenReturn(list);
       
       // test
       this.mockMvc.perform(get("/api/routes/view/all"))
       			.andExpect(status().isOk())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(jsonPath("$", Matchers.hasSize(2)));
       verify(routeService, times(1)).allRoutes();
     }
     
     /**
      * This is positive test case intended to verify the functionality of retrieving a specific route.
      * It mocks a one bus record and checks if the correct status, media type and content is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(4)
     public void positiveTestCaseToGetOneRoute() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior

    	 Route dOne = new Route("164", "Station A", "Station B");
 	    
    	 Mockito.when(routeService.oneRoute("R7845")).thenReturn(dOne);
       
       // test
       this.mockMvc.perform(get("/api/routes/view/R7845"))
       			.andExpect(status().isOk())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'title':'164'}"));
       
       verify(routeService, times(1)).oneRoute("R7845");
     }
     
     /**
      * This is positive test case intended to verify the functionality of deleting a specific route.
      * It mocks the behavior of the deleteRoute() and checks if the correct status and media type is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(3)
     public void psotiveTestCaseToDeleteRoute() throws Exception {
    	 
       // Create the mocks we need and set up their behavior
       Mockito.when(routeService.deleteRoute("R1234")).thenReturn(true);
       
       // test
       this.mockMvc.perform(delete("/api/routes/delete/R1234"))
       			.andExpect(status().isOk()).andExpect(content()
       			.contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'msgType':'Success'}"))
       			.andExpect(content().json("{'msg':'Deletion Successfull.!!'}"));
       verify(routeService, times(1)).deleteRoute("R1234");
     }
     
     /**
      * This is negative test case intended to verify the functionality of getOneRoute() when given id is not in the db.
      * It mocks the behavior of the oneRoute() and checks if the correct status, media type, and content is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(2)
     public void negativeTestCaseToGetOneRoute() throws Exception {
    	 
    	// Create the mocks we need and set up their behavior 	    
       Mockito.when(routeService.oneRoute("R1234")).thenThrow(new RecordNotFoundException("Routes"));
       
       // test
       this.mockMvc.perform(get("/api/routes/view/R1234"))
       			.andExpect(status().isInternalServerError())
       			.andExpect(content().json("{'msgType':'Error'}"));
       
       verify(routeService, times(1)).oneRoute("R1234");
     }
     
     /**
      * This is negative test case intended to verify the functionality of deleteRoute() when given route id is not in the db.
      * It mocks the behavior of the busService.deleteRoute() and checks if the correct status, media type and content is returned by the controller
      * 
      * @throws Exception
      */
     @Test
     @Order(1)
     public void negativeTestCaseToDeleteRoute() throws Exception {
    	 
       // Create the mocks we need and set up their behavior
       Mockito.when(routeService.deleteRoute("R1234")).thenReturn(false);
       
       // test
       this.mockMvc.perform(delete("/api/routes/delete/R1234"))
       			.andExpect(status().isUnprocessableEntity())
       			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
       			.andExpect(content().json("{'msgType':'Error'}"))
       		    .andExpect(content().json("{'msg':'!!..Record Not Found'}"));
       
       verify(routeService, times(1)).deleteRoute("R1234");
     }
}

