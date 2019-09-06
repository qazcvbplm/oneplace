package com.ops.servicedao;

import com.ops.dto.OrderAddRequest;
import com.ops.entity.Orders;

import java.util.List;

public interface OrdersService {


    Object charge(Orders order, String addr);

    String pay(String userId, String orderId);

    List<Orders> find(Orders orders);

    String add(Integer[] productIds, Integer[] numbers, Orders orders);

    String add2(OrderAddRequest orderAddRequest);
}
