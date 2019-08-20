package com.ops.dao;

import com.ops.entity.App;

public interface AppMapper {
    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}