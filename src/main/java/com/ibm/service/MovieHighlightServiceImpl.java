package com.ibm.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.entity.MovieHighlight;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.repo.MovieHighlightRepository;

@Service
public class MovieHighlightServiceImpl implements MovieHighlightService {

	@Autowired
	private MovieHighlightRepository repo;

	@Autowired
	private MovieService service;

	private static final String name = "highlight";
	private static final String imageBaseUrl = "http://localhost:8880/";

	@Override
	public int save(int id, MultipartFile image) throws MovieNotFoundException {
		MovieHighlight mh = repo.findById(0).orElse(null);
	    if(mh == null)
	    	mh = new MovieHighlight();
	    mh.setMovie(service.searchById(id));

	    try {
	        String fileExtension = StringUtils.getFilenameExtension(image.getOriginalFilename());
	        // Generate the file name based on the movie name
	        String fileName = name + "." + fileExtension;

	        // Create the directory if it doesn't exist
	        Files.createDirectories(Path.of(name));

	        // Save the image file to the target directory
	        Path filePath = Path.of(name, fileName);
	        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	        mh.setLargePosterUrl(imageBaseUrl + filePath.toString().replace("\\", "/"));
	        mh.setId(0);
	    
	        // Save the new movie highlight with ID 0
	        repo.save(mh);
	    } catch (IOException e) {
	        return -1;
	    }

	    return mh.getMovie().getId();
	}

	@Override
	public Movie getHighlight() {
		return repo.findById(0).map(MovieHighlight::getMovie).orElse(null);
	}

}
