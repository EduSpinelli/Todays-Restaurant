package com.todays.restaurant.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.todays.restaurant.domain.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

  public User findByUserNameAndPassword(String userName, String password);
  
  public List<User> findByVote(Boolean vote);
  
}
