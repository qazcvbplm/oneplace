package com.ops.dao;

import com.ops.entity.ShopApply;

import java.util.List;

public interface ShopApplyMapper {
    int insert(ShopApply record);

    List<ShopApply> find();
}