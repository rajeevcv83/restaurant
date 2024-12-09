package com.sapient.cuisine.restaurant.response;

import java.util.List;

import com.sapient.cuisine.restaurant.model.Cuisine;
import com.sapient.cuisine.restaurant.model.Restaurant;

public class CuisineInfo {

	private Restaurant restaurant;
	private List<Cuisine> cuisines;
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public List<Cuisine> getCuisines() {
		return cuisines;
	}
	public void setCuisines(List<Cuisine> cuisines) {
		this.cuisines = cuisines;
	}
}
