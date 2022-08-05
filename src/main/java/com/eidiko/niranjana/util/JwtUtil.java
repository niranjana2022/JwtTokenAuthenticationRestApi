package com.eidiko.niranjana.util;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	//1.generate token
	public  String generateToken(String username)
	{
		log.info("In this method one token is generating");
		return Jwts.builder().setSubject(username).setIssuer("Eidiko")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(10))) //10 minutes expiration(convert 10 minutes to miliseconds)
				.signWith(SignatureAlgorithm.HS256,secret).compact();
				//.signWith(SignatureAlgorithm.HS256,secret.getBytes()).compact();
	}
	//2.Read Claims/what is there in token
	public Claims getClaims(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	//3.get Expiration date of the Token
	public Date getExpDate(String token)
	{
		return getClaims(token).getExpiration();
	}
	//4.get subject/username from token
	public String getUserName(String token)
	{
		return getClaims(token).getSubject();
	}
	//5.check token has Expire date or not
	public boolean validateTokenExp(String token)
	{
			Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	//6.validate the token by using its username
	public boolean isTokenValidate(String token,String username)
	{
			String tokenUserName = getUserName(token);
		return (username.equals(tokenUserName) && !validateTokenExp(token));
	}
}
