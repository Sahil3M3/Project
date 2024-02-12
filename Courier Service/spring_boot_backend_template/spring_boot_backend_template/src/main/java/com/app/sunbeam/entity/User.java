package com.app.sunbeam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
@Entity
public class User extends BasicEntity {

	
	@Column
	private String email;
	
	@Column
    private int pinCode;
	
	    @Column
	    private String area;
	
	    @Column(nullable = false)
	    private int number;
	

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false)
	    private String password;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserRole role;

	    
	    
	    
	   }
