package com.vendas.api;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.vendas.api.bean.Product;
import com.vendas.api.dao.ProductDAO;
import com.vendas.api.service.ProductService;

public class ProductTest {
	
	@Autowired
	private ProductService productService;
	private ProductDAO productDao;

	@Test
	public void productSearchTest() {		
		List<Product> listOfProducts;
		listOfProducts = productService.getProductSearch("vinho");

		assertFalse(listOfProducts != null);
	}
	
	@Test
	public void productSearchByIdTest() {
		Product product = this.productDao.getProductById(1);
		assertTrue(product != null);
	}

}
