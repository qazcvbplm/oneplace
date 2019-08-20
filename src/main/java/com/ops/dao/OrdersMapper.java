package com.ops.dao;

import com.ops.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {
    int insert(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateStatus(Map<String, Object> oo);

    List<Orders> find(Orders orders);

}