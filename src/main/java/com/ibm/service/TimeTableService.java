package com.ibm.service;

import java.sql.Date;
import java.util.List;

import com.ibm.entity.TimeTable;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;
import com.ibm.pojo.TimeTableRequest;

public interface TimeTableService {

	int save(TimeTable t) throws TimeTableAlreadyExistException;

	int update(TimeTable t) throws TimeTableNotFoundException;

	TimeTable searchById(int id) throws TimeTableNotFoundException;
	
	TimeTable searchByDateAndScreen(TimeTableRequest ttr) throws ScreenNotFoundException, TimeTableNotFoundException;
	
	TimeTable searchByShow(int id);

	List<TimeTable> list();

	List<TimeTable> listByScreenId(int id) throws ScreenNotFoundException;

	List<TimeTable> listByScreenName(String name) throws ScreenNotFoundException;

	List<TimeTable> listByDate(Date d);

	Boolean removeById(int id);

}
