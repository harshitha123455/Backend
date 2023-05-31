package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
