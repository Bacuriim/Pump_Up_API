package org.pump.user.controller;

import org.pump.meter.model.Meter;
import org.pump.user.UserRole;
import org.pump.user.model.User;
import org.pump.user.repository.UserRepository;
import org.pump.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity getUserByLogin(@PathVariable(value = "login") String login) {
		return ResponseEntity.ok(userRepository.findByLogin(login));
	}
	
	@PostMapping("/user")
	@ResponseBody
	// Create a new user
	public ResponseEntity createUser(String id, String login, String password) {
		User user = new User(id, login, password, UserRole.USER);
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}
}
