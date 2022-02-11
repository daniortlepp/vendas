package com.vendas.api.service;

import java.util.List;
import com.vendas.api.dao.OrderDAO;
import com.vendas.api.bean.Order;
import com.vendas.api.bean.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
public class OrderService {

	@Autowired
	OrderDAO orderDao;
	
	@Transactional
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	@Transactional
	public Order getOrder(int id) {
		return orderDao.getOrder(id);
	}
	
	@Transactional
	public List<Order> getOrderSearch(String param) {
		return orderDao.getOrderSearch(param);
	}

	@Transactional
	public void addOrder(Order order) {
		orderDao.addOrder(order);
	}

	@Transactional
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	@Transactional
	public void deleteOrder(int id) {
		orderDao.deleteOrder(id);
	}
}
