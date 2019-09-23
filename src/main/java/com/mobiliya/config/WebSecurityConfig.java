
package com.mobiliya.config;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mobiliya.util.JwtAuthenticationEntryPoint;
import com.mobiliya.util.JwtRequestFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//Add Spring Security in which auth api has no restrictions .First auth rest api check username and password and generate token 
//and corresponding rest api is called according to roles .
//If username and password is invalid then it returns 401 Unauthorised Error
//If token is passwed by authorisation key in header if it's not mapped with key then it returns 403 Http status code
//which is indicated forbidden error.

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger logger = LogManager.getLogger(WebSecurityConfigurerAdapter.class);

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Starts configureGlobal");
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		logger.info("Starts configureGlobal");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info("Starts PasswordEncoder");
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		logger.info("Starts authenticationManagerBean");
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		logger.info("Starts configure");
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/auth/**").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		logger.info("Ends configure");

	}
}
