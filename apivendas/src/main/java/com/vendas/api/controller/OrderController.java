package com.vendas.api.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vendas.api.bean.Order;
import com.vendas.api.service.OrderItemsService;
import com.vendas.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	OrderItemsService orderItemsService;
	
	public static final long HOUR = 3600*1000;
	
	@CrossOrigin
	@RequestMapping(value = "/order", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Order> getOrders() {
		List<Order> listOfOrders = orderService.getOrders();
		return listOfOrders;
	}

	@CrossOrigin
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Order getOrderById(@PathVariable int id) {
		return orderService.getOrder(id);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/order/search", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public List<Order> getOrderSearch(HttpServletRequest request) {
		String param = request.getParameter("param");
		List<Order> listOfOrders = orderService.getOrderSearch(param);
		return listOfOrders;
	}

	@CrossOrigin
	@RequestMapping(value = "/order", method = RequestMethod.POST, headers = "Accept=application/json")
	public Order addOrder(@RequestBody Order order) throws ParseException {
		Date now = new Date();
		Date date = new Date(now.getTime() - 3 * HOUR);
		order.setDate(date);
		orderService.addOrder(order);
		return order;
		
	}

	@CrossOrigin
	@RequestMapping(value = "/order", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateOrder(@RequestBody Order order) {
		orderService.updateOrder(order);
	}

	@CrossOrigin
	@RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteOrder(@PathVariable("id") int id) {
		orderService.deleteOrder(id);		
	}	
	
}
