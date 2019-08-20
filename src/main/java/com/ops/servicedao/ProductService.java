package com.ops.servicedao;

import com.ops.entity.Product;

import java.util.List;

public interface ProductService {

    String add(Product product);

    int update(Product product);

    List<Product> findByCategoryId(String categoryId);

    Product findById(int id);


}
