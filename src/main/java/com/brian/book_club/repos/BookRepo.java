package com.brian.book_club.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brian.book_club.models.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
	
	List<Book> findAll();

}
