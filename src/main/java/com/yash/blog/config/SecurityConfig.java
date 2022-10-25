package com.yash.blog.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.yash.blog.security.CustomUserDetailsService;
import com.yash.blog.security.JwtAuthenticationEntryPoint;
import com.yash.blog.security.JwtAuthenticationFilter;

import io.swagger.models.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public static final String[] PUBLIC_URLS = {
			"/v1/auth/**",
			"/v3/api-docs",//to access in json
			"/v2/api-docs",// to access in swagger API
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**",
	};
	
	@Autowired
	private CustomUserDetailsService customuserdetailsservice;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtauthenticationentrypoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtautheticationfilter;
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(PUBLIC_URLS).permitAll()
		.antMatchers(org.springframework.http.HttpMethod.GET).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(this.jwtauthenticationentrypoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtautheticationfilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(this.customuserdetailsservice).passwordEncoder(passwordEncoder());		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
}

