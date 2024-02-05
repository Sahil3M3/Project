package com.app.sunbeam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sunbeam.Dao.UserDao;
import com.app.sunbeam.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	
	private UserDao userDao;
	
 @Override
	public User saveUser(User u) {
		// TODO Auto-generated method stub
		return userDao.save(u);
	}

@Override
public List<User> list() {
	
	return userDao.findAll();
}
	


}
