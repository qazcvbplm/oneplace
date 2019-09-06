package com.ops.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ops.entity.OrderProduct;

import java.util.List;

public interface OrderProductMapper extends BaseMapper<OrderProduct> {
    int insert(OrderProduct record);


    OrderProduct selectByPrimaryKey(Integer id);


    List<OrderProduct> findByOrder(String id);

}