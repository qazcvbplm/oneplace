package com.ops.dao;

import com.ops.entity.Place;

import java.util.List;

public interface PlaceMapper {
    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);

    List<Place> find(Place place);

    List<Place> findByCon(String userId);

    List<Place> findUserId(String openid);
}