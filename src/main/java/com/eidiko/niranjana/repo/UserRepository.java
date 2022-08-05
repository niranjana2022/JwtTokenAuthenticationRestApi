package com.eidiko.niranjana.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eidiko.niranjana.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String user);
}
