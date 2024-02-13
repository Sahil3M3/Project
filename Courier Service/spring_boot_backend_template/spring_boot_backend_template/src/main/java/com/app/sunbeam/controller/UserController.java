package com.app.sunbeam.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.sunbeam.dto.CommonApiResponse;
import com.app.sunbeam.dto.RegisterUserRequestDto;
import com.app.sunbeam.dto.UserLoginRequest;
import com.app.sunbeam.dto.UserLoginResponse;
import com.app.sunbeam.dto.UserResponseDto;
import com.app.sunbeam.dto.UserStatusUpdateRequestDto;
import com.app.sunbeam.entity.User;
import com.app.sunbeam.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.app.sunbeam.resource.UserResource;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {


	@Autowired
	private UserResource userResource;

	// RegisterUserRequestDto, we will set only email, password & role from UI
	@PostMapping("/admin/register")
	public ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody RegisterUserRequestDto request) {
		System.out.println(request);
		return userResource.registerAdmin(request);
	}

	// for customer and restaurant register
	@PostMapping("register")
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
		return this.userResource.registerUser(request);
	}
	
	@PostMapping("login")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		return userResource.login(userLoginRequest);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<UserResponseDto> fetchUser(@RequestParam("userId") int userId) {
		return userResource.getUserByUserId(userId);
	}
	
	@GetMapping("/fetch/role-wise")
	public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role) throws JsonProcessingException {
		return userResource.getUsersByRole(role);
	}
	
	@GetMapping("/fetch/courier/delivery-person")
	public ResponseEntity<UserResponseDto> fetchDeliveryPerson(@RequestParam("courierId") int courierId) {
		return userResource.getDeliveryPersonsByCourier(courierId);
	}
	
	@PutMapping("update/status")
	public ResponseEntity<CommonApiResponse> updateUserStatus(@RequestBody UserStatusUpdateRequestDto request) {
		return userResource.updateUserStatus(request);
	}
	
	@DeleteMapping("delete/courier")
	public ResponseEntity<CommonApiResponse> deleteRestaurant(@RequestParam("courierId") int courierId) {
		return userResource.deleteCourier(courierId);
	}
	
	@DeleteMapping("delete/courier/delivery-person")
	public ResponseEntity<CommonApiResponse> deleteDeliveryPerson(@RequestParam("deliveryId") int deliveryId) {
		return userResource.deleteDeliveryPerson(deliveryId);
	}
}
