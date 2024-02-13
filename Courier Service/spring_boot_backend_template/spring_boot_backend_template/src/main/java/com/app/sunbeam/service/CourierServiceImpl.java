package com.app.sunbeam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.sunbeam.Dao.CourierDao;
import com.app.sunbeam.entity.Courier;
import com.app.sunbeam.entity.User;

public class CourierServiceImpl implements CourierService{

	@Autowired
	private CourierDao courierDao;

	@Override
	public Courier addCourier(Courier courier) {
		return courierDao.save(courier);
	}

	@Override
	public Courier updateCourier(Courier courier) {
		return courierDao.save(courier);
	}

	@Override
	public Courier getCourierById(int courierId) {

		Optional<Courier> optionalCourier = courierDao.findById(courierId);

		if (optionalCourier.isPresent()) {
			return optionalCourier.get();
		}

		return null;

	}

	@Override
	public List<Courier> getAllCourier() {
		return courierDao.findAll();
	}

	@Override
	public Courier getCourierByTrackingNumber(String trackingNumber) {
		return courierDao.findByTrackingNumber(trackingNumber);
	}

	@Override
	public List<Courier> getCourierBySender(User sender) {
		return courierDao.findBySender(sender);
	}

	@Override
	public List<Courier> getCourierByCourier(User courier) {
		return courierDao.findByCourier(courier);
	}

	@Override
	public List<Courier> getCourierByDeliveryPerson(User deliveryPerson) {
		return courierDao.findByDeliveryPerson(deliveryPerson);
	}
}
