/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketingsystem.model.Schedule;
import com.example.ticketingsystem.repository.ScheduleRepository;

@Service
public class ScheduleServiceImplementation implements ScheduleService {
	
	@Autowired 
	private ScheduleRepository scheduleRepo;
	
	/**
	 * Overrides a interface method to return a list of all Schedule entities from the database by using the findAll() method of the busRepo object
	 * 
	 * @return list of all Schedule entities
	 */
	@Override
	public List<Schedule> allSchedules() {
		
		return scheduleRepo.findAll();
	}

	/**
	 * Overrides a interface method to returns a Schedule object from a repository based on the provided id.
	 * 
	 * @param String driverId
	 * @return Schedule object
	 */	
	@Override
	public Schedule oneSchedule(String id) {
		
		return scheduleRepo.findById(id).get();
	}

	/**
	 * 
	 * This function, "checkSchedule," takes a Schedule object and an operation string as input. 
	 * It retrieves a list of schedules from a repository based on specific properties and checks for their existence. 
	 * If the operation is "i" (insert), it returns true if the list is empty. 
	 * If the operation is "u" (update), it returns true if the list is empty or if the first schedule in the list has the same ScheduleId as the provided one.
	 * Otherwise, it returns false.
	 * 
	 * @param schedule
	 * @param ops
	 * @return boolean
	 */
	public boolean checkSchedule(Schedule schedule, String ops) {
		
		String id = schedule.getScheduleId();
		String route = schedule.getRouteId();
		String vin = schedule.getVin();
		String arrival = schedule.getArrival();
		String depature = schedule.getDepature();
			
		List<Schedule> scheduleList = scheduleRepo.getSchedulesByProperties(arrival, depature, route, vin);

		boolean isEmpty = scheduleList.isEmpty();
		
		if(ops.equalsIgnoreCase("i"))
			 return (isEmpty);
			
		if(ops.equalsIgnoreCase("u"))
			return (isEmpty)? true : (scheduleList.get(0).getScheduleId().equalsIgnoreCase(id));
		
		return false;
	}
	
	/**
	 *  Checks the schedule's validity, 
	 *  then attempts the insertion and returns true if successful, 
	 *  or false in case of errors
	 *  
	 * @param schedule object
	 * @return boolean value
	 */
	@Override
	public boolean insertSchedule(Schedule schedule) {
		
		boolean isValidSchedule = checkSchedule(schedule, "i");
				
		if(isValidSchedule){
			
			try {
				
				scheduleRepo.insert(schedule);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}
		
		return isValidSchedule;
		
	}
	
	/**
	 *  updates a schedule record in a repository.
	 *  It first checks if a schedule with the given id exists in the repository.
	 *  If it does, it attempts to save the updated schedule record to the repository
	 *  If an exception occurs during the save operation, it prints the stack trace and returns false. Otherwise, it returns true.
	 *  
	 * @param driver object
	 * @return boolean value
	 */
	@Override
	public boolean updateSchedule(Schedule schedule) {
		
		String id = schedule.getScheduleId();
		boolean isValidSchedule = (scheduleRepo.existsById(id) && checkSchedule(schedule, "u"));
		
		
		if(isValidSchedule){
			
			try {
				scheduleRepo.save(schedule);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}

		return isValidSchedule;
	}

	/**
	 * This function checks if a schedule with a given ID exists in the repository, then attempts to delete it. 
	 * If the deletion is successful, it returns true; otherwise, it returns false and logs any exceptions.
	 * 
	 * @param String driverId
	 * @return boolean value 
	 */
	@Override
	public boolean deleteSchedule(String id) {
		
		boolean isValidSchedule = scheduleRepo.existsById(id);

		if(isValidSchedule){
			
			try {
				scheduleRepo.deleteById(id);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				return false;
			}
		}

		return isValidSchedule;
	}

}
