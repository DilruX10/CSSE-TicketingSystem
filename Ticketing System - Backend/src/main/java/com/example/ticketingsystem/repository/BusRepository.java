/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketingsystem.model.Bus;

@Repository
public interface BusRepository  extends MongoRepository<Bus, String> {

}
