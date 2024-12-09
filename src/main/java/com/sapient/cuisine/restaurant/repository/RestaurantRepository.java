package com.sapient.cuisine.restaurant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.cuisine.restaurant.model.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer>{

	Restaurant findByNameAndCity(String name, String city);
	Restaurant findById(int id);

	List<Restaurant> findAllByCity(String city);

	List<Restaurant> findAllByPin(String pin);
}
