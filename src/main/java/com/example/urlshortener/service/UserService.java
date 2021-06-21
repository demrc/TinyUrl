package com.example.urlshortener.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.urlshortener.domain.UserDto;
import com.example.urlshortener.model.User;

public interface UserService extends UserDetailsService {
	User save(UserDto userRegistrationDto);
	User findByEmailAndPassword(String email,String password);
}
