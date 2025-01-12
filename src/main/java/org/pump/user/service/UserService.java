package org.pump.user.service;

import org.pump.user.model.User;
import org.pump.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUserByLogin(String login) {
		return (User) userRepository.findByLogin(login);
	}

	public void save(User user) {
		userRepository.save(user);
	}
	
	public String delete(String login, String password) {
		User user = (User) userRepository.findByLogin(login);
		userRepository.delete(user);
		return "User " + login + " deleted!";
	}
	
}
