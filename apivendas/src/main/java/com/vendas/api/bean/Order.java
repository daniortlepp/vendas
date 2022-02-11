package com.vendas.api.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="`order`")
@Proxy(lazy = false)
public class Order {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(name="customerName")
	String customerName;	
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	Date date;
	
	@Column(name="total")
	double total;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<OrderItems> items;
	
	public Order() {
		super();
	}
	
	public Order(int id, String customerName, Date date, double total, OrderItems items) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.date = date;
		this.total = total;
		this.items = new ArrayList<OrderItems>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public List<OrderItems> getItems() {
		return items;
	}

	public void setItems(List<OrderItems> items) {
		this.items = items;
	}
	
}