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

/**
 * Implementation of the {@link MovieService} interface that provides operations for
 * managing movies.
 */
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repo;

	private static final String uploadDir = "images";
	private static final String imageBaseUrl = "http://localhost:8880/";

	/**
	 * Saves a new movie with the provided details and image.
	 *
	 * @param movie the movie entity to be saved
	 * @param image the image file of the movie
	 * @return the ID of the saved movie
	 * @throws MovieAlreadyExistException if a movie with the same name already
	 *                                    exists
	 */
	@Override
	public int save(Movie movie, MultipartFile image) throws MovieAlreadyExistException {
		Movie existingMovie = repo.findByName(movie.getName());
		if (existingMovie != null && existingMovie.getId() != movie.getId()) {
			throw new MovieAlreadyExistException(existingMovie.getName());
		}

		try {
			String fileExtension = StringUtils.getFilenameExtension(image.getOriginalFilename());
			String fileName = convertToLegalFilename(movie.getName()) + "." + fileExtension;

			Files.createDirectories(Path.of(uploadDir));

			Path filePath = Path.of(uploadDir, fileName);
			Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			movie.setImageUrl(imageBaseUrl + filePath.toString().replace("\\", "/"));
			repo.save(movie);

			return movie.getId();
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * Updates an existing movie with the provided details.
	 *
	 * @param movie the updated movie entity
	 * @return the ID of the updated movie
	 * @throws MovieNotFoundException if the movie with the specified ID is not
	 *                                found
	 */
	@Override
	public int update(Movie movie) throws MovieNotFoundException {
		searchById(movie.getId());
		repo.save(movie);
		return movie.getId();
	}

	/**
	 * Retrieves a list of all movies.
	 *
	 * @return a list of Movie entities
	 */
	@Override
	public List<Movie> list() {
		return repo.findAll();
	}

	/**
	 * Searches for a movie by ID.
	 *
	 * @param id the ID of the movie to search for
	 * @return the found Movie entity
	 * @throws MovieNotFoundException if the movie with the specified ID is not
	 *                                found
	 */
	@Override
	public Movie searchById(int id) throws MovieNotFoundException {
		try {
			return repo.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
		} catch (Exception e) {
			throw new MovieNotFoundException(id);
		}
	}

	/**
	 * Searches for a movie by name.
	 *
	 * @param name the name of the movie to search for
	 * @return the found Movie entity
	 * @throws MovieNotFoundException if the movie with the specified name is not
	 *                                found
	 */
	@Override
	public Movie searchByName(String name) throws MovieNotFoundException {
		Movie movie = repo.findByName(name);
		if (movie == null) {
			throw new MovieNotFoundException(name);
		}
		return movie;
	}

	/**
	 * Removes a movie by ID.
	 *
	 * @param id the ID of the movie to remove
	 * @throws MovieNotFoundException if the movie with the specified ID is not
	 *                                found
	 */
	@Override
	public void removeById(int id) throws MovieNotFoundException {
		Movie movie = searchById(id);
		repo.delete(movie);
	}

	/**
	 * Removes a movie by name.
	 *
	 * @param name the name of the movie to remove
	 * @throws MovieNotFoundException if the movie with the specified name is not
	 *                                found
	 */
	@Override
	public void removeByName(String name) throws MovieNotFoundException {
		Movie movie = searchByName(name);
		repo.delete(movie);
	}

	/**
	 * Converts the input string to a legal file name.
	 *
	 * @param input the input string to convert
	 * @return the converted legal file name
	 */
	public static String convertToLegalFilename(String input) {
		String illegalChars = "[/\\\\:*?\"<>|]";
		String replacement = "_";
		int maxFilenameLength = 255;

		String filename = input.replaceAll(illegalChars, replacement);
		filename = filename.replaceAll("\\s", replacement);

		if (filename.length() > maxFilenameLength) {
			filename = filename.substring(0, maxFilenameLength);
		}

		filename = filename.trim().toLowerCase();

		return filename;
	}
}
