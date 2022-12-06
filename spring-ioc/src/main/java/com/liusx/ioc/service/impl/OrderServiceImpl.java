package com.liusx.ioc.service.impl;

import com.liusx.ioc.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public String getOrderName() {
        return "order";
    }
}
