package com.gua.guaaop.service.impl;

import com.gua.guaaop.aop.RequestLog;
import com.gua.guaaop.pojo.Order;
import com.gua.guaaop.pojo.Result;
import com.gua.guaaop.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @RequestLog
    public Result buy(Order order) {
        if(order.getCode().equals("0")){
            throw new RuntimeException("fail");
        }
        Result result = new Result();
        result.setCode("1");
        result.setMsg("success");
        return result;
    }
}
