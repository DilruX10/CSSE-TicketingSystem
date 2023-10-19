/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem.services;

import java.util.List;

import com.example.ticketingsystem.model.Schedule;

public interface ScheduleService {
	
	public List<Schedule> allSchedules();
	
	public Schedule oneSchedule(String id);
	
	public boolean insertSchedule(Schedule schedule);
	
	public boolean updateSchedule(Schedule schedule);
	
	public boolean deleteSchedule(String id);
	
}
