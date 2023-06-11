package com.ibm.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Screen;
import com.ibm.entity.SeatingArrangement;
import com.ibm.entity.TimeTable;
import com.ibm.repo.TimeTableRepository;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	private TimeTableRepository repo;

	@Override
	public int save(TimeTable t) {
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
		repo.save(t);
		return t.getId();
	}

	@Override
	public List<TimeTable> list() {
		return repo.findAll();
	}

	@Override
	public List<TimeTable> listByScreen(Screen s) {
		return repo.findAllByScreen(s);
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
	public TimeTable searchById(int id) {
		return repo.findById(id).get();
	}

}
