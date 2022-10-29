package com.micros.security1.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.micros.security1.entity.TrainUser;
import com.micros.security1.repository.UserRepository;

@Service
public class userService implements UserDetailsService{
	
	@Autowired
	private UserRepository urepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, NullPointerException {
		// TODO Auto-generated method stub
		TrainUser trainUser = urepo.getTheUser(username);
		System.out.println(trainUser);
		if (trainUser == null)
		{
			throw new NullPointerException("Sorry Vro! You aren't allowed");
		}
		String email = trainUser.getEmail();
		String passwd = trainUser.getPassword();
		return new User(email, passwd, new ArrayList<>());
	}

}
