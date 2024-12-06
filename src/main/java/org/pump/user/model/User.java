package org.pump.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String encryptedPassword;
	
	public User(String login, String password) {
		this.login = login;
		this.encryptedPassword = encryptPassword(password);
	}
	
	public static String encryptPassword(String password) {
		char[] chars = password.toCharArray();
		char[] charsAfterEncrypt = new char[chars.length];
		int i = 0;
		for (char c: chars) {
			charsAfterEncrypt[i] = (char) (255 - (int) c);
			i++;
		}
		return new String(charsAfterEncrypt);
	}
	
	public static String encoderPassword(String encryptedPassword) {
		char[] chars = encryptedPassword.toCharArray();
		char[] charsAfterEncoding = new char[chars.length];
		int i = 0;
		for (char c: chars) {
			charsAfterEncoding[i] = (char) (255 - (int) c);
			i++;
		}
		return new String(charsAfterEncoding);
	}
}
