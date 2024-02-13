package com.app.sunbeam.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.sunbeam.entity.Courier;
import com.app.sunbeam.entity.User;

public interface CourierDao extends JpaRepository<Courier, Integer>{
	Courier findByTrackingNumber(String trackingNumber);

	List<Courier> findBySender(User sender);

	List<Courier> findByDeliveryPerson(User deliveryPerson);

	List<Courier> findByCourier(User courier);
}
