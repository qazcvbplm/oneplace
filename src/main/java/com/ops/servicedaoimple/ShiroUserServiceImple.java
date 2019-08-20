package com.ops.servicedaoimple;

import com.ops.dao.ShiroUserMapper;
import com.ops.entity.ShiroUser;
import com.ops.servicedao.ShiroUserService;
import com.ops.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShiroUserServiceImple implements ShiroUserService {

    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @Override
    public String add(ShiroUser user) {
        String ID = UUID.randomUUID().toString();
        user.setId(ID);
        if (checkUserNameExtis(user.getUsername())) {
            throw new RuntimeException("账号已经存在");
        } else {
            user.setPassword(Util.EnCode(user.getPassword()));
            shiroUserMapper.insert(user);
        }
        return ID;
    }

    public boolean checkUserNameExtis(String username) {
        return shiroUserMapper.findByUserName(username) == null ? false : true;
    }

    @Override
    public ShiroUser findByUserName(String username) {
        return shiroUserMapper.findByUserName(username);
    }


}
