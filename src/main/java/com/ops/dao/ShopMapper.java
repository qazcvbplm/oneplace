package com.ops.dao;

import com.ops.entity.Shop;

import java.util.List;

public interface ShopMapper {
    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<Shop> find(Shop shop);
}