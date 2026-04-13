package com.kh.restaurant.controller;

import java.util.List;

import com.kh.restaurant.model.dto.RestaurantDto;
import com.kh.restaurant.model.service.RestaurantService;

public class RestaurantController {
	private RestaurantService rs = new RestaurantService();
	
	public List<RestaurantDto> findAll(){
		
		return rs.findAll();
	}
}
