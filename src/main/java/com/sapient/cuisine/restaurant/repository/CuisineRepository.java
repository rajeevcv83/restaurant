package com.sapient.cuisine.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.cuisine.restaurant.model.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Integer>{
	
	Cuisine findByName(String name);
	Cuisine findById(int id);
}
