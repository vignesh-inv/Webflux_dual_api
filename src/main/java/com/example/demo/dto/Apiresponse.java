package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Orderentity;
import com.example.demo.entity.Userentity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder({"users","orders","errormessage"})
public class Apiresponse {

	private Userentity users;

	private List<Orderentity> orders;
	
	private String status;

	public Userentity getUsers() {
		return users;
	}

	public void setUsers(Userentity users) {
		this.users = users;
	}

	public Object getOrders() {
		return orders;
	}

	public void setOrders(List<Orderentity> orders) {
		this.orders = orders;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Apiresponse(Userentity users, List<Orderentity> orders, String status) {
		super();
		this.users = users;
		this.orders = orders;
		this.status = status;
	}

	public Apiresponse() {
		super();
	}

	public Apiresponse(String status) {
		super();
		this.status = status;
	}

	
	


}
