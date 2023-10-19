/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketingsystem.exception.RecordNotFoundException;
import com.example.ticketingsystem.model.Route;
import com.example.ticketingsystem.repository.RouteRepository;

@Service
public class RouteServiceImplementation implements RouteService {
	
	@Autowired 
	private RouteRepository routeRepo;
	
	/**
	 * Overrides a interface method to return a list of all route entities from the database by using the findAll() method of the busRepo object
	 * 
	 * @return list of all route entities
	 */
	@Override
	public List<Route> allRoutes() {

		return routeRepo.findAll();
	}
	
	/**
	 * Overrides a interface method to returns a route object from a repository based on the provided id.
	 * 
	 * @param String routeId
	 * @return route object	 * 
	 * @throws RecordNotFoundException 
	 */	
	@Override
	public Route oneRoute(String id)  throws RecordNotFoundException {
		
		try {
			return routeRepo.findById(id).get();
			
		} catch (NoSuchElementException  e) {
			
			e.printStackTrace();
			throw new RecordNotFoundException("Routes");
		} 
	}
	
	/**
	 * This verifies the existence of a route by origin and destination, 
	 * and returns a boolean value based on the operation specified in ops.
	 * 
	 * @param route
	 * @param ops
	 * @return boolean
	 */
	public boolean checkRoutesByOriginAndDestination(Route route, String ops) {
		
		String id = route.getRouteId();
		String origin = route.getOrigin();
		String destination = route.getDestination();
		
		List<Route> routesList = routeRepo.getRoutesByOriginAndDestination(origin, destination);
		
		boolean isEmpty = routesList.isEmpty();
		
		if(ops.equalsIgnoreCase("i"))
			return (isEmpty);
		
		if(ops.equalsIgnoreCase("u"))
			return (isEmpty)? true : (routesList.get(0).getRouteId().equalsIgnoreCase(id));
		
		return false;
	}
	
	/**
	 *  Inserts a route object into a repository and returns a boolean value indicating success or failure.
	 *  
	 * @param route object
	 * @return boolean value
	 */
	@Override
	public boolean insertRoute(Route route) {
		
		boolean isValidRoute = checkRoutesByOriginAndDestination(route, "i");
		
		if(isValidRoute){
			
			try {
				routeRepo.insert(route);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
		return isValidRoute;
	}

	/**
	 *  updates a route record in a repository.
	 *  It first checks if a route with the given id exists in the repository.
	 *  If it does, it attempts to save the updated route record to the repository
	 *  If an exception occurs during the save operation, it prints the stack trace and returns false. Otherwise, it returns true.
	 *  
	 * @param route object
	 * @return boolean value
	 */
	@Override
	public boolean updateRoute(Route route) {
	
		String id = route.getRouteId();
		boolean isValidRoute = (routeRepo.existsById(id) && checkRoutesByOriginAndDestination(route, "u"));

		if(isValidRoute){
			
			try {
				routeRepo.save(route);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}

		return isValidRoute;
	}
	
	/**
	 * This function checks if a route with a given ID exists in the repository, then attempts to delete it. 
	 * If the deletion is successful, it returns true; otherwise, it returns false and logs any exceptions.
	 * 
	 * @param String driverId
	 * @return boolean value 
	 */
	@Override
	public boolean deleteRoute(String id) {

		boolean isValidRoute = routeRepo.existsById(id);

		if(isValidRoute){
			
			try {
				routeRepo.deleteById(id);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}

		return isValidRoute;
	}

}
