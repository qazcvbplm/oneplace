package com.ops.entity;

public class WxUserBackPack {
    private String id;

    private String openid;

    private String image;

    private String name;

    private Integer count;


    public WxUserBackPack() {
        super();
    }

    public WxUserBackPack(String id, String openid, String image, String name, Integer count) {
        super();
        this.id = id;
        this.openid = openid;
        this.image = image;
        this.name = name;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}