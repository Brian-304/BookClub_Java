package com.brian.book_club.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.brian.book_club.models.LoginUser;
import com.brian.book_club.models.User;
import com.brian.book_club.repos.UserRepository;

@Service
public class UserService {
	
	// Methods for CRUD
	
		 @Autowired
		    private UserRepository userRepo;
		    
		    // TO-DO: Write register and login methods!
		    public User register(User newUser, BindingResult result) {
		        // TO-DO: Additional validations!
		    	Optional<User> futureUser= userRepo.findByEmail(newUser.getEmail());
		    	// verify email exist
		        if(futureUser.isPresent()) {
		        	result.rejectValue("email", "registerNull", "email not found");
		        }
		        // verify password
		        if(!newUser.getPassword().equals(newUser.getConfirm())) {
		        	result.rejectValue("confirm", "registerNull", "passwords do not match");
		        	
		        }
		        
		        // return results
		        if(result.hasErrors()) {
		        	return null;
		        }else {
		        	// Add bcrypt and save user to the database
		        	String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		        	newUser.setPassword(hashedPW);
		        	// Save user in the database
		        	return userRepo.save(newUser);
		        }
		        	
		    	
		    }
		    public User login(LoginUser newLoginObject, BindingResult result) {
		        // Check email exist in the database
		    	Optional<User> futureUser= userRepo.findByEmail(newLoginObject.getEmail());
		    	if(!futureUser.isPresent()) {
		    		result.rejectValue("email", "registerNull", "email error");
		    	} else {
		    		
		    		User user = futureUser.get();
		    		// if Bcrypt passwords do not match, reject user
		    		if(!BCrypt.checkpw(newLoginObject.getPassword(),user.getPassword())) {
		    			result.rejectValue("password", "registerNull", "password invalid");
		    		}
		    		if(result.hasErrors()) {
		    			return null;
		    		} else {
		    		// Otherwise return user object
		    			return user;
		    		}
		    	}
		    	
		        return null;
		    }
		    //Read One user
		    public User findOne(Long id) {
		    	Optional<User> futureUser= userRepo.findById(id);
		    	if(futureUser.isPresent()) {
		    		return futureUser.get();	
		    	} else {
		    		return null;
		    	}
		    	
		    	
		    }

}
