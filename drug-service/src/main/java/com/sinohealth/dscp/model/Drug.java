package com.sinohealth.dscp.model;

import java.util.Date;

public class Drug {

    private Integer id;

    private Date createTime;

    private String createUser;

    private String roleName;

    private int roleStatus;

    private Date updateTime;

    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public int getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(int roleStatus) {
        this.roleStatus = roleStatus;
    }

    public enum StatusEnum{
        Status1(1,"正常"),Status2(2,"禁用"),Status3(0,"未知");
        private int code;
        private String descripter;
        StatusEnum(int code,String descripter){
            this.code = code;
            this.descripter = descripter;
        }

        public int getCode() {
            return code;
        }

        public String getDescripter() {
            return descripter;
        }
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleStatus=" + roleStatus +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}