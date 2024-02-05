package com.app.sunbeam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sunbeam.entity.User;
import com.app.sunbeam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public String add(@RequestBody User s)
	{
		 userService.saveUser(s);
		 return "New User added";
	}
	
	@GetMapping("/get")
	public List<User> list()
	{
		return userService.list();
	}
}
