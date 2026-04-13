package com.kh.restaurant.model.service;

import java.util.List;

import com.kh.restaurant.model.dao.RestaurantDao;
import com.kh.restaurant.model.dto.RestaurantDto;

public class RestaurantService {
	
	private RestaurantDao rd = new RestaurantDao();
	
	public List<RestaurantDto> findAll(){
		return RestaurantDao.findAll();
	}
		
		
	
}
