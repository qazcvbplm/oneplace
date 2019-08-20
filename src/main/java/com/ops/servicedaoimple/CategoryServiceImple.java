package com.ops.servicedaoimple;

import com.ops.dao.CategoryMapper;
import com.ops.entity.Category;
import com.ops.servicedao.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImple implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public String add(Category category) {
        return categoryMapper.insert(category) + "";
    }

    @Override
    public int update(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public List<Category> findByShopId(String shopId) {
        return categoryMapper.findByShopId(shopId);
    }
}
