package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Orderentity;
import com.example.demo.entity.Userentity;

public class Apiresponse {

	private Userentity users;

	private List<Orderentity> orders;

	public Userentity getUserentity() {
		return users;
	}

	public void setUserentity(Userentity userentity) {
		this.users = userentity;
	}

	public List<Orderentity> getOrderentity() {
		return orders;
	}

	public void setOrderentity(List<Orderentity> orderentity) {
		this.orders = orderentity;
	}

	public Apiresponse(Userentity userentity, List<Orderentity> list) {
		super();
		this.users = userentity;
		this.orders = list;
	}

}
