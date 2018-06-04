package com.sinohealth.dscp.api.v1;

import com.sinohealth.dscp.exception.JsonResult;
import com.sinohealth.dscp.exception.RespCode;
import com.sinohealth.dscp.model.Resource;
import com.sinohealth.dscp.service.ResourceServiceV1;
import com.sinohealth.dscp.util.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/5/23
 * @Description: 资源控制器
 */
@RestController
@ResponseBody
@RequestMapping("/resource/v1")
public class ResourceController {

    @Autowired
    private ResourceServiceV1 resourceServiceV1;

    /**
     * 新增资源信息
     *
     * @param request
     * @param resource
     * @return
     */
    @PostMapping("/addResource")
    public JsonResult addResource(HttpServletRequest request, Resource resource) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(resourceServiceV1.addResource(resource));
        return jsonResult;
    }

    /**
     * 修改资源信息
     *
     * @param request
     * @param resource
     * @return
     */
    @PutMapping("/updateResource")
    public JsonResult updateResource(HttpServletRequest request, Resource resource) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(resourceServiceV1.updateResource(resource));
        return jsonResult;
    }

    /**
     * 删除资源
     * @param request
     * @param resourceId
     * @return
     */
    @DeleteMapping("/deleteResource/{resourceId}")
    public JsonResult deleteResource(HttpServletRequest request, Integer resourceId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(resourceServiceV1.deleteResource(resourceId));
        return jsonResult;
    }

    /**
     * 根据角色编号字符串id获取角色信息
     *
     * @param request
     * @param roleIds
     * @return
     */
    @GetMapping("/getResourcesByRoleIds/{roleIds}")
    public JsonResult getResourcesByRoles(HttpServletRequest request, @PathVariable String roleIds) {

        List<Resource> resources = null;
        //获取资源信息
        if (StringUtils.isNotBlank(roleIds)) {
            if (roleIds.indexOf(",") > 0) {
                resources = resourceServiceV1.getResourcesByIds(roleIds.split(","));
            } else {
                resources = resourceServiceV1.getResourcesByIds(roleIds);
            }
        }
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(resources);
        return jsonResult;
    }

    /**
     * 通过用户id获取资源
     *
     * @param request
     * @param userId
     * @return
     */
    @GetMapping("/getResourcesByUserId/{userId}")
    public JsonResult getResourcesByUserId(HttpServletRequest request, @PathVariable Integer userId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(resourceServiceV1.getResourcesByUserId(userId));
        return jsonResult;
    }

    /**
     * 获取资源信息
     *
     * @param request
     * @param pageUtil 分页属性
     * @param resource 条件查询
     * @return
     */
    @GetMapping("/getResources")
    public JsonResult getResources(HttpServletRequest request, PageUtil pageUtil, Resource resource) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(resourceServiceV1.getResources(pageUtil, resource));
        return jsonResult;
    }
}
