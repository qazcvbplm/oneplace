package com.ops.dao;

import com.ops.entity.WxUserBackPack;

import java.util.List;

public interface WxUserBackPackMapper {
    int insert(WxUserBackPack record);

    int insertSelective(WxUserBackPack record);

    WxUserBackPack selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxUserBackPack record);

    int updateByPrimaryKey(WxUserBackPack record);

    int check(String string);

    int add(WxUserBackPack kc);

    List<WxUserBackPack> findByuserId(String userId);

    int use(WxUserBackPack pack);
}