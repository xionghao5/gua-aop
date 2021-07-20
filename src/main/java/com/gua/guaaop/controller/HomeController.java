package com.gua.guaaop.controller;

import com.gua.guaaop.pojo.Order;
import com.gua.guaaop.pojo.Result;
import com.gua.guaaop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/buy")
    public Result buy(@RequestBody Order order) {
        return orderService.buy(order);
    }
}
