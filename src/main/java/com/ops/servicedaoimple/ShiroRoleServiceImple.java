package com.ops.servicedaoimple;

import com.ops.dao.ShiroRoleMapper;
import com.ops.entity.ShiroRole;
import com.ops.servicedao.ShiroRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ShiroRoleServiceImple implements ShiroRoleService {

    @Autowired
    private ShiroRoleMapper shiroRoleMapper;

    @Override
    public String add(ShiroRole role) {
        String ID = UUID.randomUUID().toString();
        role.setId(ID);
        shiroRoleMapper.insert(role);
        return ID;
    }

    @Override
    public List<ShiroRole> findAll() {
        return shiroRoleMapper.findAll();
    }

    @Override
    public ShiroRole findById(String roleId) {
        return shiroRoleMapper.selectByPrimaryKey(roleId);
    }
}
