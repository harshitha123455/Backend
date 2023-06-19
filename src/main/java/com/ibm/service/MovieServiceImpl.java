package com.ibm.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.exception.MovieAlreadyExistException;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.repo.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repo;

	private static final String uploadDir = "images";
	private static final String imageBaseUrl = "http://localhost:8880/";

	@Override
	public int save(Movie m, MultipartFile image) throws MovieAlreadyExistException {
		Movie m1 = repo.findByName(m.getName());
		if (m1 != null && m1.getId() != m.getId()) // if movie is in db and their id's do not match
			throw new MovieAlreadyExistException(m1.getName());
		// Process the image file
		try {
			String fileExtension = StringUtils.getFilenameExtension(image.getOriginalFilename());
			// Generate the file name based on the movie name
			String fileName = convertToLegalFilename(m.getName()) + "." + fileExtension;

			// Create the directory if it doesn't exist
			Files.createDirectories(Path.of(uploadDir));

			// Save the image file to the target directory
			Path filePath = Path.of(uploadDir, fileName);
			Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			System.err.println(imageBaseUrl + filePath.toString());
			m.setImageUrl(imageBaseUrl + filePath.toString().replace("\\", "/"));
			repo.save(m);
			return m.getId();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public int update(Movie m) throws MovieNotFoundException {
		searchById(m.getId()); // check whether the movie exists
		repo.save(m);
		return m.getId();
	}

	@Override
	public List<Movie> list() {
		return repo.findAll();
	}

	@Override
	public Movie searchById(int id) throws MovieNotFoundException {
		Movie m;
		try {
			m = repo.findById(id).get();
		} catch (Exception e) {
			throw new MovieNotFoundException(id);
		}
		return m;
	}

	@Override
	public Movie searchByName(String name) throws MovieNotFoundException {
		Movie m = repo.findByName(name);
		if (m == null)
			throw new MovieNotFoundException(name);
		return m;
	}

	@Override
	public void removeById(int id) throws MovieNotFoundException {
		Movie m;
		try {
			m = repo.findById(id).get();
		} catch (Exception e) {
			throw new MovieNotFoundException(id);
		}
		repo.delete(m);
	}

	@Override
	public void removeByName(String name) throws MovieNotFoundException {
		Movie m = repo.findByName(name);
		if (m == null)
			throw new MovieNotFoundException(name);
		else
			repo.delete(m);
	}

	// Generate a legal file name from movie name
	public static String convertToLegalFilename(String input) {
	    String illegalChars = "[/\\\\:*?\"<>|]";
	    String replacement = "_";
	    int maxFilenameLength = 255;

	    // Remove or replace illegal characters
	    String filename = input.replaceAll(illegalChars, replacement);

	    // Replace whitespaces with underscores
	    filename = filename.replaceAll("\\s", replacement);

	    // Truncate or modify the string length
	    if (filename.length() > maxFilenameLength) {
	        filename = filename.substring(0, maxFilenameLength);
	    }

	    // Normalize the filename (optional)
	    filename = filename.trim().toLowerCase();

	    return filename;
	}


}
