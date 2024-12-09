package com.sapient.cuisine.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.cuisine.restaurant.exception.CuisineAllReadyExistException;
import com.sapient.cuisine.restaurant.model.Cuisine;
import com.sapient.cuisine.restaurant.repository.CuisineRepository;
import com.sapient.cuisine.restaurant.service.CuisineService;

@Service
public class CuisineServiceImpl implements CuisineService {

	@Autowired
	private CuisineRepository cuisineRepository;

	@Override
	public void saveCuisine(Cuisine cuisine) {
		Cuisine cuis = cuisineRepository.findByName( cuisine.getName());
		if(cuis == null) {
			cuisineRepository.save(cuisine);
		}
		else {
			throw new CuisineAllReadyExistException("Cuisine Allready Exist");
		}
		
	}
	
	@Override
	public void saveCuisines(List<Cuisine> cuisines) {
		for(Cuisine cus: cuisines) {
			cuisineRepository.save(cus);
		}
		
	}

	@Override
	public Cuisine getCausine(int id) {
		
		return cuisineRepository.findById(id);
	
	}



	
	
	
}
