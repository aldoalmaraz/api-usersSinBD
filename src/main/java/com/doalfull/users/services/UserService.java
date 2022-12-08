package com.doalfull.users.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import com.doalfull.users.models.User;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;

@Service
public class UserService {
	
	@Autowired  
	private Faker faker;
	
private List<User> users = new ArrayList<>();

@PostConstruct
public void init () {
	
	for (int i = 0; i<100; i++) {
		users.add(new User(faker.name().username(), faker.dragonBall().character(), faker.funnyName().name()));
		
	}
}

public List<User> getUsers() {
	return users;
}



public User getUserByUsername(String username) {
	return users.stream().filter(u -> u.getUsername().equals(username)).findAny()
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("User %s not found", username)));
}

public User createUser(User user) {
	if (users.stream().anyMatch(u-> u.getUsername().equals(user.getUsername()))) {
		throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("User $s already exists",user.getUsername()));
		
	}
	users.add(user);
	return user;
}


public  User updateUser (User user ,String username) {
	User userToBeUpdate=getUserByUsername(username);
	userToBeUpdate.setNickname(user.getNickname());
	userToBeUpdate.setPassword(user.getPassword());
	return userToBeUpdate;
}

public void deleteUser(String username) {
	User userByUsername=getUserByUsername(username);
	users.remove(userByUsername);
}

}
