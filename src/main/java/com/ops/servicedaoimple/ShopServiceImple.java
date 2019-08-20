package com.ops.servicedaoimple;

import com.ops.dao.ShopMapper;
import com.ops.entity.Shop;
import com.ops.servicedao.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ShopServiceImple implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public String add(Shop shop) {
        String Id = UUID.randomUUID().toString();
        shop.setId(Id);
        shopMapper.insert(shop);
        return Id;
    }

    @Override
    public List<Shop> find(Shop shop) {
        return shopMapper.find(shop);
    }

    @Override
    public int update(Shop shop) {
        return shopMapper.updateByPrimaryKeySelective(shop);
    }


}
