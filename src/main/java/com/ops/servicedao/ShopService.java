package com.ops.servicedao;

import com.ops.entity.Shop;

import java.util.List;

public interface ShopService {

    String add(Shop shop);

    List<Shop> find(Shop shop);

    int update(Shop shop);

}
