package com.ops.entity;

import com.ops.util.MQO;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class Article {
    private Integer id;
    @NotEmpty
    private String userid;
    @NotEmpty
    private String type;

    private Date createtime;
    @NotEmpty
    private String images;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    private Integer isshow;

    private Integer zan;

    private MQO mqo = new MQO();

    private WxUser wxUser;

    private Integer pid;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public WxUser getWxUser() {
        return wxUser;
    }

    public void setWxUser(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public MQO getMqo() {
        return mqo;
    }

    public void setMqo(MQO mqo) {
        this.mqo = mqo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }
}