package com.sapient.cuisine.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CuisinePrice {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
		private int restaurant;
		private int cuisine;
		private double price;
		
		public CuisinePrice() {}
		
		public CuisinePrice(int id, int restaurant, int cuisine, double price) {
			super();
			this.id = id;
			this.restaurant = restaurant;
			this.cuisine = cuisine;
			this.price = price;
		}
		public int getCuisine() {
			return cuisine;
		}
		public void setCuisine(int cuisine) {
			this.cuisine = cuisine;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getRestaurant() {
			return restaurant;
		}
		public void setRestaurant(int restaurant) {
			this.restaurant = restaurant;
		}
}
