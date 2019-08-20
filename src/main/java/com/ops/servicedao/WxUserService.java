package com.ops.servicedao;

import com.ops.entity.WxUser;
import com.ops.entity.WxUserBackPack;

import java.util.List;

public interface WxUserService {

    WxUser login(String openid);

    WxUser update(WxUser wxUser);

    List<WxUser> find(WxUser wxUser);

    List<WxUserBackPack> findzz(String userId);

}
