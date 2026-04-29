package com.example.demo.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public class Orderentity {

	@Column(value = "OrderId")
	private Long orderId;

	private Double amount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Orderentity(Long orderId, Double amount) {
		super();
		this.orderId = orderId;
		this.amount = amount;
	}

}
