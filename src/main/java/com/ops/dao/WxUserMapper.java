package com.ops.dao;

import com.ops.entity.WxUser;

import java.util.List;
import java.util.Map;

public interface WxUserMapper {
    int insert(WxUser record);

    WxUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(WxUser record);

    List<WxUser> find(WxUser wxUser);

    int addmoney(Map<String, Object> mo);

    int payMoney(Map<String, Object> mo);

    int updateAddress(WxUser wxUser);

}