package com.ops.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class WxUserFollow {

    private Integer id;
    @NotEmpty
    private String userid;
    @NotEmpty
    private String followid;

    private Date createtime;

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

    public String getFollowid() {
        return followid;
    }

    public void setFollowid(String followid) {
        this.followid = followid == null ? null : followid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}