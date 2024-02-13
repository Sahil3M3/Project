package com.app.sunbeam.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.app.sunbeam.Utility.Constants.ActiveStatus;
import com.app.sunbeam.Utility.Constants.UserRole;
import com.app.sunbeam.Utility.Helper;
import com.app.sunbeam.dto.CommonApiResponse;
import com.app.sunbeam.dto.RegisterUserRequestDto;
import com.app.sunbeam.dto.UserLoginRequest;
import com.app.sunbeam.dto.UserLoginResponse;
import com.app.sunbeam.dto.UserResponseDto;
import com.app.sunbeam.dto.UserStatusUpdateRequestDto;
import com.app.sunbeam.entity.Address;
import com.app.sunbeam.entity.User;
import com.app.sunbeam.exception.UserNotFoundException;
import com.app.sunbeam.exception.UserSaveFailedException;
import com.app.sunbeam.service.AddressService;
import com.app.sunbeam.service.UserService; 

@Component
@Transactional
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    public ResponseEntity<CommonApiResponse> registerAdmin(RegisterUserRequestDto registerRequest) {
        CommonApiResponse response = new CommonApiResponse();

        if (registerRequest == null || registerRequest.getEmailId() == null || registerRequest.getPassword() == null) {
            response.setResponseMessage("Invalid or missing input");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User existingUser = this.userService.getUserByEmailAndStatus(registerRequest.getEmailId(),
                ActiveStatus.ACTIVE.value());

        if (existingUser != null) {
            response.setResponseMessage("User already registered with this email");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = RegisterUserRequestDto.toUserEntity(registerRequest);
        user.setRole(UserRole.ROLE_ADMIN.value());
        user.setStatus(ActiveStatus.ACTIVE.value());

        existingUser = this.userService.addUser(user);

        if (existingUser == null) {
            response.setResponseMessage("Failed to register admin");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.setResponseMessage("Admin registered successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //2nd
    public ResponseEntity<CommonApiResponse> registerUser(RegisterUserRequestDto request) {

    	CommonApiResponse response = new CommonApiResponse();
    	
        if (request == null || request.getEmailId() == null || request.getPassword() == null) {
            response.setResponseMessage("Invalid or missing input");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User existingUser = this.userService.getUserByEmailAndStatus(request.getEmailId(), ActiveStatus.ACTIVE.value());

        if (existingUser != null) {
            response.setResponseMessage("User with this email already registered");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (request.getRole() == null) {
            response.setResponseMessage("Role is missing in the request");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = RegisterUserRequestDto.toUserEntity(request);
        
        if (user.getRole().equals(UserRole.ROLE_CUSTOMER.value())) {
            user.setCustomerRefId(Helper.getAlphaNumericUniqueId(5));
        } else if (user.getRole().equals(UserRole.ROLE_COURIER.value())) {
            user.setCourierRefId(Helper.getAlphaNumericUniqueId(5));
        }

        user.setStatus(ActiveStatus.ACTIVE.value());

        // Delivery person is for Courier, so we need to set the Courier
        if (user.getRole().equals(UserRole.ROLE_DELIVERY.value())) {
            User courier = this.userService.getUserById(request.getCourierId());
            if (courier == null) {
                throw new UserNotFoundException("Courier not found");
            }
            user.setCourier(courier);
        }

        Address address = RegisterUserRequestDto.toAddressEntity(request);
        Address savedAddress = this.addressService.addAddress(address);
        if (savedAddress == null) {
            throw new UserSaveFailedException("Registration failed, exception occurred while saving address");
        }
        user.setAddress(savedAddress);

        existingUser = this.userService.addUser(user);
        if (existingUser == null) {
            throw new UserSaveFailedException("Registration failed, exception occurred while saving user");
        }

        response.setResponseMessage("User registered successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    // 3 rd
    public ResponseEntity<UserLoginResponse> login(UserLoginRequest loginRequest) {
    
    	UserLoginResponse response = new UserLoginResponse();

		if (loginRequest == null) {
			response.setResponseMessage("Missing Input");
			response.setSuccess(false);

			return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User user = null;
		
		try {
			
			user = this.userService.getUserByEmailIdAndRoleAndStatus(loginRequest.getEmailId(), loginRequest.getRole(),
				ActiveStatus.ACTIVE.value());

			
			response.setUser(user);
			response.setResponseMessage("Logged in sucessful");
			response.setSuccess(true);
			return new ResponseEntity<UserLoginResponse>(response, HttpStatus.OK);

		} catch (Exception ex) {
			response.setResponseMessage("Invalid email or password.");
			response.setSuccess(false);
			return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
		}
    	
    }

    public ResponseEntity<UserResponseDto> getUsersByRole(String role) {
     
    	UserResponseDto response = new UserResponseDto();

		if (role == null) {
			response.setResponseMessage("missing role");
			response.setSuccess(false);
			return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<User> users = new ArrayList<>();

		users = this.userService.getUserByRoleAndStatus(role, ActiveStatus.ACTIVE.value());

		if (users.isEmpty()) {
			response.setResponseMessage("No Users Found");
			response.setSuccess(false);
		}

		List<User> userDtos = new ArrayList<>();

		for (User user : users) {
			userDtos.add(user);
		}

		response.setUsers(userDtos);
		response.setResponseMessage("User Fetched Successfully");
		response.setSuccess(true);

		return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> updateUserStatus(UserStatusUpdateRequestDto request) {
       
    	CommonApiResponse response = new CommonApiResponse();

		if (request == null) {
			response.setResponseMessage("bad request, missing data");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getUserId() == 0 || request.getStatus() == null) {
			response.setResponseMessage("bad request, missing request data");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		

		User user = null;
		user = this.userService.getUserById(request.getUserId());

		user.setStatus(request.getStatus());

		User updatedUser = this.userService.updateUser(user);

		if (updatedUser == null) {
			throw new UserSaveFailedException("Failed to update the User status");
		}

		response.setResponseMessage("User " + request.getStatus() + " Successfully!!!");
		response.setSuccess(true);
		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

    }

    public ResponseEntity<UserResponseDto> getDeliveryPersonsByCourier(int courierId) {
        
    	UserResponseDto response = new UserResponseDto();

		if (courierId == 0) {
			response.setResponseMessage("missing courier id");
			response.setSuccess(false);
			return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User courier = this.userService.getUserById(courierId);

		if (courier == null) {
			throw new UserNotFoundException("Courier Not Found in DB");
		}

		List<User> users = new ArrayList<>();

		users = this.userService.getUserByCourierAndRoleAndStatusIn(courier, UserRole.ROLE_DELIVERY.value(),
				Arrays.asList(ActiveStatus.ACTIVE.value()));

		if (users.isEmpty()) {
			response.setResponseMessage("No Delivery Guys Found");
			response.setSuccess(false);
		}

		response.setUsers(users);
		response.setResponseMessage("User Fetched Successfully");
		response.setSuccess(true);

		return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
    	
    }

    public ResponseEntity<CommonApiResponse> deleteCourier(int courierId) {
    	UserResponseDto response = new UserResponseDto();

		if (courierId == 0) {
			response.setResponseMessage("missing courier id");
			response.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User courier = this.userService.getUserById(courierId);

		if (courier == null) {
			response.setResponseMessage("Courier not found");
			response.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<User> deliveryPersons = new ArrayList<>();

		deliveryPersons = this.userService.getUserByCourierAndRoleAndStatusIn(courier, UserRole.ROLE_DELIVERY.value(),
				Arrays.asList(ActiveStatus.ACTIVE.value()));

		courier.setStatus(ActiveStatus.DEACTIVATED.value());
		User deletedCourier = this.userService.updateUser(courier);

		// deactivating the courier
		if (deletedCourier == null) {
			throw new UserSaveFailedException("Failed to deactivate the courier!!!");
		}

		// deactivating the all couriers delivery persons
		if (!deliveryPersons.isEmpty()) {

			for (User deliveryPerson : deliveryPersons) {
				deliveryPerson.setStatus(ActiveStatus.DEACTIVATED.value());
			}

			List<User> deletedDeliveryPerons = this.userService.updateAllUser(deliveryPersons);

			if (CollectionUtils.isEmpty(deletedDeliveryPerons)) {
				throw new UserSaveFailedException("Failed to deactivate the courier!!!");
			}

		}

		response.setResponseMessage("Courier Deactivated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> deleteDeliveryPerson(int deliveryId) {

		UserResponseDto response = new UserResponseDto();

		if (deliveryId == 0) {
			response.setResponseMessage("missing delivery person id");
			response.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User delivery = this.userService.getUserById(deliveryId);

		if (delivery == null) {
			response.setResponseMessage("Delivery Person not found");
			response.setSuccess(false);
			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		delivery.setStatus(ActiveStatus.DEACTIVATED.value());

		User deletedDelivery = this.userService.updateUser(delivery);

		// deactivating the seller
		if (deletedDelivery == null) {
			throw new UserSaveFailedException("Failed to deactivate the delivery person!!!");
		}

		response.setResponseMessage("Delivery Person Deactivated Successful");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDto> getUserByUserId(int userId) {


		UserResponseDto response = new UserResponseDto();

		if (userId == 0) {
			response.setResponseMessage("missing courier id");
			response.setSuccess(false);
			return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(userId);

		if (user == null) {
			throw new UserNotFoundException("User Not Found in DB");
		}

		response.setUsers(Arrays.asList(user));
		response.setResponseMessage("User Fetched Successfully");
		response.setSuccess(true);

		return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
	
    }
}
