package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Movie;
import com.ibm.entity.Shows;

public interface ShowRepository extends JpaRepository<Shows, Integer> {

	List<Shows> findAllByMovie(Movie m);
}
