package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.SeatingArrangement;

public interface SeatingArrangementRepository extends JpaRepository<SeatingArrangement, Integer>{

}
