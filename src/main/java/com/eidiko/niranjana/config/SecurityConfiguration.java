package com.eidiko.niranjana.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
//	public static final String[] API_URLS = {
//			"user/save","user/login"
//	};

	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private InvalidUserAuthEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter securityFilter;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
	HttpServletResponse response;
	HttpServletRequest request;
	AuthenticationException error;
	
	//This method is for Authentication
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
		}
		//This method for Authorization
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/user/save","/user/generateTokenUsinglogin","/user/generateTokenUsinglogin1","/user/tokenUsername","/user/tokenDetailsHardcore",
					"/user/generateToken","/user/generateTokenUsingIDPWDInHeader","/user/getTokenDetailsUsingTokenInHeader","/user/generateTokenUsingIDPWDInHeaderBO",
					"/user/getTokenDetailsUsingTokenInHeaderBO","/user/generateTokenUsingIDPWDInPropertiesBO","/user/getTokenDetailsUsingTokenInPropertiesBO")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//register filter with 2nd request onward
			.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
			
		}
	
}
