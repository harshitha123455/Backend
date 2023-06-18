package com.ibm.service;

import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.entity.MovieHighlight;
import com.ibm.exception.MovieNotFoundException;

public interface MovieHighlightService {

	int save(int id, MultipartFile image) throws MovieNotFoundException;

	Movie getHighlight();
}
