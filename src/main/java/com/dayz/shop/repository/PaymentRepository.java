package com.dayz.shop.repository;


import com.dayz.shop.jpa.entities.Payment;
import com.dayz.shop.jpa.entities.PaymentType;
import com.dayz.shop.jpa.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	List<Payment> findAllByUser(User user);
	List<Payment> findAllByUserAndPaymentTypeNotIn(User user, Collection<PaymentType> paymentTypes);
}
