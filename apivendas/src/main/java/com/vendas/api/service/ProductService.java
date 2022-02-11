package com.vendas.api.service;

import java.util.List;
import com.vendas.api.dao.ProductDAO;
import com.vendas.api.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
public class ProductService {

	@Autowired
	ProductDAO productDao;
	
	@Transactional
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Transactional
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}
	
	@Transactional
	public List<Product> getProductSearch(String param) {
		return productDao.getProductSearch(param);
	}

	@Transactional
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	@Transactional
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	@Transactional
	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
	}
}
