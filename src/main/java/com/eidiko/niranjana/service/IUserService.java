package com.eidiko.niranjana.service;


import java.util.Optional;

import com.eidiko.niranjana.model.User;
import com.eidiko.niranjana.model.UserRequest;

public interface IUserService {

	
	public Integer saveUser(User user);
	Optional<User> findByUsername(String user);
	
	public int saveTokenInDB(int id, String token);
	
}
