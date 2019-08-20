package com.ops.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class ShiroUser {

    private String id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String ascriptionid;
    @NotEmpty
    private String roleId;

    private Integer isDelete;


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAscriptionid() {
        return ascriptionid;
    }

    public void setAscriptionid(String ascriptionid) {
        this.ascriptionid = ascriptionid == null ? null : ascriptionid.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


}