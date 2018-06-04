package com.sinohealth.dscp.service;

import com.sinohealth.dscp.model.Resource;
import com.sinohealth.dscp.model.Role;
import com.sinohealth.dscp.model.RoleResource;
import com.sinohealth.dscp.repository.ResourceRepository;
import com.sinohealth.dscp.repository.RoleResourceRepository;
import com.sinohealth.dscp.util.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceV1 {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleResourceRepository roleResourceRepository;

    /**
     * 获取资源
     *
     * @param id
     * @return
     */
    public Resource getResourceById(Integer id) {
        return resourceRepository.getOne(id);
    }

    public ResourceRepository getResourceRepository() {
        return this.resourceRepository;
    }

    /**
     * 添加资源
     *
     * @param resource
     */
    public Resource addResource(Resource resource) {
        return resourceRepository.saveAndFlush(resource);
    }

    /**
     * 更新资源
     *
     * @param resource
     * @return
     */
    @Transactional
    public Resource updateResource(Resource resource) {
        return resourceRepository.saveAndFlush(resource);
    }

    /**
     * 获取资源信息
     *
     * @param roles
     * @return
     */
    public List<Resource> getResourcesByRoles(List<Role> roles) {
        List<RoleResource> roleResources = new ArrayList<RoleResource>();
        for (Role role : roles) {
            roleResources = roleResourceRepository.getByRoleId(role.getId());
            if (roleResources != null && roleResources.size() > 0) {
                break;
            }
        }
        List<Resource> resources = new ArrayList<Resource>();
        for (RoleResource roleResource : roleResources) {
            Resource resource = resourceRepository.getById(roleResource.getResourceId());
            if (resource != null) {
                resources.add(resource);
            }
        }
        return resources;
    }

    /**
     * 通过roleid获取资源信息
     *
     * @param roleIds
     * @return
     */
    public List<Resource> getResourcesByIds(String... roleIds) {
        List<RoleResource> roleResources = new ArrayList<RoleResource>();
        for (String roleId : roleIds) {
            roleResources = roleResourceRepository.getByRoleId(Integer.valueOf(roleId));
            if (roleResources != null && roleResources.size() > 0) {
                break;
            }
        }
        List<Resource> resources = new ArrayList<Resource>();
        for (RoleResource roleResource : roleResources) {
            Resource resource = resourceRepository.getById(roleResource.getResourceId());
            if (resource != null) {
                resources.add(resource);
            }
        }
        return resources;
    }

    /**
     * 获取资源数量
     *
     * @return
     */
    public Long getResourceCount() {
        return this.getResourceRepository().count();
    }

    /**
     * 通过用户编号获取资源
     *
     * @param userId
     * @return
     */
    public List<Resource> getResourcesByUserId(Integer userId) {
        return resourceRepository.getResourcesByUserId(userId);
    }

    /**
     * 根据资源编号删除资源
     *
     * @param resourceId
     */
    public Integer deleteResource(Integer resourceId) {
        try {
            resourceRepository.delete(resourceId);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 获取资源信息
     * 此查询不需要写sql语句，已经封装好
     *
     * @param pageUtil
     * @param resource
     * @return
     */
    public Page<Resource> getResources(PageUtil pageUtil, Resource resource) {
        //如果只是分页没有添加过滤条件，可以使用
        //Page<Resource> resources = resourceRepository.findAll(pageUtil);
        //ExampleMatcher matcher = ExampleMatcher.matching(); //构建对象[使用QueryByExampleExecutor对象中方法时可以使用，模糊匹配]
        //重写方法，添加过滤条件功能
        Page<Resource> resources = resourceRepository.findAll(new Specification<Resource>() {

            @Override
            public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(resource.getResourceName())) {
                    list.add(criteriaBuilder.equal(root.get("resourceName").as(String.class), resource.getResourceName()));
                }
                if (StringUtils.isNotBlank(resource.getResourceUrl())) {
                    list.add(criteriaBuilder.equal(root.get("resourceUrl").as(String.class), resource.getResourceUrl()));
                }
                if (resource.getParentId() > 0) {
                    list.add(criteriaBuilder.equal(root.get("parentId").as(Integer.class), resource.getParentId()));
                }
                if (resource.getResourceType() > 0) {
                    list.add(criteriaBuilder.equal(root.get("resourceType").as(Integer.class), resource.getResourceType()));
                }
                if (StringUtils.isNotBlank(resource.getLogoUrl())) {
                    list.add(criteriaBuilder.equal(root.get("logoUrl").as(String.class), resource.getLogoUrl()));
                }
                if (resource.getResourceStatus() > 0) {
                    list.add(criteriaBuilder.equal(root.get("resourceStatus").as(Integer.class), resource.getResourceStatus()));
                }
                if (null != resource.getCreateTime()) {
                    list.add(criteriaBuilder.equal(root.get("createTime").as(String.class), resource.getCreateTime()));
                }
                if (StringUtils.isNotBlank(resource.getCreateUser())) {
                    list.add(criteriaBuilder.equal(root.get("createUser").as(String.class), resource.getCreateUser()));
                }
                if (null != resource.getUpdateTime()) {
                    list.add(criteriaBuilder.equal(root.get("updateTime").as(String.class), resource.getUpdateTime()));
                }
                if (StringUtils.isNotBlank(resource.getUpdateUser())) {
                    list.add(criteriaBuilder.equal(root.get("updateUser").as(String.class), resource.getUpdateUser()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageUtil);
        return resources;
    }
}
