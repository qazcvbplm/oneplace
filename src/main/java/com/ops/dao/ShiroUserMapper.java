package com.ops.dao;

import com.ops.entity.ShiroUser;

public interface ShiroUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShiroUser record);

    int insertSelective(ShiroUser record);

    ShiroUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShiroUser record);

    int updateByPrimaryKey(ShiroUser record);

    ShiroUser findByUserName(String username);

}