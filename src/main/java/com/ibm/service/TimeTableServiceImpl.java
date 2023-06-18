package com.ibm.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Screen;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.Shows;
import com.ibm.entity.TimeTable;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.exception.TimeTableAlreadyExistException;
import com.ibm.exception.TimeTableNotFoundException;
import com.ibm.pojo.TimeTableRequest;
import com.ibm.repo.TimeTableRepository;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	private TimeTableRepository repo;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired ShowService showService;

	@Override
	public int save(TimeTable t) throws TimeTableAlreadyExistException {
		if (repo.findByDateAndScreen(t.getDate(), t.getScreen()) != null)
			throw new TimeTableAlreadyExistException(
					"Timetable for " + t.getScreen().getName() + " already exists on " + t.getDate());

		SeatingArrangement sa = new SeatingArrangement();
		Screen s = t.getScreen();
		sa.setTotalSeats(s.getTotalSeats());
		sa.setAvailableSeats(sa.getTotalSeats());
		sa.setAvailableNormalSeats(s.getNormalSeats());
		sa.setAvailablePremiumSeats(s.getPremiumSeats());
		sa.setAvailableExecutiveSeats(s.getExecutiveSeats());
		sa.setReserved(new Boolean[s.getTotalSeats()]);
		t.getSlot1().setSeatingArrangement(sa);
		t.getSlot2().setSeatingArrangement(new SeatingArrangement(sa));
		t.getSlot3().setSeatingArrangement(new SeatingArrangement(sa));
		t.getSlot4().setSeatingArrangement(new SeatingArrangement(sa));
		
		t.getSlot1().setTime("Morning Show");
		t.getSlot2().setTime("Noon Show");
		t.getSlot3().setTime("Evening Show");
		t.getSlot4().setTime("Night Show");
		
		repo.save(t);
		return t.getId();
	}
	
	@Override
	public int update(TimeTable t) throws TimeTableNotFoundException {
		searchById(t.getId()); // check whether the timetable exists
		repo.save(t);
		return t.getId();
	}

	@Override
	public List<TimeTable> list() {
		return repo.findAll();
	}

	@Override
	public List<TimeTable> listByScreenId(int id) throws ScreenNotFoundException {
		return repo.findAllByScreen(screenService.searchById(id));
	}
	
	@Override
	public List<TimeTable> listByScreenName(String name) throws ScreenNotFoundException {
		return repo.findAllByScreen(screenService.searchByName(name));
	}

	@Override
	public List<TimeTable> listByDate(Date d) {
		return repo.findAllByDate(d);
	}

	@Override
	public Boolean removeById(int id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public TimeTable searchById(int id) throws TimeTableNotFoundException {
		TimeTable t;
		try {
			t = repo.findById(id).get();
		} catch (Exception e) {
			throw new TimeTableNotFoundException(id);
		}
		return t;
	}

	@Override
	public TimeTable searchByDateAndScreen(TimeTableRequest ttr) throws ScreenNotFoundException, TimeTableNotFoundException {
		TimeTable t = repo.findByDateAndScreen(ttr.getDate(), screenService.searchById(ttr.getSid()));
		if (t == null)
			throw new TimeTableNotFoundException("No Timetable found for screen: " + screenService.searchById(ttr.getSid()).getName() + " on " + ttr.getDate());
		return t;
	}

	@Override
	public TimeTable searchByShow(int id) {
		Shows s = showService.searchById(id);
		return repo.findBySlot1OrSlot2OrSlot3OrSlot4(s, s, s, s);
	}

}
