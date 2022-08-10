package com.eidiko.niranjana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.niranjana.model.SaveToken;
import com.eidiko.niranjana.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TokenStoreInDB {
	
	@Autowired
	private IUserService service;
	
	@PostMapping("/tokenSave")
	public ResponseEntity<String> tokenStoreInDB(@RequestBody SaveToken data)
	{
		
		int tokenIs = service.saveTokenInDB(data.getId(),data.getToken());
		log.info("tokenIs : "+tokenIs);
		
		return new ResponseEntity<String>("Token saved on id "+data.getId()+" in sql database",HttpStatus.OK);

		
	}

}
