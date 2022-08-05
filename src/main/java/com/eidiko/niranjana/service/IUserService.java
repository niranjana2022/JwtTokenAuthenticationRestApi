package com.eidiko.niranjana.service;


import java.util.Optional;

import com.eidiko.niranjana.model.User;

public interface IUserService {

	
	public Integer saveUser(User user);
	Optional<User> findByUsername(String user);
	
}
