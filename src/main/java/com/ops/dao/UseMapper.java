package com.ops.dao;

import com.ops.entity.Use;

import java.util.List;

public interface UseMapper {
    int insert(Use record);

    int insertSelective(Use record);

    Use selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Use record);

    int updateByPrimaryKey(Use record);

    List<Use> find(Use use);
}