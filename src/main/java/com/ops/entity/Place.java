package com.ops.entity;

import com.ops.util.MQO;

import java.util.Date;

public class Place {
    private String id;

    private String url;

    private String image;

    private String userid;

    private WxUser wxUser;

    private String shopid;

    private String shopName;

    private Long createtimelong;

    private Date createtime;

    private Integer isdelete;

    private MQO mqo = new MQO();

    private Boolean gz;

    private Integer updateCount;

    private String name;

    private String productName;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public Boolean getGz() {
        return gz;
    }

    public void setGz(Boolean gz) {
        this.gz = gz;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public MQO getMqo() {
        return mqo;
    }

    public void setMqo(MQO mqo) {
        this.mqo = mqo;
    }

    public Place(String id, String url, String image, String userid, String shopid, Long createtimelong) {
        super();
        this.id = id;
        this.url = url;
        this.image = image;
        this.userid = userid;
        this.shopid = shopid;
        this.createtimelong = createtimelong;
    }

    public Place() {
        super();
    }


    public Place(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid == null ? null : shopid.trim();
    }

    public Long getCreatetimelong() {
        return createtimelong;
    }

    public void setCreatetimelong(Long createtimelong) {
        this.createtimelong = createtimelong;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}