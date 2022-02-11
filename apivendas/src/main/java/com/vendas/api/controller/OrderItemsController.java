package com.vendas.api.controller;

import java.util.List;
import com.vendas.api.bean.OrderItems;
import com.vendas.api.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemsController {
	
	@Autowired
	OrderItemsService orderItemsService;
	
	@CrossOrigin
	@RequestMapping(value = "/orderitems", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<OrderItems> getOrdersItems() {
		List<OrderItems> listOfOrdersItems = orderItemsService.getOrdersItems();
		return listOfOrdersItems;
	}

	@CrossOrigin
	@RequestMapping(value = "/orderitems/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public OrderItems getOrderItemsById(@PathVariable int id) {
		return orderItemsService.getOrderItems(id);
	}

	@CrossOrigin
	@RequestMapping(value = "/orderitems", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addOrderItems(@RequestBody OrderItems orderItems) {	
		orderItemsService.addOrderItems(orderItems);
		
	}

	@CrossOrigin
	@RequestMapping(value = "/orderitems", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateOrderItems(@RequestBody OrderItems orderItems) {
		orderItemsService.updateOrderItems(orderItems);
	}

	@CrossOrigin
	@RequestMapping(value = "/orderitems/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteOrderItems(@PathVariable("id") int id) {
		orderItemsService.deleteOrderItems(id);		
	}	
}
