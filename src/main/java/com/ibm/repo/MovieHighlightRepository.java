package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.MovieHighlight;

public interface MovieHighlightRepository extends JpaRepository<MovieHighlight, Integer> {

}
