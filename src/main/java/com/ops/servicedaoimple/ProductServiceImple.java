package com.ops.servicedaoimple;

import com.ops.dao.ProductMapper;
import com.ops.entity.Product;
import com.ops.servicedao.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImple implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public String add(Product product) {
        return productMapper.insert(product) + "";
    }

    @Override
    public int update(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public List<Product> findByCategoryId(String categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }

    @Override
    public Product findById(int id) {
        return productMapper.selectByPrimaryKey(id);
    }


}
