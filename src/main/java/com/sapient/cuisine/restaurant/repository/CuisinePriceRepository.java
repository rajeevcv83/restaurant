package com.sapient.cuisine.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.cuisine.restaurant.model.CuisinePrice;

@Repository
public interface CuisinePriceRepository extends JpaRepository<CuisinePrice, Integer> {

	/**
	 * 
	 * @param restaurantId
	 * @return List of all cuisineprice from respected resturants
	 */
	List<CuisinePrice> findAllByRestaurant(int restaurantId);

}
