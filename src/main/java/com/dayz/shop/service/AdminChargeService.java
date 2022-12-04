package com.dayz.shop.service;

import com.dayz.shop.jpa.entities.OrderStatus;
import com.dayz.shop.jpa.entities.Payment;
import com.dayz.shop.jpa.entities.PaymentType;
import com.dayz.shop.repository.PaymentRepository;
import com.dayz.shop.repository.StoreConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminChargeService {
	private StoreConfigRepository storeConfigRepository;
	private PaymentRepository paymentRepository;

	@Autowired
	public AdminChargeService(StoreConfigRepository storeConfigRepository, PaymentRepository paymentRepository) {
		this.storeConfigRepository = storeConfigRepository;
		this.paymentRepository = paymentRepository;
	}

	public String initPayment(Payment payment) {
		payment.setPaymentStatus(OrderStatus.PENDING);
		payment.setPaymentType(PaymentType.ADMIN);
		payment = paymentRepository.save(payment);
		return buildRedirectUrl(payment);
	}

	private String buildRedirectUrl(Payment payment) {
		String url = ""; //TODO
		return url;
	}
}
