package com.restaurant.choice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(basePackageClasses = {RestaurantChoiceApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class RestaurantChoiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestaurantChoiceApplication.class, args);
  }
}
