/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ticketingsystem.model.Schedule;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
	
	@Query("{$and:[ {'arrival' : ?0}, {'depature' : ?1}, {'routeId' : ?2}, {'vin' : ?3}]}")
    List<Schedule> getSchedulesByProperties(String arrival, String depature, String routeId, String vin);
}
