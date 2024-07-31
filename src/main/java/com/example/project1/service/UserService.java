package com.example.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project1.model.User;
import com.example.project1.repo.UserRepository;

@Service
public class UserService {

	  private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    @Autowired
	    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	 public User registerUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
