package com.brian.book_club.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brian.book_club.models.Book;
import com.brian.book_club.models.User;
import com.brian.book_club.services.BookService;
import com.brian.book_club.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userServ;

	@Autowired
	private BookService bookServ;

	// If I go to this route, is the user logged_in.
	// Need to include session
	@RequestMapping("/home")
	public String homePage(HttpSession session, Model model) {
		// retrieve the user from session
		Long userId = (Long) session.getAttribute("user_id");
		// check if userId is null
		if (userId == null) {
			return "redirect:/";
		} else {
			// go to the database to retrieve the user using the id in session
			User thisUser = userServ.findOne(userId);
			List<Book> allBooks = bookServ.allBooks();
			// To not pass entire object, use .notation to get more attributes of the object
			// example thisUser.getEmail
			model.addAttribute("thisUser", thisUser);
			model.addAttribute("allBooks", allBooks);

//			model.addAttribute("thisUser", thisUser.getEmail());
			return "home.jsp";
		}

	}
	//Only need person in session and empty book object for the form
	@GetMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book, // this three always together
			HttpSession session) {
		// retrieve the user from session
		Long userId = (Long) session.getAttribute("user_id");
		// check if userId is null
		if (userId == null) {
			return "redirect:/";
		}
		
		return "form.jsp";
	}

	@PostMapping("/books/create")
	public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model,
			HttpSession session) {
		//route guard
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		//if there are any errors re-render the form
		if (result.hasErrors()) {
			return "form.jsp";
		} else {
			// go to the database to retrieve the user using the id in session
			Long userId = (Long) session.getAttribute("user_id");// getting the userId from session
			User thisUser = userServ.findOne(userId);// getting the actual User object by id

			book.setPoster(thisUser);// Setting the poster to the user object I got from session if not in the form
			bookServ.createBook(book);// Create a book. 
//				
			return "redirect:/home";
		}
	}

	@RequestMapping("/books/{id}")
	public String one(@PathVariable("id") Long id, Model model, HttpSession session ) {
		//route guard
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		// Go to the database, with id, get the book
		User thisUser = userServ.findOne((Long)session.getAttribute("user_id"));//to render login user info from session
		Book thisBook = bookServ.findBook(id);

		// pass the book to jsp page
		model.addAttribute("thisUser", thisUser); //render user on jsp
		model.addAttribute("thisBook", thisBook); //render book on jsp

		return "show.jsp";

	}

	@GetMapping("/edit/{id}")
	public String edit(@Valid @PathVariable("id") Long id, Model model, HttpSession session) {
		//route guard 
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		// find expense with provided id
		Book book = bookServ.findBook(id);
		// pass this expense to jsp page to display data using model.addAttribute
		model.addAttribute("book", book);
		return "edit.jsp";
		
	}

	@PutMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if (result.hasErrors()) {
			return "edit.jsp";
		} else {
			// If there are no errors, update the object
			bookServ.updateBook(book);
			return "redirect:/home";
		}
	}

}
