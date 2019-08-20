package com.ops.dao;

import com.ops.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findByCategoryId(String categoryId);

    int sale(Map<String, Object> so);
}