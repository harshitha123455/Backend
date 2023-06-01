package com.ibm.service;

import java.util.List;

import com.ibm.entity.SeatingArrangement;

public interface SeatingArrangementService {

	int save(SeatingArrangement sa);

	List<SeatingArrangement> list();

	SeatingArrangement searchById(int id);

	Boolean remove(int id);
	
	Boolean bookSeat(int id, int x, int y);
}
