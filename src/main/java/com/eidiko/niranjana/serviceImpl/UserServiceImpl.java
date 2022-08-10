package com.eidiko.niranjana.serviceImpl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eidiko.niranjana.model.User;
import com.eidiko.niranjana.model.UserRequest;
import com.eidiko.niranjana.repo.UserRepository;
import com.eidiko.niranjana.service.IUserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	//save method
	@Override
	public Integer saveUser(User user) 
	{
		log.info("username is : "+user.getName());
		log.info("password is : "+user.getPassword());
		//user.setName(pwdEncoder.encode(user.getName()));//Only This line is using for "encode" the password and stored in DB which is comming from request body
		user.setPassword(pwdEncoder.encode(user.getPassword()));//Only This line is using for "encode" the password and stored in DB which is comming from request body
		log.info("password as Encoded form : "+user.getPassword());
		return repo.save(user).getId();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = findByUsername(username);
		if(optUser.isEmpty())
		{
			throw new UsernameNotFoundException(username);
		}
		
		else {
			//read the user details from DB
			User user = optUser.get();		
			return new org.springframework.security.core.userdetails.User(username,user.getPassword(),
					user.getRoles().stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));		
			}
	}


	@Override
	public Optional<User> findByUsername(String user) {
		return repo.findByUsername(user);
	}


	@Override
	public int saveTokenInDB(int id, String token) {
		// TODO Auto-generated method stub
		return repo.saveToken(id, token);
	}



	
}
