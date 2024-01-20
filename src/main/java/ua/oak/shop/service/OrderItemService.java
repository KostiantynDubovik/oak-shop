package ua.oak.shop.service;

import ua.oak.shop.jpa.entities.OrderItem;
import ua.oak.shop.repository.OrderItemRepository;
import ua.oak.shop.repository.OrderRepository;
import ua.oak.shop.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;

	@Autowired
	public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
	}

	public OrderItem updateOrderItemCount(OrderItem orderItem, Integer count) {
		orderItem.setCount(count);
		OrderUtils.recalculateOrder(orderItem.getOrder());
		orderRepository.save(orderItem.getOrder());
		return orderItemRepository.save(orderItem);
	}
}