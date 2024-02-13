package com.app.sunbeam.dto;

import lombok.Data;

@Data
public class UserStatusUpdateRequestDto { 
	
	private int userId;
	
	private String status;

}
