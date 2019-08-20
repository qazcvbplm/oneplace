package com.ops.dao;

import com.ops.entity.PlaceOpertion;

import java.util.List;

public interface PlaceOpertionMapper {
    int insert(PlaceOpertion record);

    int insertSelective(PlaceOpertion record);

    PlaceOpertion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlaceOpertion record);

    int updateByPrimaryKey(PlaceOpertion record);

    List<PlaceOpertion> findByPlaceId(String placeId);

    int complete(int[] id);

    List<PlaceOpertion> findByPlaceId2(String placeId);
}