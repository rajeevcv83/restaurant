package com.sapient.cuisine.resturant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sapient.cuisine.restaurant.RestaurantApplication;
import com.sapient.cuisine.restaurant.exception.RestaurantAllReadyExistException;
import com.sapient.cuisine.restaurant.model.Cuisine;
import com.sapient.cuisine.restaurant.model.Rating;
import com.sapient.cuisine.restaurant.model.Restaurant;
import com.sapient.cuisine.restaurant.repository.CuisinePriceRepository;
import com.sapient.cuisine.restaurant.repository.CuisineRepository;
import com.sapient.cuisine.restaurant.repository.RatingRepository;
import com.sapient.cuisine.restaurant.repository.RestaurantRepository;
import com.sapient.cuisine.restaurant.response.CuisineInfo;
import com.sapient.cuisine.restaurant.service.impl.RestaurantServiceImpl;

@ContextConfiguration(classes = RestaurantApplication.class)
@SpringBootTest
public class RestaurantServiceTest {
	
	
	@InjectMocks
	private RestaurantServiceImpl restaurantService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private CuisineRepository cuisineRepository;

	@Mock
	private CuisinePriceRepository cuisinePriceRepository;

	@Mock
	private RatingRepository ratingRepository;
	
	@Test
	void testSaveRestaurant() {
		when(restaurantRepository.findByNameAndCity(Mockito.anyString(), Mockito.anyString() )).thenReturn(getMockRestaurant());
		verify(restaurantRepository, atMostOnce()).save(new Restaurant());
		
	}
	
	@Test
	void testSaveRestaurant_While_Reastaurant_Exist() {
		Restaurant rest = new Restaurant();
		rest.setId(1);
		rest.setName("HYZ");
		rest.setCity("Delhi");
		when(restaurantRepository.findByNameAndCity("HYZ", "Delhi" )).thenReturn(getMockRestaurant());

		assertThatThrownBy(() -> restaurantService.saveRestaurant(rest)).isExactlyInstanceOf(RestaurantAllReadyExistException.class);
	
		
	}
	
	@Test
	void testSaveRestaurant_While_Reastaurant_No_Exist() {
		Restaurant rest = new Restaurant();
		rest.setId(1);
		rest.setName("HYZ");
		rest.setCity("Delhi");
		when(restaurantRepository.findByNameAndCity("HYZ", "Delhi" )).thenReturn(null);
		restaurantService.saveRestaurant(rest);
		verify(restaurantRepository, atMostOnce()).save(rest);
		
	}
	
	@Test
	void testSaveRestaurants() {
		Restaurant rest = getMockRestaurant();
		
		Restaurant rest1 = new Restaurant();
		rest1.setId(2);
		rest1.setName("HYZ");
		rest1.setCity("Delhi");

		
		when(restaurantRepository.findByNameAndCity(Mockito.anyString(), Mockito.anyString() )).thenReturn(null);
		restaurantService.saveRestaurants(List.of(rest, rest1));
		verify(restaurantRepository, times(1)).saveAll(List.of(rest, rest1));
	}
	
	@Test
	void testGetRestaurantOfPin() {
		Restaurant rest = getMockRestaurant();
		when(restaurantRepository.findAllByPin(Mockito.anyString())).thenReturn(List.of(rest));
		restaurantService.getRestaurantOfPin("100001");
		verify(restaurantRepository, times(1)).findAllByPin(Mockito.anyString());
		
	}
	
	@Test
	void testGetRestaurantOfCity() {
		Restaurant rest = getMockRestaurant();
		when(restaurantRepository.findAllByCity(Mockito.anyString())).thenReturn(List.of(rest));
		restaurantService.getRestaurantOfCity("HYZ");
		verify(restaurantRepository, times(1)).findAllByCity(Mockito.anyString());
	}
	
	@Test
	void testGetBestFoodFromRestaurant() {
		Restaurant rest = getMockRestaurant();
		when(ratingRepository.findAllByRestaurant(Mockito.anyInt())).thenReturn(getMockedRatingFromARastaurant());
		when(cuisineRepository.findAll()).thenReturn(getCuisineList());
		when(restaurantRepository.findById(Mockito.anyInt())).thenReturn(rest);
		CuisineInfo cuisineInfo = restaurantService.getBestFoodFromRestaurant(10);
	
		assertEquals("Delhi", cuisineInfo.getRestaurant().getCity());
		assertEquals(1, cuisineInfo.getRestaurant().getId());
		assertEquals(2, cuisineInfo.getCuisines().size());		
		
	}
	
	@Test
	void testGetAllCuisineFromRestaurant() {
		Restaurant rest = getMockRestaurant();
		when(ratingRepository.findAllByRestaurant(Mockito.anyInt())).thenReturn(getMockedRatingFromARastaurant());
		when(cuisineRepository.findAll()).thenReturn(getCuisineList());
		when(restaurantRepository.findById(Mockito.anyInt())).thenReturn(rest);
		CuisineInfo cuisineInfo = restaurantService.getAllCuisine(10);
	
		assertEquals("Delhi", cuisineInfo.getRestaurant().getCity());
		assertEquals(1, cuisineInfo.getRestaurant().getId());
		assertEquals(4, cuisineInfo.getCuisines().size());		
		
	}


	private List<Cuisine> getCuisineList() {
		List<Cuisine> cus = new ArrayList<>();
		Cuisine c = new Cuisine(1, "Palak Panneer");
		cus.add(c);
		
		c = new Cuisine(2, "Tandoori Paneer");
		cus.add(c);
		c = new Cuisine(1, "Palak Kofta");
		cus.add(c);
		c = new Cuisine(1, "Matar Panneer");
		cus.add(c);
		return cus;
	}

	private List<Rating> getMockedRatingFromARastaurant() {
		List<Rating> ratings = new ArrayList<>();
		Rating rat = new Rating(1, 1, 10, 4.5, "NA");
		ratings.add(rat);
		
		rat = new Rating(2, 1, 10, 2.5, "NA");
		ratings.add(rat);
		
		rat = new Rating(3, 2, 10, 4, "NA");
		ratings.add(rat);
		
		rat = new Rating(4, 2, 10, 4.25, "NA");
		ratings.add(rat);
		
		rat = new Rating(5, 2, 10, 3.5, "NA");
		ratings.add(rat);
		
		rat = new Rating(6, 3, 10, 2.5, "NA");
		ratings.add(rat);
		
		rat = new Rating(7, 2, 10, 3, "NA");
		ratings.add(rat);
		
		rat = new Rating(8, 1, 10, 4.25, "NA");
		ratings.add(rat);
		
		rat = new Rating(9, 3, 10, 4.5, "NA");
		ratings.add(rat);
		
		rat = new Rating(10, 3, 10, 2.5, "NA");
		ratings.add(rat);
		
		rat = new Rating(11, 4, 10, 4, "NA");
		ratings.add(rat);
		
		rat = new Rating(12, 4, 10, 4.25, "NA");
		ratings.add(rat);
		
		
		return ratings;
	}

	private Restaurant getMockRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(1);
		restaurant.setName("HYZ");
		restaurant.setCity("Delhi");
		restaurant.setPin("100001");
		return restaurant;
		
	
	}
	

}
