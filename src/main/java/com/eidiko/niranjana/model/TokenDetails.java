package com.eidiko.niranjana.model;

import java.util.Date;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDetails {
	
	private String token;
	private Claims claim;
	private Date tokenExpDate;
	private String username;
	private boolean isTokenValid;
	
	

}
