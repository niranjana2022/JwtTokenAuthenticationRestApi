package com.eidiko.niranjana.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
}
