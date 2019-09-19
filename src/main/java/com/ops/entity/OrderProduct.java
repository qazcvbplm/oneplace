package com.ops.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.persistence.Id;
import java.math.BigDecimal;

public class OrderProduct {
    @TableId(type = IdType.AUTO)
    @Id
    private Integer id;

    private String productname;

    private String productimage;

    private Integer numbers;

    private BigDecimal productprice;

    private String orderid;

    private BigDecimal totalprice;

    private String producttype;

    private Integer productId;


    public Integer getProductId() {
        return productId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public OrderProduct(Product product, Integer number, String orderId2) {
        super();
        this.setProductId(product.getId());
        this.setProductname(product.getName());
        this.setProductimage(product.getImage());
        this.setNumbers(number);
        this.setProductprice(product.getPrice());
        this.setProducttype(product.getType());
        this.orderid = orderId2;
        this.totalprice = this.productprice.multiply(new BigDecimal(this.numbers));
    }


    public OrderProduct() {
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage == null ? null : productimage.trim();
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public BigDecimal getProductprice() {
        return productprice;
    }

    public void setProductprice(BigDecimal productprice) {
        this.productprice = productprice;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }
}