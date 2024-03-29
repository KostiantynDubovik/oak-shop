package com.dayz.shop.controllers;

import com.dayz.shop.ProcessMessage;
import com.dayz.shop.jpa.entities.*;
import com.dayz.shop.repository.UserRepository;
import com.dayz.shop.service.OrderService;
import com.dayz.shop.utils.OrderUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Aspect
@RestController
@RequestMapping("/api/order")
public class OrderController {

	private final OrderService orderService;
	private final UserRepository userRepository;

	@Autowired
	public OrderController(OrderService orderService,
	                       UserRepository userRepository) {
		this.orderService = orderService;
		this.userRepository = userRepository;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('STORE_READ')")
	public Order getOrder(@RequestAttribute Store store) {
		return OrderUtils.getCurrentOrder(store);
	}

	@GetMapping("all/{page}")
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAuthority('STORE_READ')")
	public List<Order> getAllUserOrders(@RequestAttribute Store store, OpenIDAuthenticationToken principal, @PathVariable int page, @RequestParam(defaultValue = "20") int pageSize) {
		Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize < 20 ? 20 : pageSize, Sort.by("timePlaced"));
		return orderService.getAllUserOrders((User) principal.getPrincipal(), store, pageable);
	}

	@GetMapping("all/{steamId}/{page}")
	@PreAuthorize("hasAuthority('STORE_WRITE')")
	public List<Order> getAllUserOrdersBySteamId(@RequestAttribute Store store, @PathVariable String steamId, @PathVariable int page, @RequestParam(defaultValue = "20") int pageSize) {
		Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, pageSize < 20 ? 20 : pageSize, Sort.by("timePlaced"));
		return orderService.getAllUserOrders(userRepository.getBySteamIdAndStore(steamId, store), store, pageable);
	}

	@PostMapping("add/{item}")
	@PreAuthorize("hasAuthority('STORE_READ')")
	public Order addOrderItem(@PathVariable Item item, @RequestAttribute Store store) {
		return orderService.addOrderItem(item, store);
	}

	@DeleteMapping("delete/{item}")
	@PreAuthorize("hasAuthority('STORE_READ')")
	public Order deleteOrderItem(@PathVariable Item item, @RequestAttribute Store store) {
		return orderService.deleteOrderItem(item, store);
	}

	@ProcessMessage
	@PostMapping("{item}/{server}")
	@PreAuthorize("hasAuthority('STORE_READ')")
	public Order buyItemNow(@PathVariable Item item, @PathVariable Server server, @RequestAttribute Store store) {
		try {
			return orderService.buyItemNow(item, store, server);
		} catch (Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}

	@ProcessMessage
	@PostMapping()
	@PreAuthorize("hasAuthority('STORE_READ')")
	public Order placeOrder(@RequestAttribute Store store) {
		try {
			return orderService.placeOrder(store);
		} catch (Exception e) {
			throw new ServerErrorException(e.getMessage(), e);
		}
	}
}
