package com.ops.entity;

import java.util.List;

public class ShiroPerms {

    private Integer id;

    private String url;

    private String perms;

    private Integer isDelete;

    private String des;

    private String des2;

    private List<ShiroPerms> perm;


    public List<ShiroPerms> getPerm() {
        return perm;
    }

    public void setPerm(List<ShiroPerms> perm) {
        this.perm = perm;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    public ShiroPerms(String url, String perms, String des, String des2) {
        super();
        this.url = url;
        this.perms = perms;
        this.des = des;
        this.des2 = des2;
    }

    public ShiroPerms() {
        super();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }
}