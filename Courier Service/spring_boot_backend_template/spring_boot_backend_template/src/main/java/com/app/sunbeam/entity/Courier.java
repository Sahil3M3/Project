package com.app.sunbeam.entity;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Courier extends BasicEntity {

	

	private String courierName;

	private String trackingNumber; // A unique identifier for each courier package

	private String courierDate;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender; // The customer who sent the courier package

	@OneToOne
	@JoinColumn(name = "receiver_address_id")
	private Address receiverAddress; // Address of the receiver

	private String receiverName; // Name of the receiver

	private String courierType; // Type of the courier (e.g., luggage, document, gift, etc.)

	private double weight; // Weight of the courier package

	private String deliveryStatus;

	private String deliveryDate;

	private String deliveryTime; // evening, afternoon....

	private String message; // General message related to the courier, updated by Delivery Person

	@ManyToOne
	@JoinColumn(name = "delivery_person_id")
	private User deliveryPerson; // The delivery person who will deliver courier package
	
	@ManyToOne
	@JoinColumn(name = "courier_id")
	private User courier; // The courier login who has added Courier entry for customer to deliver
}
