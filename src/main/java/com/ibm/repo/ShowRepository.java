package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Shows;

public interface ShowRepository extends JpaRepository<Shows, Integer> {

}
