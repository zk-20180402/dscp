package com.sinohealth.dscp.api.v1;

import com.sinohealth.dscp.exception.JsonResult;
import com.sinohealth.dscp.exception.RespCode;
import com.sinohealth.dscp.model.Job;
import com.sinohealth.dscp.service.JobServiceV1;
import com.sinohealth.dscp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Administrator
 * @Date: 2018/5/23
 * @Description: 职业控制器
 */
@RestController
@ResponseBody
@RequestMapping("/job/v1")
public class JobController {

    @Autowired
    private JobServiceV1 jobServiceV1;

    /**
     * 新增组织架构信息
     *
     * @param request
     * @param job
     * @return
     */
    @PostMapping("/addJob")
    public JsonResult addRole(HttpServletRequest request, Job job) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(jobServiceV1.addJob(job));
        return jsonResult;
    }

    /**
     * 修改资源信息
     *
     * @param request
     * @param job
     * @return
     */
    @PutMapping("/updateJob")
    public JsonResult updateJob(HttpServletRequest request, Job job) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(jobServiceV1.updateJob(job));
        return jsonResult;
    }

    /**
     * 职位删除
     * @param request
     * @param JobId
     * @return
     */
    @DeleteMapping("/deleteJob/{jobId}")
    public JsonResult deleteJob(HttpServletRequest request, Integer JobId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(jobServiceV1.deleteJob(JobId));
        return jsonResult;
    }

    /**
     * 获取资源信息
     *
     * @param request
     * @param pageUtil 分页属性
     * @param job 条件查询
     * @return
     */
    @GetMapping("/getJobs")
    public JsonResult getResources(HttpServletRequest request, PageUtil pageUtil, Job job) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(jobServiceV1.getJobs(pageUtil, job));
        return jsonResult;
    }
}
