package com.ops.servicedao;

import com.ops.entity.ShiroUser;

public interface ShiroUserService {

    String add(ShiroUser user);

    ShiroUser findByUserName(String username);

}
