package com.restaurant.choice.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.restaurant.choice.domain.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	List<User> findByVote(Boolean vote);

}
