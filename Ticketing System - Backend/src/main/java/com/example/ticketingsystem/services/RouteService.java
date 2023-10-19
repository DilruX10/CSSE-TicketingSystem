/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Route;

public interface RouteService {
	
	public List<Route> allRoutes();
	
	public Route oneRoute(String id) throws RecordNotFoundException;
	
	public boolean insertRoute(Route route);
	
	public boolean updateRoute(Route route);
	
	public boolean deleteRoute(String id);
}
