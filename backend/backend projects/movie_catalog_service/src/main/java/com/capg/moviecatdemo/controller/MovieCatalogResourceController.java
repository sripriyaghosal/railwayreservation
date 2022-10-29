package com.capg.moviecatdemo.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.capg.moviecatdemo.entity.CatalogItem;
import com.capg.moviecatdemo.entity.Movie;
import com.capg.moviecatdemo.entity.Rating;
import com.capg.moviecatdemo.entity.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourceController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		// RestTemplate restTemplate = new RestTemplate();

		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
				
		return ratings.getUserRating().stream().map(rating -> {
			// For each movie ID, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			// Put them all together
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}).collect(Collectors.toList());

		// get all rated movie IDs
		
		
		// return Collections.singletonList(new CatalogItem("Transformer", "Test", 4));

		/*
		Movie movie = webClientBuilder.build()
		.get()
		.uri("http://localhost:8082/movies/" + rating.getMovieId())
		.retrieve()
		.bodyToMono(Movie.class)
		.block();
		*/
	}

}
