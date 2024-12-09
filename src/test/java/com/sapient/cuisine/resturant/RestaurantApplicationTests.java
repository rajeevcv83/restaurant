package com.sapient.cuisine.resturant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sapient.cuisine.restaurant.RestaurantApplication;
import com.sapient.cuisine.restaurant.controller.CuisineController;

@SpringBootTest
@ContextConfiguration(classes = RestaurantApplication.class)
@AutoConfigureWebTestClient

class RestaurantApplicationTests {

	@Autowired
	private CuisineController cuisineController;

	@Test
	void contextLoads() {
	}


}
