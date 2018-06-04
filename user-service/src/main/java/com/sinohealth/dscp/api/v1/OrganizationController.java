package com.sinohealth.dscp.api.v1;

import com.sinohealth.dscp.exception.JsonResult;
import com.sinohealth.dscp.exception.RespCode;
import com.sinohealth.dscp.model.Organization;
import com.sinohealth.dscp.model.Resource;
import com.sinohealth.dscp.service.OrganizationServiceV1;
import com.sinohealth.dscp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Administrator
 * @Date: 2018/5/23
 * @Description: 组织架构控制器
 */
@RestController
@ResponseBody
@RequestMapping("/organization/v1")
public class OrganizationController {

    @Autowired
    private OrganizationServiceV1 organizationServiceV1;

    /**
     * 新增组织架构信息
     *
     * @param request
     * @param organization
     * @return
     */
    @PostMapping("/addOrganization")
    public JsonResult addRole(HttpServletRequest request, Organization organization) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(organizationServiceV1.addOrganization(organization));
        return jsonResult;
    }

    /**
     * 修改组织架构信息
     *
     * @param request
     * @param organization
     * @returno
     */
    @PutMapping("/updateOrientation")
    public JsonResult updateResource(HttpServletRequest request, Organization organization) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(organizationServiceV1.updateOrganization(organization));
        return jsonResult;
    }

    /**
     * 删除组织架构
     * @param request
     * @param organizationId
     * @return
     */
    @DeleteMapping("/deleteOrganization/{organizationId}")
    public JsonResult deleteResource(HttpServletRequest request, Integer organizationId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(organizationServiceV1.deleteOrganization(organizationId));
        return jsonResult;
    }

    /**
     * 获取组织架构信息
     *
     * @param request
     * @param pageUtil 分页属性
     * @param organization 条件查询
     * @return
     */
    @GetMapping("/getOrganizations")
    public JsonResult getResources(HttpServletRequest request, PageUtil pageUtil, Organization organization) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(organizationServiceV1.getOrganizations(pageUtil, organization));
        return jsonResult;
    }

}
