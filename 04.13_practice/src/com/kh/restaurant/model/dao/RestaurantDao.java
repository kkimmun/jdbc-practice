package com.kh.restaurant.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.restaurant.model.dto.RestaurantDto;

public class RestaurantDao {
	
	
	public List<RestaurantDto> findAll(){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<RestaurantDto> list = new ArrayList();
		
		String sql = """
							SELECT
									RESTAURANT_ID, 
									RESTAURANT_NAME,
									RESTAURANT_ADDRESS,
									BUSINNESS_DAYS,
									BUSINNESS_HOURS,
									OPEN_DATE
								FROM RESTAURANT
								ORDER BY RESTAURANT_ID
						""";
		
		try {
				Class.forName("Oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("oracle:jdbc:thin:@127.0.0.1:1521:XE",
													"C##SG",
													"SG");
				
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(sql);
				
				
				while(rset.next()) {
					
					RestaurantDto rd = new RestaurantDto();
					
					rd.setRestaurantId(rset.getString("RESTAURANT_ID"));
					rd.setRestaurantName(rset.getString("RESTAURANT_NAME"));
					rd.setRestaurantAddress(rset.getString("RESTAURANT_ADDRESS"));
					rd.setBusinessDays(rset.getString("BUSINESS_DAYS"));
					rd.setBusinessHours(rset.getString("BUSINESS_HOURS"));
					rd.setBusinessDays(rset.getString("OPEN_DATE"));
					
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		
	}
	
}
