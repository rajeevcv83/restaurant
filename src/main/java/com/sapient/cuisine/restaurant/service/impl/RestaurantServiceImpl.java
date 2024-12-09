package com.sapient.cuisine.restaurant.service.impl;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.cuisine.restaurant.exception.RestaurantAllReadyExistException;
import com.sapient.cuisine.restaurant.model.Cuisine;
import com.sapient.cuisine.restaurant.model.CuisinePrice;
import com.sapient.cuisine.restaurant.model.Rating;
import com.sapient.cuisine.restaurant.model.Restaurant;
import com.sapient.cuisine.restaurant.repository.CuisinePriceRepository;
import com.sapient.cuisine.restaurant.repository.CuisineRepository;
import com.sapient.cuisine.restaurant.repository.RatingRepository;
import com.sapient.cuisine.restaurant.repository.RestaurantRepository;
import com.sapient.cuisine.restaurant.response.CuisineInfo;
import com.sapient.cuisine.restaurant.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private CuisineRepository cuisineRepository;

	@Autowired
	private CuisinePriceRepository cuisinePriceRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public void saveRestaurant(Restaurant restaurant) throws RestaurantAllReadyExistException {

		Restaurant rest = restaurantRepository.findByNameAndCity(restaurant.getName(), restaurant.getCity());
		if (rest == null) {
			restaurantRepository.save(restaurant);
		} else {
			throw new RestaurantAllReadyExistException("Restaurant allready Exist");
		}

	}

	@Override
	public void saveRestaurants(List<Restaurant> restaurant) {

		restaurantRepository.saveAll(restaurant);

	}

	@Override
	public List<Restaurant> getRestaurantOfCity(String city) {
		return restaurantRepository.findAllByCity(city);
	}

	@Override
	public List<Restaurant> getRestaurantOfPin(String pin) {

		return restaurantRepository.findAllByPin(pin);
	}

	@Override
	public CuisineInfo getAllCuisine(int resturantId) {
		Map<Integer, Double> cuisineRatingMap = getCuisineRatingMap(resturantId);

		List<Cuisine> cuList = cuisineRepository.findAll();

		CuisineInfo cuisineInfo = new CuisineInfo();
		cuisineInfo.setRestaurant(getRastaurantName(resturantId));
		LinkedHashMap<Integer, Double> cuisinePriceMap = getCuisinePriceMap(resturantId);
		for (Cuisine cu : cuList) {
			cu.setRating(cuisineRatingMap.getOrDefault(cu.getId(), 0.0));
			cu.setPrice(cuisinePriceMap.getOrDefault(cu.getId(), 0.0));

		}
		cuisineInfo.setCuisines(cuList);
		return cuisineInfo;
	}

	@Override
	public CuisineInfo getBestFoodFromRestaurant(int resturantId) {
		 Map<Integer, Double> cuisineRatingMap = getCuisineRatingMap(resturantId);
		List<Cuisine> cuList = cuisineRepository.findAll();

		CuisineInfo cuisineInfo = new CuisineInfo();
		cuisineInfo.setRestaurant(getRastaurantName(resturantId));
		LinkedHashMap<Integer, Double> cuisinePriceMap = getCuisinePriceMap(resturantId);
		for (Cuisine cu : cuList) {
			cu.setRating(cuisineRatingMap.getOrDefault(cu.getId(), 0.0));
			cu.setPrice(cuisinePriceMap.getOrDefault(cu.getId(), 0.0));
		}

		cuisineInfo.setCuisines(cuList.stream().sorted(Comparator.comparingDouble(Cuisine::getRating)).limit(2)
				.collect(Collectors.toList()));
		return cuisineInfo;
	}

	private Restaurant getRastaurantName(int resturantId) {

		return restaurantRepository.findById(resturantId);

	}

	private LinkedHashMap<Integer, Double> getCuisinePriceMap(int resturantId) {
		List<CuisinePrice> cuisinePriceList = cuisinePriceRepository.findAllByRestaurant(resturantId);
		return cuisinePriceList.stream().collect(
				Collectors.toMap(CuisinePrice::getCuisine, CuisinePrice::getPrice, (x, y) -> x, LinkedHashMap::new));

	}

	private Map<Integer, Double> getCuisineRatingMap(int resturantId) {
		List<Rating> ratList = ratingRepository.findAllByRestaurant(resturantId);
		return ratList.stream().collect(Collectors.groupingBy(Rating::getCuisine, TreeMap::new,
				Collectors.averagingDouble(Rating::getRatings)));
	}

}
