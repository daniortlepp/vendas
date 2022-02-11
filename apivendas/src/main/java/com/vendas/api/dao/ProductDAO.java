package com.vendas.api.dao;

import java.util.List;
import com.vendas.api.bean.Product;
import com.vendas.api.utils.Utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Product> getProducts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> productList = session.createQuery("from Product").list();
		return productList;
	}

	public Product getProductById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.load(Product.class, new Integer(id));
		return product;
	}
	
	public List<Product> getProductSearch(String param) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = null;
		if (!Utils.isNumeric(param)) {
			query = session.createQuery("from Product where name like :param");
			query.setParameter("param", "%" + param + "%");
		} else {
			query = session.createQuery("from Product where id = :param");
			query.setParameter("param", Integer.parseInt(param));
		}
		List<Product> productList = query.list();
		return productList;
	}

	public Product addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
		return product;
	}

	public void updateProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(product);
	}

	public void deleteProduct(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.load(Product.class, new Integer(id));
		if (null != product) {
			session.delete(product);
		}
	}
}
