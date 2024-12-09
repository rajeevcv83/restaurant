package com.sapient.cuisine.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.cuisine.restaurant.model.Rating;
import com.sapient.cuisine.restaurant.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public void saveRating(Rating rating) {
		ratingRepository.save(rating);
	}

	/**
	 * 
	 * @param ratingList
	 */
	public void saveAllRating(List<Rating> ratingList) {
		for (Rating rat : ratingList) {
			ratingRepository.save(rat);
		}
	}

	
	/**
	 * 
	 * @param restaurantId
	 * @return List of rating of that Restaurant
	 */
	public List<Rating> getRestaurantRating(int restaurantId) {

		return ratingRepository.findAllByRestaurant(restaurantId);
	}

}
