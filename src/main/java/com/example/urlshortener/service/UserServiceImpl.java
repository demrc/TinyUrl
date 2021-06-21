package com.example.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.urlshortener.domain.UserDto;
import com.example.urlshortener.model.User;
import com.example.urlshortener.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
			
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				registrationDto.getPassword());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmailAndPassword(username,"");
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),null);
	}

	@Override
	public User findByEmailAndPassword(String email,String password) {
		User user = userRepository.findByEmailAndPassword(email,password);
		return user;
	}
}