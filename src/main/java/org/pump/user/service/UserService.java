package org.pump.user.service;

import org.pump.user.model.User;
import org.pump.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUserByLoginAndPassword(String login, String password) {
		String encryptedPassword = User.encryptPassword(password);
		return userRepository.findUserByLoginAndEncryptedPassword(login, encryptedPassword);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
	
	public String delete(String login, String password) {
		String encryptedPassword = User.encryptPassword(password);
		userRepository.deleteUserByLoginAndEncryptedPassword(login, encryptedPassword);
		return "User " + login + " deleted!";
	}
	
}
