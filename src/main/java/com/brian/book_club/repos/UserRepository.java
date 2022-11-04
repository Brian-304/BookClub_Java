package com.brian.book_club.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.book_club.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	// this method overrides all other functions
	List<User> findAll();
	
	//Add findByEmail in the User Class
	Optional<User> findByEmail(String email);
}

//Reference query methods in helpful links to creating own queries 