package com.ops.dto;

import com.ops.entity.Orders;

import java.util.List;

public class OrderAddRequest {

    private List<Long> productId;

    private List<Integer> counts;

    private Orders orders;

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
        this.productId = productId;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
