package com.sapient.cuisine.restaurant.service;

import java.util.List;

import com.sapient.cuisine.restaurant.exception.RestaurantAllReadyExistException;
import com.sapient.cuisine.restaurant.model.Restaurant;
import com.sapient.cuisine.restaurant.response.CuisineInfo;


public interface RestaurantService {

	/**
	 * 
	 * @param restaurant
	 */
	void saveRestaurants(List<Restaurant> restaurant);
	/**
	 * 
	 * @param restaurant
	 * @throws RestaurantAllReadyExistException
	 */
	
	void saveRestaurant(Restaurant restaurant) throws RestaurantAllReadyExistException;
	/**
	 * 
	 * @param city
	 * @return List of restaurant of city
	 */
	List<Restaurant> getRestaurantOfCity(String city);
	
	/**
	 * 
	 * @param pin
	 * @return
	 */
	List<Restaurant> getRestaurantOfPin(String pin);
	
	/**
	 * 
	 * @param resturantId
	 * @return 2 best cuisine of resturantId
	 */
	CuisineInfo getBestFoodFromRestaurant(int resturantId);
	
	/**
	 * 
	 * @param resturantId
	 * @return get all cuisine of resturantId
	 */
	CuisineInfo getAllCuisine(int resturantId);
	
	
}
