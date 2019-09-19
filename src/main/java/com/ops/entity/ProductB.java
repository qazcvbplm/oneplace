package com.ops.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import ops.model.X.base.entity.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product_b")
public class ProductB implements BaseEntity {

    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ca_id", nullable = false)
    private Long caId;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sub_name", nullable = false)
    private String subName;

    @Column(name = "images", nullable = false)
    private String images;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "unit", nullable = false, length = 30)
    private String unit;

    @Lob
    @Column(name = "rich_text", nullable = false)
    private String richText;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "stock", nullable = false, columnDefinition = "int default 0")
    private Integer stock;

    @TableLogic
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer deleted;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCaId() {
        return caId;
    }

    public void setCaId(Long caId) {
        this.caId = caId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public void beAdd() {

    }

    @Override
    public void beUpdate() {

    }
}
