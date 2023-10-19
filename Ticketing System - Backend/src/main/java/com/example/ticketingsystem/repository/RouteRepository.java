/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.ticketingsystem.model.Route;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
	
	@Query("{$and:[ {'origin' : ?0}, {'destination' : ?1}]}")
    List<Route> getRoutesByOriginAndDestination(String origin, String destination);
}
