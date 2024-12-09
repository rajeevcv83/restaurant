package com.sapient.cuisine.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RestaurantNotFoundException(String message) {
		 super(String.format("Resturant with id %d not found", message));
	}

}
