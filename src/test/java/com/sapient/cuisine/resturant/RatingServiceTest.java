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
import com.sapient.cuisine.restaurant.model.Rating;
import com.sapient.cuisine.restaurant.repository.CuisinePriceRepository;
import com.sapient.cuisine.restaurant.repository.RatingRepository;
import com.sapient.cuisine.restaurant.repository.RestaurantRepository;
import com.sapient.cuisine.restaurant.service.RatingService;

@ContextConfiguration(classes = RestaurantApplication.class)
@SpringBootTest
public class RatingServiceTest {
	
	
	@InjectMocks
	private RatingService ratingService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	

	@Mock
	private CuisinePriceRepository cuisinePriceRepository;

	@Mock
	private RatingRepository ratingRepository;
	
	@Test
	void testSaveCuisinePrice() {
		Rating rat = getMockRating();
		
		ratingService.saveRating(rat);
		verify(ratingRepository, atMostOnce()).save(rat);
		
	}
	
	
	

	@Test
	void testSaveCuisines() {
		
		ratingService.saveAllRating(getMockedRatingFromARastaurant());
	
	}
	
	@Test
	void testGetCuisine() {
		
		when(ratingRepository.findAllByRestaurant(1)).thenReturn(getMockedRatingFromARastaurant());
		List<Rating> result = ratingService.getRestaurantRating(1);
		assertEquals(12, result.size());
		
	}
	
	private Rating getMockRating() {
		return new Rating(1, 1, 1, 2.00, "NA");
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


	

}