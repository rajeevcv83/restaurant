package com.sapient.cuisine.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.cuisine.restaurant.exception.CuisineAllReadyExistException;
import com.sapient.cuisine.restaurant.exception.RestaurantAllReadyExistException;
import com.sapient.cuisine.restaurant.model.Cuisine;
import com.sapient.cuisine.restaurant.model.CuisinePrice;
import com.sapient.cuisine.restaurant.model.Rating;
import com.sapient.cuisine.restaurant.model.Restaurant;
import com.sapient.cuisine.restaurant.response.CuisineInfo;
import com.sapient.cuisine.restaurant.service.CuisinePriceService;
import com.sapient.cuisine.restaurant.service.CuisineService;
import com.sapient.cuisine.restaurant.service.RatingService;
import com.sapient.cuisine.restaurant.service.RestaurantService;

@RestController
public class CuisineController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private CuisineService cuisineService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private CuisinePriceService cuisinePriceService;


	

	@Description ("To Save The restaurant Object")
	
	@PostMapping("/saveRestaurants")
	public ResponseEntity<String> saveRestaurants(@RequestBody List<Restaurant> restaurants) {
		try {
			restaurantService.saveRestaurants(restaurants);
			;
		} catch (RestaurantAllReadyExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(451));
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Saved", HttpStatus.OK);
	}

	@GetMapping("/getRestaurantByCity/{city}")
	public List<Restaurant> getRestaurantByCity(@PathVariable("city") String city) {

		return restaurantService.getRestaurantOfCity(city);

	}

	@PostMapping("/saveCuisines")
	public ResponseEntity<String> saveCuisines(@RequestBody List<Cuisine> cuisine) {
		try {
			cuisineService.saveCuisines(cuisine);
			
		} catch (CuisineAllReadyExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(451));
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Saved", HttpStatus.OK);

	}

	@PostMapping("/saveRating")
	public ResponseEntity<String> saveRating(@RequestBody List<Rating> rating) {
		try {
			ratingService.saveAllRating(rating);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Saved", HttpStatus.OK);

	}

	@PostMapping("/saveCausinePrices")
	public ResponseEntity<String> saveCausinePrices(@RequestBody List<CuisinePrice> cuisinePrices) {
		try {
			cuisinePriceService.saveCuisinePrices(cuisinePrices);
		} catch (Exception e) {
			return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Saved", HttpStatus.OK);

	}

	@GetMapping("/bestCuisine/{resturantId}")
	public CuisineInfo bestCuisine(@PathVariable int resturantId) {

		return restaurantService.getBestFoodFromRestaurant(resturantId);

	}

	@GetMapping("/getAllCuisine/{resturantId}")
	public CuisineInfo getAllCuisine(@PathVariable int resturantId) {

		return restaurantService.getAllCuisine(resturantId);

	}
}
