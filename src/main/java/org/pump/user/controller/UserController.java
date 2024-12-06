package org.pump.user.controller;

import org.pump.meter.model.Meter;
import org.pump.user.model.User;
import org.pump.user.repository.UserRepository;
import org.pump.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user")
// API for a liquid reservoir meter
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	@ResponseBody
	// Returns all meters
	public List<String> getAllUsers() {
		List<User> usersList = userRepository.findAll();
		List<String> loginList = new ArrayList<>();
		for (User user : usersList) {
			loginList.add(user.getLogin());
		}
		return loginList;
	}
	
	@GetMapping("/user/{login}")
	@ResponseBody
	// Returns user that have the same login that you search
	public boolean userExists(@PathVariable(value = "login") String login) {
		return userRepository.existsUserByLogin(login);
	}
	
	@PostMapping("/user")
	@ResponseBody
	// Create a new user
	public String createUser(String login, String password) {
		if (userRepository.existsUserByLogin(login)) {
			return "User " + login + " already exists";
		} else {
			User user = new User(login, password);
			userService.save(user);
			return "User created!";
		}
	}
}
