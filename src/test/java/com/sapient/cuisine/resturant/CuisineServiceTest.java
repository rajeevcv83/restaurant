package com.sapient.cuisine.resturant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atMostOnce;
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
import com.sapient.cuisine.restaurant.exception.CuisineAllReadyExistException;
import com.sapient.cuisine.restaurant.model.Cuisine;
import com.sapient.cuisine.restaurant.repository.CuisinePriceRepository;
import com.sapient.cuisine.restaurant.repository.CuisineRepository;
import com.sapient.cuisine.restaurant.repository.RatingRepository;
import com.sapient.cuisine.restaurant.repository.RestaurantRepository;
import com.sapient.cuisine.restaurant.service.impl.CuisineServiceImpl;

@ContextConfiguration(classes = RestaurantApplication.class)
@SpringBootTest
public class CuisineServiceTest {

	@InjectMocks
	private CuisineServiceImpl cuisineService;

	@Mock
	private RestaurantRepository restaurantRepository;

	@Mock
	private CuisineRepository cuisineRepository;

	@Mock
	private CuisinePriceRepository cuisinePriceRepository;

	@Mock
	private RatingRepository ratingRepository;

	@Test
	void testSaveCuisine() {
		Cuisine cui = getMockCuisine();

		when(cuisineRepository.findByName(Mockito.anyString())).thenReturn(new Cuisine(1, "Palak Panneer"));

		assertThatThrownBy(() -> cuisineService.saveCuisine(cui))
				.isExactlyInstanceOf(CuisineAllReadyExistException.class);

	}

	@Test
	void testSaveCuisine_While_Reastaurant_Exist() {
		Cuisine cui = getMockCuisine();

		when(cuisineRepository.findByName("Palak Panneer")).thenReturn(new Cuisine(1, "Palak Panneer"));
		assertThatThrownBy(() -> cuisineService.saveCuisine(cui))
				.isExactlyInstanceOf(CuisineAllReadyExistException.class);

	}

	@Test
	void testSaveCuisine_While_Reastaurant_No_Exist() {
		Cuisine cui = getMockCuisine();
		when(cuisineRepository.findByName("Palak Panneer")).thenReturn(null);
		cuisineService.saveCuisine(cui);
		verify(cuisineRepository, atMostOnce()).save(cui);

	}

	@Test
	void testSaveCuisines() {

		when(cuisineRepository.findByName("Palak Panneer")).thenReturn(new Cuisine(1, "Palak Panneer"));
		cuisineService.saveCuisines(getCuisineList());

	}

	@Test
	void testGetCuisine() {

		when(cuisineRepository.findById(1)).thenReturn(getMockCuisine());
		Cuisine result = cuisineService.getCausine(1);
		assertEquals(1, result.getId());

	}

	private Cuisine getMockCuisine() {
		Cuisine cui = new Cuisine();
		cui.setId(1);
		cui.setName("Palak Panneer");
		return cui;
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

}
