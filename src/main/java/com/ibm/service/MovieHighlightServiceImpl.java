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
import com.ibm.entity.MovieHighlight;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.repo.MovieHighlightRepository;

/**
 * Implementation of the {@link MovieHighlightService} interface that manages
 * movie highlights.
 */
@Service
public class MovieHighlightServiceImpl implements MovieHighlightService {

	@Autowired
	private MovieHighlightRepository repo;

	@Autowired
	private MovieService service;

	private static final String uploadDir = "highlight";
	private static final String imageBaseUrl = "http://localhost:8880/";

	/**
	 * Saves the movie highlight with the provided ID and image file.
	 *
	 * @param id    The ID of the movie to set as the highlight.
	 * @param image The image file representing the movie highlight.
	 * @return The ID of the saved movie highlight.
	 * @throws MovieNotFoundException If the movie with the given ID is not found.
	 */
	@Override
	public int save(int id, MultipartFile image) throws MovieNotFoundException {
		MovieHighlight mh = new MovieHighlight();
		mh.setMovie(service.searchById(id));
		try {
			String fileExtension = StringUtils.getFilenameExtension(image.getOriginalFilename());
			String fileName = convertToLegalFilename(mh.getMovie().getName()) + "." + fileExtension;

			Files.createDirectories(Path.of(uploadDir));

			Path filePath = Path.of(uploadDir, fileName);
			Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			mh.setLargePosterUrl(imageBaseUrl + filePath.toString().replace("\\", "/"));
			repo.save(mh);

			return mh.getMovie().getId();
		} catch (IOException e) {
			return -1;
		}
	}

	/**
	 * Retrieves the currently set movie highlight.
	 *
	 * @return The movie that is currently set as the highlight, or null if no
	 *         highlight is set.
	 */
	@Override
	public List<MovieHighlight> getHighlight() {
		return repo.findAll();
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

	@Override
	public void removeById(int id) {
		repo.deleteById(id);
		
	}
}
