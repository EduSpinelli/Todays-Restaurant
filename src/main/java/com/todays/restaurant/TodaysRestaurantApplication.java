package com.todays.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {TodaysRestaurantApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class TodaysRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodaysRestaurantApplication.class, args);
	}
}
