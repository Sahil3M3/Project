package com.app.sunbeam.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sunbeam.entity.User;
import com.app.sunbeam.entity.UserRole;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
   
	
	
}
