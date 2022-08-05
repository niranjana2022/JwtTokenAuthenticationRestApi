package com.eidiko.niranjana.config;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eidiko.niranjana.serviceImpl.UserServiceImpl;
import com.eidiko.niranjana.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserServiceImpl userSErviceImpl;
	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private JwtUtil util;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
/*
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUserName(jwtToken);
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userSErviceImpl.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
*/
		
		//read token from Auth read
		String token = request.getHeader("Authorization");
		if(token!=null)
		{
			log.info("token: "+token);
			//do verify the token(VALID THE USERNAME THEN VALID THE TOKEN)
			String tokenUsername = util.getUserName(token);
			log.info("tokenUsername: "+tokenUsername);
				//username shouldnot be empty and 
			if(tokenUsername!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails = userDetailService.loadUserByUsername(tokenUsername);
				log.info("userDetails: "+userDetails);
				//verify token validation
				boolean isTokenValid= util.isTokenValidate(token, userDetails.getUsername());
				log.info("isTokenValid: "+isTokenValid);
				if(isTokenValid)
				{
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(tokenUsername, userDetails.getPassword(),userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					//fial object stored in SecurityContext with User Details(username,password)
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		chain.doFilter(request, response);
	}
		
}