package com.eidiko.niranjana.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.eidiko.niranjana.model.User;
import com.eidiko.niranjana.model.UserRequest;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String user);
	
	@Query(value="update USRTAB SET TOKEN=:token where ID=:id",nativeQuery=true)
    @Modifying
    @Transactional
	public int saveToken(int id,String token);
}
