package com.ops.servicedao;

import com.ops.entity.Category;

import java.util.List;

public interface CategoryService {

    String add(Category category);

    int update(Category category);

    List<Category> findByShopId(String shopId);

}
