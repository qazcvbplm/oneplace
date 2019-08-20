package com.ops.dao;

import com.ops.entity.WxUserFollow;

public interface WxUserFollowMapper {
    int insert(WxUserFollow record);

    WxUserFollow selectByPrimaryKey(Integer id);

    int check(WxUserFollow wuf);

    int remove(WxUserFollow wuf);

}