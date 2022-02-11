package com.vendas.api.dao;

import java.util.List;
import com.vendas.api.bean.Order;
import com.vendas.api.utils.Utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Order> getOrders() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Order> orderList = session.createQuery("from Order").list();
		return orderList;
	}

	public Order getOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Order order = (Order) session.load(Order.class, new Integer(id));
		return order;
	}
	
	public List<Order> getOrderSearch(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		if (!Utils.isNumeric(param)) {
			query = session.createQuery("from Order where customerName like :param");
			query.setParameter("param", "%" + param + "%");
		} else {
			query = session.createQuery("from Order where id = :param");
			query.setParameter("param", Integer.parseInt(param));
		}
		List<Order> orderList = query.list();
		return orderList;
	}

	public Order addOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		return order;
	}

	public void updateOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(order);
	}

	public void deleteOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Order order = (Order) session.load(Order.class, new Integer(id));
		if (null != order) {
			session.delete(order);
		}
	}
	
}
