package com.ops.dao;

import com.ops.entity.ShiroRole;

import java.util.List;

public interface ShiroRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShiroRole record);

    int insertSelective(ShiroRole record);

    ShiroRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShiroRole record);

    int updateByPrimaryKey(ShiroRole record);

    List<ShiroRole> findAll();

}