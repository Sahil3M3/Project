package com.app.sunbeam.service;

import java.util.List;

import com.app.sunbeam.entity.User;



public interface UserService {

	public User saveUser(User u);
	public List<User>list();
	
}
