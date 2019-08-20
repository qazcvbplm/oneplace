package com.ops.dao;

import com.ops.entity.ShiroPerms;

import java.util.List;

public interface ShiroPermsMapper {

    int insert(ShiroPerms record);

    ShiroPerms selectByPrimaryKey(Integer id);

    ShiroPerms checkUrlExits(String url);

    int enable(String[] url);


    List<ShiroPerms> groupByDes();

    List<ShiroPerms> findByDes(String des);
}