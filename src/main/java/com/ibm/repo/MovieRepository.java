package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	Movie findByName(String name);

}
