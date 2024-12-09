package com.sapient.cuisine.restaurant.service;

import java.util.List;

import com.sapient.cuisine.restaurant.model.Cuisine;

public interface CuisineService {

	/**
	 * 
	 * @param cuisine
	 */
	void saveCuisine(Cuisine cuisine);
	
	/**
	 * 
	 * @param cuisines
	 */
	void saveCuisines(List<Cuisine> cuisines);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Cuisine getCausine(int id);
	
}
