package com.todays.restaurant.repository;

import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.domain.model.Winner;
import com.todays.restaurant.repository.WinnerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WinnerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private WinnerRepository winnerRepository;

  @Test
  @Transactional
  public void whenFindByDate_thenReturnAListOfWinnersInLastWeek() {

    Restaurant nutsExpress = Restaurant.createNew("Nuts Experss");
    entityManager.persist(nutsExpress);
    entityManager.flush();

    Winner winnerInWeek = Winner.createNewWinner(nutsExpress);
    entityManager.persist(winnerInWeek);
    entityManager.flush();

    List<Winner> winnersInLastWeekFound =
        winnerRepository.findWinnersInLastWeekByDate(LocalDate.now().minusWeeks(1));

    assertTrue(winnersInLastWeekFound.stream()
        .anyMatch(winner -> nutsExpress.getName().equals(winner.getRestaurant().getName())));


  }



}
