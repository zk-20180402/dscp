package com.sinohealth.dscp.api.v1;

import com.sinohealth.dscp.exception.JsonResult;
import com.sinohealth.dscp.exception.RespCode;
import com.sinohealth.dscp.model.Role;
import com.sinohealth.dscp.model.User;
import com.sinohealth.dscp.service.RoleServiceV1;
import com.sinohealth.dscp.service.UserServiceV1;
import com.sinohealth.dscp.util.EncryptUtil;
import com.sinohealth.dscp.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/5/23
 * @Description: 角色控制器
 */
@RestController
@ResponseBody
@RequestMapping("/role/v1")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    public RoleController() {
    }

    private UserServiceV1 userServiceV1;
    private RoleServiceV1 roleServiceV1;

    @Autowired
    public RoleController(UserServiceV1 userServiceV1, RoleServiceV1 roleServiceV1) {
        this.userServiceV1 = userServiceV1;
        this.roleServiceV1 = roleServiceV1;
    }

    /**
     * 获取角色根据角色id
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/getRoleById/{id}")
    public JsonResult getUserById(HttpServletRequest request, @PathVariable Integer id) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        //jsonResult.setResultData(userServiceV1.getUserById(id));
        jsonResult.setResultData(roleServiceV1.getRoleRepository().getById(id));
        return jsonResult;
    }

    /**
     * 根据用户账号密码获取相应角色
     *
     * @param request
     * @param loginName
     * @param loginPasswd
     * @return
     */
    @GetMapping("/getRolesByNamePasswd/{loginName}/{loginPasswd}")
    public JsonResult getUserByNamePasswd(HttpServletRequest request, @PathVariable String loginName, @PathVariable String loginPasswd) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        User user = userServiceV1.getUserRepository().getByLoginNameAndLoginPasswd(loginName, EncryptUtil.md5Encode(loginPasswd));
        //获取角色信息
        List<Role> roles = roleServiceV1.getRolesByUserId(user.getId());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(roles);
        return jsonResult;
    }

    /**
     * 获取角色根据用户id
     *
     * @param request
     * @param userId
     * @return
     */
    @GetMapping("/getRoleByUserId/{userId}")
    public JsonResult getRoleByUserId(HttpServletRequest request, @PathVariable Integer userId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(roleServiceV1.getRolesByUserId(userId));
        return jsonResult;
    }

    /**
     * 新增角色信息
     *
     * @param request
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public JsonResult addRole(HttpServletRequest request, Role role) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(roleServiceV1.addRole(role));
        return jsonResult;
    }

    /**
     * 修改角色信息
     *
     * @param request
     * @param role
     * @return
     */
    @PutMapping("/updateResource")
    public JsonResult updateResource(HttpServletRequest request, Role role) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(roleServiceV1.updateRole(role));
        return jsonResult;
    }

    /**
     * 删除角色
     * @param request
     * @param roleId
     * @return
     */
    @DeleteMapping("/deleteRole/{roleId}")
    public JsonResult deleteResource(HttpServletRequest request, Integer roleId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(roleServiceV1.deleteRole(roleId));
        return jsonResult;
    }

    /**
     * 获取角色信息
     *
     * @param request
     * @param pageUtil 分页属性
     * @param role 条件查询
     * @return
     */
    @GetMapping("/getRoles")
    public JsonResult getRoles(HttpServletRequest request, PageUtil pageUtil, Role role) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(roleServiceV1.getRoles(pageUtil, role));
        return jsonResult;
    }

}
