package com.ibm.service;

import org.springframework.web.multipart.MultipartFile;

import com.ibm.entity.Movie;
import com.ibm.exception.MovieNotFoundException;

/**
 * The interface for managing movie highlights.
 */
public interface MovieHighlightService {

    /**
     * Saves the movie highlight image for the specified movie.
     *
     * @param id    the ID of the movie
     * @param image the image file to be saved as the movie highlight
     * @return the ID of the saved movie highlight
     * @throws MovieNotFoundException if the movie with the specified ID is not found
     */
    int save(int id, MultipartFile image) throws MovieNotFoundException;

    /**
     * Retrieves the current movie highlight.
     *
     * @return the movie highlight entity
     */
    Movie getHighlight();
}
