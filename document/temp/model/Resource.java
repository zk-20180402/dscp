package com.sinohealth.dscp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.Entity;

/**
 * @Auther: Administrator
 * @Date: 2018/5/19 16:42
 * @Description: 资源
 */
@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Resource {

    private Integer id;
    private String resourceName;
    private String resourceUrl;
    private Integer parentId;
    private Integer resourceType;
    private String logoUrl;
    private Integer resourceStatus;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(Integer resourceStatus) {
        this.resourceStatus = resourceStatus;
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

    public Resource() {
    }

    public Resource(Integer id, String resourceName, String resourceUrl, Integer parentId, Integer resourceType,
            String logoUrl, Integer resourceStatus, Date createTime, String createUser, Date updateTime,
            String updateUser) {
        this.id = id;
        this.resourceName = resourceName;
        this.resourceUrl = resourceUrl;
        this.parentId = parentId;
        this.resourceType = resourceType;
        this.logoUrl = logoUrl;
        this.resourceStatus = resourceStatus;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resourceName='" + resourceName + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", parentId=" + parentId +
                ", resourceType=" + resourceType +
                ", logoUrl='" + logoUrl + '\'' +
                ", resourceStatus=" + resourceStatus +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
