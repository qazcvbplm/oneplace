package com.ops.servicedaoimple;

import com.ops.dao.ShiroPermsMapper;
import com.ops.entity.ShiroPerms;
import com.ops.servicedao.ShiroPermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiroPermsServiceImple implements ShiroPermsService {


    @Autowired
    private ShiroPermsMapper shiroPermsMapper;

    @Override
    public String add(ShiroPerms perms) {
        shiroPermsMapper.insert(perms);
        return null;
    }


    @Override
    public ShiroPerms checkUrlExits(String url) {
        return shiroPermsMapper.checkUrlExits(url);
    }

    @Override
    public int enableAuth(String[] url) {
        return shiroPermsMapper.enable(url);
    }


    @Override
    public List<ShiroPerms> findAll() {
        List<ShiroPerms> des = shiroPermsMapper.groupByDes();
        for (ShiroPerms temp : des) {
            temp.setPerm(shiroPermsMapper.findByDes(temp.getDes()));
        }
        return des;
    }


}
