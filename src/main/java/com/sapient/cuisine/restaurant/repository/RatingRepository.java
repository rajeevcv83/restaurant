package com.sapient.cuisine.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.cuisine.restaurant.model.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer>{
	List<Rating> findAllByRestaurantAndCuisine(int restaurantId, int cuisineId);
	List<Rating> findAllByRestaurant(int restaurantId);

}
