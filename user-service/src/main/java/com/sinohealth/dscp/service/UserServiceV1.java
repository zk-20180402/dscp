package com.sinohealth.dscp.service;

import com.sinohealth.dscp.model.User;
import com.sinohealth.dscp.repository.UserRepository;
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
public class UserServiceV1 {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Transactional
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * 查找用户
     *
     * @param name
     * @return
     */
    public User getUserByName(String name) {
        return userRepository.findUser(name);
    }

    /**
     * 根据账号密码获取用户
     *
     * @param loginName
     * @param loginPasswd
     * @return
     */
    public User getUserByNamePasswd(String loginName, String loginPasswd) {
        return userRepository.getUserByNamePasswd(loginName, loginPasswd);
    }

    /**
     * 根据账号获取用户
     *
     * @param loginName
     * @return
     */
    public User getByLoginName(String loginName) {
        return userRepository.getByLoginName(loginName);
    }

    /**
     * 根据用户编号删除用户
     *
     * @param userId
     */
    public Integer deleteUser(Integer userId) {
        try {
            userRepository.delete(userId);
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
     * @param user
     * @return
     */
    public Page<User> getUsers(PageUtil pageUtil, User user) {

        //ExampleMatcher matcher = ExampleMatcher.matching(); //构建对象[使用QueryByExampleExecutor对象中方法时可以使用，模糊匹配]
        //如果只是分页没有添加过滤条件，可以使用
        if (user == null) {
            return userRepository.findAll(pageUtil);
        } else {
            //重写方法，添加过滤条件功能
            Page<User> users = userRepository.findAll(new Specification<User>() {

                @Override
                public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    if (StringUtils.isNotBlank(user.getName())) {
                        list.add(criteriaBuilder.equal(root.get("name").as(String.class), user.getName()));
                    }
                    if (StringUtils.isNotBlank(user.getLoginName())) {
                        list.add(criteriaBuilder.equal(root.get("loginName").as(String.class), user.getLoginName()));
                    }
                    if (user.getSex() > 0) {
                        list.add(criteriaBuilder.equal(root.get("sex").as(String.class), user.getSex()));
                    }
                    if (StringUtils.isNotBlank(user.getTelephone())) {
                        list.add(criteriaBuilder.equal(root.get("telephone").as(String.class), user.getTelephone()));
                    }
                    if (user.getMobilePhone() > 0) {
                        list.add(criteriaBuilder.equal(root.get("mobilePhone").as(String.class), user.getMobilePhone()));
                    }
                    if (StringUtils.isNotBlank(user.getEmail())) {
                        list.add(criteriaBuilder.equal(root.get("email").as(String.class), user.getEmail()));
                    }
                    if (StringUtils.isNotBlank(user.getAddress())) {
                        list.add(criteriaBuilder.equal(root.get("address").as(String.class), user.getAddress()));
                    }
                    if (user.getUserStatus() > 0) {
                        list.add(criteriaBuilder.equal(root.get("userStatus").as(Integer.class), user.getUserStatus()));
                    }
                    if (null != user.getCreateTime()) {
                        list.add(criteriaBuilder.equal(root.get("createTime").as(String.class), user.getCreateTime()));
                    }
                    if (StringUtils.isNotBlank(user.getCreateUser())) {
                        list.add(criteriaBuilder.equal(root.get("createUser").as(String.class), user.getCreateUser()));
                    }
                    if (null != user.getUpdateTime()) {
                        list.add(criteriaBuilder.equal(root.get("updateTime").as(String.class), user.getUpdateTime()));
                    }
                    if (StringUtils.isNotBlank(user.getUpdateUser())) {
                        list.add(criteriaBuilder.equal(root.get("updateUser").as(String.class), user.getUpdateUser()));
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(p));
                }
            }, pageUtil);
            return users;
        }
    }
}
