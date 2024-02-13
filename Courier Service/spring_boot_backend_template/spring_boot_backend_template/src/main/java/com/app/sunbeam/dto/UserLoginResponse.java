package com.app.sunbeam.dto;



import com.app.sunbeam.entity.User;

import lombok.Data;

@Data
public class UserLoginResponse extends CommonApiResponse {

	private User user;
	
	private String jwtToken;

}
