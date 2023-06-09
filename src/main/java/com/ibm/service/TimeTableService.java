package com.ibm.service;

import java.sql.Date;
import java.util.List;

import com.ibm.entity.Screen;
import com.ibm.entity.TimeTable;

public interface TimeTableService {
	
	int save(TimeTable t);
	
	TimeTable searchById(int id);
	
	List<TimeTable> list();
	
	List<TimeTable> listByScreen(Screen s);
	
	List<TimeTable> listByDate(Date d);
	
	Boolean removeById(int id);
	
	

}