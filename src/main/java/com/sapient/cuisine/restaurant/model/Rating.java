package com.sapient.cuisine.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private int cuisine;
	private int restaurant;
	private double ratings;
	private String comments;
	
	public Rating() {}
	
	
	
	public Rating(int id, int cuisine, int restaurant, double ratings, String comments) {
		super();
		this.id = id;
		this.cuisine = cuisine;
		this.restaurant = restaurant;
		this.ratings = ratings;
		this.comments = comments;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCuisine() {
		return cuisine;
	}
	public void setCuisine(int cuisine) {
		this.cuisine = cuisine;
	}
	public int getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(int restaurant) {
		this.restaurant = restaurant;
	}
	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
