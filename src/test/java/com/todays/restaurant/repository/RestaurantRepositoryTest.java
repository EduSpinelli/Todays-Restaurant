package com.todays.restaurant.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.repository.RestaurantRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RestaurantRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private RestaurantRepository restaurantRepository;


  @Test
  public void whenFindByName_thenReturnRestaurant() {

    Restaurant nutsExpress = Restaurant.createNew("Nuts Express");
    entityManager.persist(nutsExpress);
    entityManager.flush();

    Restaurant found = restaurantRepository.findByName(nutsExpress.getName());

    assertEquals(found.getName(), nutsExpress.getName());

  }

  @Test
  public void whenFindByMaxVotes_thenReturnListOfRestauratsWithMaxVotes() {

    Restaurant nutsExpress = Restaurant.createNew("Nuts Express");
    nutsExpress.vote();
    entityManager.persist(nutsExpress);
    entityManager.flush();

    Restaurant cornerNuts = Restaurant.createNew("Corner Nuts");
    cornerNuts.vote();
    cornerNuts.vote();
    entityManager.persist(cornerNuts);
    entityManager.flush();

    List<Restaurant> restaurantsWithMaxVotes = restaurantRepository.getMaxVote();

    assertFalse(restaurantsWithMaxVotes.stream()
        .anyMatch(restaurat -> nutsExpress.getName().equals(restaurat.getName())));


    assertTrue(restaurantsWithMaxVotes.stream()
        .anyMatch(restaurat -> cornerNuts.getName().equals(restaurat.getName())));

  }

  @Test
  public void whenFindByVotesGreaterThanZero_thenReturnListOfRestaurats() {

    Restaurant nutsExpress = Restaurant.createNew("Nuts Express");
    nutsExpress.vote();
    entityManager.persist(nutsExpress);
    entityManager.flush();

    Restaurant cornerNuts = Restaurant.createNew("Corner Nuts");
    cornerNuts.vote();
    cornerNuts.vote();
    entityManager.persist(cornerNuts);
    entityManager.flush();

    Restaurant maxNuts = Restaurant.createNew("Max Nuts");
    entityManager.persist(maxNuts);
    entityManager.flush();

    List<Restaurant> restaurantsWithVotesGreaterThanZero =
        restaurantRepository.findByVotesGreaterThanZero();

    assertFalse(restaurantsWithVotesGreaterThanZero.stream()
        .anyMatch(restaurat -> maxNuts.getName().equals(restaurat.getName())));


    assertTrue(restaurantsWithVotesGreaterThanZero.stream()
        .anyMatch(restaurat -> cornerNuts.getName().equals(restaurat.getName())));

    assertTrue(restaurantsWithVotesGreaterThanZero.stream()
        .anyMatch(restaurat -> nutsExpress.getName().equals(restaurat.getName())));

  }


}
