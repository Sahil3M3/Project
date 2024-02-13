package com.app.sunbeam.service;

import com.app.sunbeam.entity.Address;

public interface AddressService {

Address addAddress(Address address);
	
	Address updateAddress(Address address);
	
	Address getAddressById(int addressId);
}
