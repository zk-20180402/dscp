package com.sinohealth.dscp.service;

import com.sinohealth.dscp.model.Organization;
import com.sinohealth.dscp.repository.OrganizationRepository;
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
public class OrganizationServiceV1 {

    @Autowired
    private OrganizationRepository organizationRepository;

    /**
     * 获取组织架构
     *
     * @param id
     * @return
     */
    public Organization getOrganizationById(Integer id) {
        return organizationRepository.getOne(id);
    }

    public OrganizationRepository getOrganizationRepository() {
        return this.organizationRepository;
    }

    /**
     * 添加组织架构
     *
     * @param organization
     */
    public Organization addOrganization(Organization organization) {
        return organizationRepository.saveAndFlush(organization);
    }

    /**
     * 更新组织架构
     *
     * @param organization
     * @return
     */
    @Transactional
    public Organization updateOrganization(Organization organization) {
        return organizationRepository.saveAndFlush(organization);
    }

    /**
     * 根据资源编号删除组织架构
     *
     * @param organizationId
     */
    public Integer deleteOrganization(Integer organizationId) {
        try {
            organizationRepository.delete(organizationId);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 获取职位信息
     * 此查询不需要写sql语句，已经封装好
     *
     * @param pageUtil
     * @param organization
     * @return
     */
    public Page<Organization> getOrganizations(PageUtil pageUtil, Organization organization) {
        //如果只是分页没有添加过滤条件，可以使用
        //Page<Resource> resources = resourceRepository.findAll(pageUtil);
        //ExampleMatcher matcher = ExampleMatcher.matching(); //构建对象[使用QueryByExampleExecutor对象中方法时可以使用，模糊匹配]
        //重写方法，添加过滤条件功能
        Page<Organization> organizations = organizationRepository.findAll(new Specification<Organization>() {

            @Override
            public Predicate toPredicate(Root<Organization> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(organization.getOrganizationName())) {
                    list.add(criteriaBuilder.equal(root.get("organizationName").as(String.class), organization.getOrganizationName()));
                }
                if (organization.getOrganizationStatus() > 0) {
                    list.add(criteriaBuilder.equal(root.get("organizationStatus").as(Integer.class), organization.getOrganizationStatus()));
                }
                if (null != organization.getCreateTime()) {
                    list.add(criteriaBuilder.equal(root.get("createTime").as(String.class), organization.getCreateTime()));
                }
                if (StringUtils.isNotBlank(organization.getCreateUser())) {
                    list.add(criteriaBuilder.equal(root.get("createUser").as(String.class), organization.getCreateUser()));
                }
                if (null != organization.getUpdateTime()) {
                    list.add(criteriaBuilder.equal(root.get("updateTime").as(String.class), organization.getUpdateTime()));
                }
                if (StringUtils.isNotBlank(organization.getUpdateUser())) {
                    list.add(criteriaBuilder.equal(root.get("updateUser").as(String.class), organization.getUpdateUser()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageUtil);
        return organizations;
    }
}
