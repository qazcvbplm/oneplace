package com.ops.dao;

import com.ops.entity.OrderProduct;

import java.util.List;

public interface OrderProductMapper {
    int insert(OrderProduct record);


    OrderProduct selectByPrimaryKey(Integer id);


    List<OrderProduct> findByOrder(String id);

}