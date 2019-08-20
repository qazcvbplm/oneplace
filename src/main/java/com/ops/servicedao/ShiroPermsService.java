package com.ops.servicedao;

import com.ops.entity.ShiroPerms;

import java.util.List;

public interface ShiroPermsService {

    String add(ShiroPerms perms);

    ShiroPerms checkUrlExits(String url);

    int enableAuth(String[] url);

    List<ShiroPerms> findAll();

}
