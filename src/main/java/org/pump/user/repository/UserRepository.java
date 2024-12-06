package org.pump.user.repository;

import org.pump.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsUserByLogin(String login);
	User findUserByLoginAndEncryptedPassword(String login, String password);
	void deleteUserByLoginAndEncryptedPassword(String login, String password);
}
