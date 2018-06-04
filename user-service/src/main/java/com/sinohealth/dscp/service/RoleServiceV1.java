package com.sinohealth.dscp.service;

import com.sinohealth.dscp.model.Role;
import com.sinohealth.dscp.repository.RoleRepository;
import com.sinohealth.dscp.repository.UserRoleRepository;
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
public class RoleServiceV1 {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    public Role getRoleById(Integer id) {
        return roleRepository.getOne(id);
    }

    public RoleRepository getRoleRepository() {
        return this.roleRepository;
    }

    /**
     * 添加用户
     *
     * @param role
     */
    public Role addRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @Transactional
    public Role updateRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    public List<Role> getRolesByUserId(Integer userId) {
        List<Integer> roleIds = userRoleRepository.getRoleIdsByUserId(userId);
        List<Role> roles = new ArrayList<Role>();
        for (Integer roldId : roleIds) {
            roles.add(roleRepository.getById(roldId));
        }
        return roles;
    }

    public Long getRoleCount() {
        return getRoleRepository().count();
    }

    /**
     * 根据角色编号删除角色
     *
     * @param roleId
     */
    public Integer deleteRole(Integer roleId) {
        try {
            roleRepository.delete(roleId);
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
     * @param role
     * @return
     */
    public Page<Role> getRoles(PageUtil pageUtil, Role role) {
        //如果只是分页没有添加过滤条件，可以使用
        //Page<Resource> resources = resourceRepository.findAll(pageUtil);
        //ExampleMatcher matcher = ExampleMatcher.matching(); //构建对象[使用QueryByExampleExecutor对象中方法时可以使用，模糊匹配]
        //重写方法，添加过滤条件功能
        Page<Role> roles = roleRepository.findAll(new Specification<Role>() {

            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(role.getRoleName())) {
                    list.add(criteriaBuilder.equal(root.get("roleName").as(String.class), role.getRoleName()));
                }
                if (role.getRoleStatus() > 0) {
                    list.add(criteriaBuilder.equal(root.get("roleStatus").as(Integer.class), role.getRoleStatus()));
                }
                if (null != role.getCreateTime()) {
                    list.add(criteriaBuilder.equal(root.get("createTime").as(String.class), role.getCreateTime()));
                }
                if (StringUtils.isNotBlank(role.getCreateUser())) {
                    list.add(criteriaBuilder.equal(root.get("createUser").as(String.class), role.getCreateUser()));
                }
                if (null != role.getUpdateTime()) {
                    list.add(criteriaBuilder.equal(root.get("updateTime").as(String.class), role.getUpdateTime()));
                }
                if (StringUtils.isNotBlank(role.getUpdateUser())) {
                    list.add(criteriaBuilder.equal(root.get("updateUser").as(String.class), role.getUpdateUser()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageUtil);
        return roles;
    }

}
