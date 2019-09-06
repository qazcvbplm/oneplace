package com.ops.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ops.entity.Orders;

import java.util.List;
import java.util.Map;

public interface OrdersMapper extends BaseMapper<Orders> {

    Orders selectByPrimaryKey(String id);

    int updateStatus(Map<String, Object> oo);

    List<Orders> find(Orders orders);

}