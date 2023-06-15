package com.ibm.service;

import java.sql.Date;
import java.util.List;

import com.ibm.entity.Screen;
import com.ibm.entity.TimeTable;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;

public interface TimeTableService {
	
	int save(TimeTable t) throws TimeTableAlreadyExistException;
	
	int update(TimeTable t) throws TimeTableNotFoundException;
	
	TimeTable searchById(int id) throws TimeTableNotFoundException;
	
	List<TimeTable> list();
	
	List<TimeTable> listByScreen(Screen s);
	
	List<TimeTable> listByDate(Date d);
	
	Boolean removeById(int id);
	
	

}
