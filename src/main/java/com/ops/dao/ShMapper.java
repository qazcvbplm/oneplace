package com.ops.dao;

import com.ops.entity.Sh;

import java.util.List;

public interface ShMapper {
    int insert(Sh record);

    int insertSelective(Sh record);

    Sh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sh record);

    int updateByPrimaryKey(Sh record);

    List<Sh> find(Sh sh);
}