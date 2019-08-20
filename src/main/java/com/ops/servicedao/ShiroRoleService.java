package com.ops.servicedao;

import com.ops.entity.ShiroRole;

import java.util.List;

public interface ShiroRoleService {

    String add(ShiroRole role);

    List<ShiroRole> findAll();

    ShiroRole findById(String roleId);

}
