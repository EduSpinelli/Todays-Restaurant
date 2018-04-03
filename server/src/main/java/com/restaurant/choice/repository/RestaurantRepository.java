package com.restaurant.choice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.restaurant.choice.domain.model.Restaurant;

//
// @CrossOrigin(origins = "http://localhost:4200",
// methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}, maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "restaurants", path = "restaurants")
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

  public Restaurant findByName(String name);

  @Query("SELECT e FROM Restaurant e WHERE e.numberVotes = "
      + "(SELECT coalesce(max(res.numberVotes), 0) FROM Restaurant res)")
  public List<Restaurant> getMaxVote();

  @Query("SELECT e FROM Restaurant e WHERE e.numberVotes > 0")
  public List<Restaurant> findByVotesGreaterThanZero();

}
