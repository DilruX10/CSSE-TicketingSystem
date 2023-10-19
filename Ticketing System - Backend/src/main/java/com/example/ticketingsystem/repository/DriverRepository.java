/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.ticketingsystem.model.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
	
	@Query("{$and:[ {'name' : ?0}, {'email' : ?1} ]}")
    List<Driver> getDriversByNameAndEmail(String name, String email);
}
