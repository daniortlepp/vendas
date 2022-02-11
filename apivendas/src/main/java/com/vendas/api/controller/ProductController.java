package com.vendas.api.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.vendas.api.bean.Product;
import com.vendas.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@CrossOrigin
	@RequestMapping(value = "/product", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Product> getProducts() {
		List<Product> listOfProducts = productService.getProducts();
		return listOfProducts;
	}

	@CrossOrigin
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Product getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/product/search", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public List<Product> getProductSearch(HttpServletRequest request) {
		String param = request.getParameter("param");
		List<Product> listOfProducts = productService.getProductSearch(param);
		return listOfProducts;
	}

	@CrossOrigin
	@RequestMapping(value = "/product", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addProduct(@RequestBody Product product) {	
		productService.addProduct(product);
		
	}

	@CrossOrigin
	@RequestMapping(value = "/product", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);		
	}	
}
