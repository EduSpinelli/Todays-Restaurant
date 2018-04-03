 package com.restaurant.choice.controller;

 import static org.junit.Assert.assertNotNull;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
 import java.io.IOException;
 import java.nio.charset.Charset;
 import java.util.Arrays;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
 import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
 import org.springframework.mock.http.MockHttpOutputMessage;
 import org.springframework.test.context.TestPropertySource;
 import org.springframework.test.context.junit4.SpringRunner;
 import org.springframework.test.context.web.WebAppConfiguration;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.web.context.WebApplicationContext;

import com.restaurant.choice.RestaurantChoiceApplication;
import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.domain.model.User;
import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.repository.RestaurantRepository;
import com.restaurant.choice.repository.UserRepository;


 @RunWith(SpringRunner.class)
 @SpringBootTest(classes = RestaurantChoiceApplication.class)
 @WebAppConfiguration
 @TestPropertySource(locations = "classpath:application-integrationtest.properties")
 public class VoteControllerTest {

 private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
 MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

 private MockMvc mockMvc;

 @Autowired
 private UserRepository userRepository;

 @Autowired
 private RestaurantRepository restaurantReposiory;

 @Autowired
 private WebApplicationContext webApplicationContext;
 private User user;

 private Restaurant restaurant;

 private HttpMessageConverter mappingJackson2HttpMessageConverter;

 @Autowired
 void setConverters(HttpMessageConverter<?>[] converters) {

 this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
 .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

 assertNotNull("the JSON message converter must not be null",
 this.mappingJackson2HttpMessageConverter);
 }

 @Before
 public void setup() throws Exception {

 this.mockMvc = webAppContextSetup(webApplicationContext).build();

 user = userRepository.save(User.createNew("joao", "joao", "joao", "carlos", "joao@carlos.com"));

 restaurant = restaurantReposiory.save(Restaurant.createNew("Subway"));

 }

 @Test
 public void responseStatusOk_whenPostVote_thenReturnSucessMsg() throws Exception {

 Vote vote = Vote.createNewVote(user.getUsername(), user.getPassword(), restaurant.getName());

 mockMvc.perform(post("/votes").content(this.json(vote)).contentType(contentType))
 .andExpect(status().isOk());
 }

 protected String json(Object o) throws IOException {
 MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
 this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON,
 mockHttpOutputMessage);
 return mockHttpOutputMessage.getBodyAsString();
 }


 }
