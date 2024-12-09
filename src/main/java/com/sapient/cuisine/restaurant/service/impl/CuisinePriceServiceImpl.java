package com.sapient.cuisine.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.cuisine.restaurant.model.CuisinePrice;
import com.sapient.cuisine.restaurant.repository.CuisinePriceRepository;
import com.sapient.cuisine.restaurant.service.CuisinePriceService;

@Service
public class CuisinePriceServiceImpl implements CuisinePriceService {

	@Autowired
	private CuisinePriceRepository cuisinePriceRepository;
	@Override
	public void saveCuisinePrice(CuisinePrice cuisine) {
		cuisinePriceRepository.save(cuisine);
		
	}

	@Override
	public void saveCuisinePrices(List<CuisinePrice> cuisinePrices) {
		cuisinePriceRepository.saveAll(cuisinePrices);
		
	}

	@Override
	public List<CuisinePrice> getCuisinePriceByRestaurant(int restaurantId) {
		
		return cuisinePriceRepository.findAllByRestaurant(restaurantId);
	}

	

}
