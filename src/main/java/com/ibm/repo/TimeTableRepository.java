package com.ibm.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Screen;
import com.ibm.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
	
	List<TimeTable> findAllByScreen(Screen s);
	
	List<TimeTable> findAllByDate(Date date);
	
	TimeTable findByDateAndScreen(Date date, Screen screen);

}
