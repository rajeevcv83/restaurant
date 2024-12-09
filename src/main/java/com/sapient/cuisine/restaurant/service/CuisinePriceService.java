package com.sapient.cuisine.restaurant.service;

import java.util.List;

import com.sapient.cuisine.restaurant.model.CuisinePrice;

public interface CuisinePriceService {

	/**
	 * 
	 * @param cuisine
	 */
	void saveCuisinePrice(CuisinePrice cuisine);
	
	/**
	 * Save List of Cuisine price for the restaurant
	 * @param cuisinePrices
	 */
	void saveCuisinePrices(List<CuisinePrice> cuisinePrices);
	
	/**
	 * 
	 * 
	 * @param restaurantId
	 * @return getting list of cuisine prices for the restaurant
	 */

	List<CuisinePrice> getCuisinePriceByRestaurant(int restaurantId);
	
}
