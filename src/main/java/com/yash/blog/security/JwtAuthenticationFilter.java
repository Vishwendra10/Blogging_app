package com.yash.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userdetailsservice;
	
	@Autowired
	private JwtTokenHelper jwttokenhelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// get token from header
		String requestToken=request.getHeader("Authorization");
		
		//Bearer 2344ghj34
		
		String username=null;
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			token= requestToken.substring(7);
			try 
			{
				username=this.jwttokenhelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Unable to get Jwt token");
			}
			catch(ExpiredJwtException e)
			{
				System.out.println("Jwt token has expired");
			}
			catch(MalformedJwtException e)
			{
				System.out.println("Invalid jwt");
			}
		}
		else
		{
			System.out.println("JWT token does not starts with Bearer");
		}
		
		//once we get the token, now validate
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) 
		{
			UserDetails userdetails = this.userdetailsservice.loadUserByUsername(username);
			if(this.jwttokenhelper.validateToken(token, userdetails))
			{
				UsernamePasswordAuthenticationToken usernamepasswordauthenticationtoken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
				usernamepasswordauthenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationtoken);
			}
			else
			{
				System.out.println("invalid JWT");
			}
		}
		else
		{
			System.out.println("Username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
	}

}
