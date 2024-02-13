package com.app.sunbeam.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.sunbeam.entity.User;


public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmailId(String email);

	User findByEmailIdAndStatus(String email, String status);

	User findByRoleAndStatusIn(String role, List<String> status);

	List<User> findByRole(String role);

	List<User> findByCourierAndRole(User courier, String role);

	List<User> findByCourierAndRoleAndStatusIn(User courier, String role, List<String> status);

	User findByEmailIdAndRoleAndStatus(String emailId, String role, String status);

	List<User> findByRoleAndStatus(String role, String status);

	User findByCustomerRefIdAndStatus(String customerRefId, String status);

	
}
