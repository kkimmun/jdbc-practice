package com.kh.restaurant.model.dto;

public class RestaurantDto {
	
	String restaurantId;
	String restaurantName;
	String restaurantAddress;
	String businessDays;
	String businessHours;
	String openDate;
	
	public RestaurantDto() {}

	public RestaurantDto(String restaurantId, String restaurantName, String restaurantAddress, String businessDays,
			String businessHours, String openDate) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.businessDays = businessDays;
		this.businessHours = businessHours;
		this.openDate = openDate;
	}

	public RestaurantDto(String restaurantName, String restaurantAddress, String businessDays, String businessHours,
			String openDate) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.businessDays = businessDays;
		this.businessHours = businessHours;
		this.openDate = openDate;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}

	public String getBusinessDays() {
		return businessDays;
	}

	public void setBusinessDays(String businessDays) {
		this.businessDays = businessDays;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	@Override
	public String toString() {
		return "RestaurantDto [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantAddress=" + restaurantAddress + ", businessDays=" + businessDays + ", businessHours="
				+ businessHours + ", openDate=" + openDate + "]";
	}
	
	
	
	
	
}
