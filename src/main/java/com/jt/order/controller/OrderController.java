package com.jt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@RequestMapping("create")
	@ResponseBody
	public String orderCreate(@RequestBody String jsonData){
		try {
			Order order = MAPPER.readValue(jsonData, Order.class);
			String orderId = order.getUserId()+System.currentTimeMillis()+"";
			order.setOrderId(orderId);
			orderService.orderCreate(order);
			return orderId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("query/{orderId}")
	@ResponseBody
	public Order queryOrder(@PathVariable String orderId){
		return orderService.queryOrderByOrderId(orderId);
	}
	
}
