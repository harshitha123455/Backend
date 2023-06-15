package com.ibm.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Screen;
import com.ibm.entity.Shows;
import com.ibm.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
	
	List<TimeTable> findAllByScreen(Screen s);
	
	List<TimeTable> findAllByDate(Date date);
	
	TimeTable findByDateAndScreen(Date date, Screen screen);
	
	TimeTable findBySlot1OrSlot2OrSlot3OrSlot4(Shows s1, Shows s2, Shows s3, Shows s4);

}
