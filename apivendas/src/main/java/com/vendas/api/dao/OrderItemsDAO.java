package com.vendas.api.dao;

import java.util.List;

import com.vendas.api.bean.OrderItems;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<OrderItems> getOrdersItems() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OrderItems> orderItemsList = session.createQuery("from OrderItems").list();
		return orderItemsList;
	}

	public OrderItems getOrderItems(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrderItems orderItems = (OrderItems) session.load(OrderItems.class, new Integer(id));
		return orderItems;
	}

	public OrderItems addOrderItems(OrderItems orderItems) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderItems);
		return orderItems;
	}

	public void updateOrderItems(OrderItems orderItems) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(orderItems);
	}

	public void deleteOrderItems(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrderItems orderItems = (OrderItems) session.load(OrderItems.class, new Integer(id));
		if (null != orderItems) {
			session.delete(orderItems);
		}
	}	
}
