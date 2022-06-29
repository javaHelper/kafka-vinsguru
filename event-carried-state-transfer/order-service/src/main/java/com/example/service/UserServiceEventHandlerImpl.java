package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.entity.PurchaseOrder;
import com.example.entity.User;
import com.example.repository.PurchaseOrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceEventHandlerImpl implements UserServiceEventHandler {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Override
	public void updateUser(User user) {
		List<PurchaseOrder> userOrders = this.purchaseOrderRepository.findByUserId(user.getId());
		userOrders.forEach(p -> p.setUser(user));
		this.purchaseOrderRepository.saveAll(userOrders);
	}


	@KafkaListener(topics = "user-service-event")
	public void consume(String userStr) {
		try {
			User user = OBJECT_MAPPER.readValue(userStr, User.class);
			this.updateUser(user);
		} catch (JsonProcessingException e) {
			log.error("## UserServiceEventHandlerImpl | consume : {}", e.getMessage());
		}
	}
}
