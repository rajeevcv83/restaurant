package com.sapient.cuisine.resturant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.sapient.cuisine.restaurant.RestaurantApplication;
import com.sapient.cuisine.restaurant.model.CuisinePrice;
import com.sapient.cuisine.restaurant.repository.CuisinePriceRepository;
import com.sapient.cuisine.restaurant.repository.RatingRepository;
import com.sapient.cuisine.restaurant.repository.RestaurantRepository;
import com.sapient.cuisine.restaurant.service.impl.CuisinePriceServiceImpl;

@ContextConfiguration(classes = RestaurantApplication.class)
@SpringBootTest
public class CuisinePriceServiceTest {
	
	
	@InjectMocks
	private CuisinePriceServiceImpl cuisinePriceService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	

	@Mock
	private CuisinePriceRepository cuisinePriceRepository;

	@Mock
	private RatingRepository ratingRepository;
	
	@Test
	void testSaveCuisinePrice() {
		CuisinePrice cui = getMockCuisinePrice();
		
		cuisinePriceService.saveCuisinePrice(cui);
		verify(cuisinePriceRepository, atMostOnce()).save(cui);
		
	}
	
	
	

	@Test
	void testSaveCuisines() {
		
		cuisinePriceService.saveCuisinePrices(getCuisinePriceList());
		//verify(cuisinePriceRepository, times(1)).saveAll(getCuisinePriceList());
	}
	
	@Test
	void testGetCuisine() {
		
		when(cuisinePriceRepository.findAllByRestaurant(1 )).thenReturn(getCuisinePriceList());
		List<CuisinePrice> result = cuisinePriceService.getCuisinePriceByRestaurant(1);
		assertEquals(8, result.size());
		
	}
	
	private CuisinePrice getMockCuisinePrice() {
		return new CuisinePrice(1, 1, 1, 20.00);
	}


	private List<CuisinePrice> getCuisinePriceList() {
		List<CuisinePrice> cus = new ArrayList<>();
		CuisinePrice c = new CuisinePrice(1, 1, 1, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(1, 1, 1, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(2, 1, 2, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(3, 1, 3, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(4, 1, 4, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(5, 2, 1, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(6, 2, 3, 20.00);
		cus.add(c);
		
		c = new CuisinePrice(7, 2, 3, 20.00);
		cus.add(c);
		
		
		return cus;
	}	

}