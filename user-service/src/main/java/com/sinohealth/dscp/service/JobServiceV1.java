package com.sinohealth.dscp.service;

import com.sinohealth.dscp.model.Job;
import com.sinohealth.dscp.repository.JobRepository;
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
public class JobServiceV1 {

    @Autowired
    private JobRepository jobRepository;

    /**
     * 获取职位
     *
     * @param id
     * @return
     */
    public Job getJobById(Integer id) {
        return jobRepository.getOne(id);
    }

    public JobRepository getJobRepository() {
        return this.jobRepository;
    }

    /**
     * 添加职位
     *
     * @param job
     */
    public Job addJob(Job job) {
        return jobRepository.saveAndFlush(job);
    }

    /**
     * 更新职位
     *
     * @param job
     * @return
     */
    @Transactional
    public Job updateJob(Job job) {
        return jobRepository.saveAndFlush(job);
    }

    /**
     * 根据职位编号删除职位
     *
     * @param jobId
     */
    public Integer deleteJob(Integer jobId) {
        try {
            jobRepository.delete(jobId);
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
     * @param job
     * @return
     */
    public Page<Job> getJobs(PageUtil pageUtil, Job job) {
        //如果只是分页没有添加过滤条件，可以使用
        //Page<Resource> resources = resourceRepository.findAll(pageUtil);
        //ExampleMatcher matcher = ExampleMatcher.matching(); //构建对象[使用QueryByExampleExecutor对象中方法时可以使用，模糊匹配]
        //重写方法，添加过滤条件功能
        Page<Job> jobs = jobRepository.findAll(new Specification<Job>() {

            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(job.getJobName())) {
                    list.add(criteriaBuilder.equal(root.get("jobName").as(String.class), job.getJobName()));
                }
                if (job.getJobStatus() > 0) {
                    list.add(criteriaBuilder.equal(root.get("jobStatus").as(Integer.class), job.getJobStatus()));
                }
                if (null != job.getCreateTime()) {
                    list.add(criteriaBuilder.equal(root.get("createTime").as(String.class), job.getCreateTime()));
                }
                if (StringUtils.isNotBlank(job.getCreateUser())) {
                    list.add(criteriaBuilder.equal(root.get("createUser").as(String.class), job.getCreateUser()));
                }
                if (null != job.getUpdateTime()) {
                    list.add(criteriaBuilder.equal(root.get("updateTime").as(String.class), job.getUpdateTime()));
                }
                if (StringUtils.isNotBlank(job.getUpdateUser())) {
                    list.add(criteriaBuilder.equal(root.get("updateUser").as(String.class), job.getUpdateUser()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageUtil);
        return jobs;
    }
}
