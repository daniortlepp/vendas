package com.vendas.api.service;

import java.util.List;
import com.vendas.api.dao.OrderItemsDAO;
import com.vendas.api.bean.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderItemsService")
public class OrderItemsService {

	@Autowired
	OrderItemsDAO orderItemsDao;
	
	@Transactional
	public List<OrderItems> getOrdersItems() {
		return orderItemsDao.getOrdersItems();
	}

	@Transactional
	public OrderItems getOrderItems(int id) {
		return orderItemsDao.getOrderItems(id);
	}

	@Transactional
	public void addOrderItems(OrderItems orderItems) {
		orderItemsDao.addOrderItems(orderItems);
	}

	@Transactional
	public void updateOrderItems(OrderItems orderItems) {
		orderItemsDao.updateOrderItems(orderItems);
	}

	@Transactional
	public void deleteOrderItems(int id) {
		orderItemsDao.deleteOrderItems(id);
	}
}
