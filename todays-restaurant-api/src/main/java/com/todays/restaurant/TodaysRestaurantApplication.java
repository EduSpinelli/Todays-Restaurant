package com.todays.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EntityScan(basePackageClasses = {TodaysRestaurantApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class TodaysRestaurantApplication {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }


  public static void main(String[] args) {
    SpringApplication.run(TodaysRestaurantApplication.class, args);
  }
}
