package com.app.sunbeam.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.sunbeam.entity.User;

import lombok.Data;

@Data
public class UserResponseDto extends CommonApiResponse {
	
	private List<User> users = new ArrayList<>();

}
