package com.capg.movinfodemo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movinfodemo.entity.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoResourceController {
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId, "Test name");
		
	}

}
