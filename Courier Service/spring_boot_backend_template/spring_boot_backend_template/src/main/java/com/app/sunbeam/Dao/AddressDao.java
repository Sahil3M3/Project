package com.app.sunbeam.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.sunbeam.entity.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {

 
}
