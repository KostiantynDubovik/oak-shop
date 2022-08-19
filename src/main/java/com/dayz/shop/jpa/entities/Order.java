package com.dayz.shop.jpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ORDERS")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Order {
	@Id
	@Column(name = "ORDER_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
	@ToString.Exclude
	@JsonBackReference
	private List<OrderItem> orderItems;

	@Column(name = "ORDER_TOTAL")
	private BigDecimal orderTotal;

	@ManyToOne
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "orders_users_STORE_ID_fk"))
	@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	@JsonBackReference
	private Store store;

	@ManyToOne
	@JoinColumn(name = "SERVER_ID")
	@JsonBackReference
	private Server server;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private OrderStatus status;

	public void addOrderItem(OrderItem orderItem) {
		if (orderItems.add(orderItem)) {
			orderTotal = orderTotal.add(orderItem.getPrice());
		}
	}

	public void removeOrderItem(OrderItem orderItem) {
		if (orderItems.remove(orderItem)) {
			orderTotal = orderTotal.subtract(orderItem.getPrice());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Order order = (Order) o;
		return id != null && Objects.equals(id, order.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, orderItems, orderTotal, user, store, server, status);
	}
}
