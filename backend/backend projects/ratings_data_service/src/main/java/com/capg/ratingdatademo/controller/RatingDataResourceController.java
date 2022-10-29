package com.capg.ratingdatademo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ratingdatademo.entity.Rating;
import com.capg.ratingdatademo.entity.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResourceController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("users/{userId}")
	// public List<Rating> getUserRating(@PathVariable("userId") String userId) {
	public UserRating getUserRating(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3));

		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	}

}
