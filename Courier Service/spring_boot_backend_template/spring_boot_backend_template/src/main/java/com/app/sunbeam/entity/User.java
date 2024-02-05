package com.app.sunbeam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
@Entity
public class User {

	    @javax.persistence.Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(unique = true, nullable = false)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserRole role;

	    
	    public User()
	    {
	    	
	    }
	    
	    
		public User(Integer id, String username, String password, UserRole role) {
		
			this.id = id;
			this.username = username;
			this.password = password;
			this.role = role;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

	    
	
}
