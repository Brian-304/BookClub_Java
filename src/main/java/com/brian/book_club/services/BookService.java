package com.brian.book_club.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brian.book_club.models.Book;
import com.brian.book_club.repos.BookRepo;



@Service
public class BookService {

	//CRUD
	@Autowired
	private BookRepo bookRepo;
	
	
    // returns all the books or thing
    public List<Book> allBooks() {
        return bookRepo.findAll();
    }
    // creates a book or createThing
    public Book createBook(Book b) {
        return bookRepo.save(b);
    }
    // retrieves a book or findThing
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    
    }
    
    // updateBook or updateThing
    public Book updateBook(Book b) {
    	return bookRepo.save(b);
    	
    }
    
    public void deleteBook(Long id) {
    	bookRepo.deleteById(id);
    }

}
