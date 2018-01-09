package com.todays.restaurant.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.todays.restaurant.domain.model.Winner;

@RepositoryRestResource(collectionResourceRel = "winners", path = "winners")
public interface WinnerRepository extends PagingAndSortingRepository<Winner, Long> {

  @Query("SELECT e FROM Winner e WHERE e.voteDate > :dateOfWeek")
  public List<Winner> findWinnersInLastWeekByDate(@Param("dateOfWeek") LocalDate expDate);

}
