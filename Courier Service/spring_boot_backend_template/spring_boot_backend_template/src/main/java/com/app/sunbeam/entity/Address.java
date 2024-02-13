package com.app.sunbeam.entity;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Address extends BasicEntity{
	
	private String street;
	
	private String landmark;

	private String city;

	private String pincode;
	
	private String state;
	
	private String country;

}
