package com.sinohealth.dscp.api.v1;

import com.sinohealth.dscp.exception.JsonResult;
import com.sinohealth.dscp.exception.RespCode;
import com.sinohealth.dscp.model.Resource;
import com.sinohealth.dscp.model.User;
import com.sinohealth.dscp.service.UserServiceV1;
import com.sinohealth.dscp.util.EncryptUtil;
import com.sinohealth.dscp.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Administrator
 * @Date: 2018/5/23
 * @Description: 用户控制器
 */
@RestController
@ResponseBody
@RequestMapping("/user/v1")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController() {
    }

    private UserServiceV1 userServiceV1;

    @Autowired
    public UserController(UserServiceV1 userServiceV1) {
        this.userServiceV1 = userServiceV1;
    }

    /**
     * 获取用户
     *
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    public JsonResult getUserById(HttpServletRequest request, @PathVariable Integer id) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        //jsonResult.setResultData(userServiceV1.getUserById(id));
        jsonResult.setResultData(userServiceV1.getUserRepository().getById(id));
        return jsonResult;
    }

    /**
     * 根据账号密码获取用户
     *
     * @param request
     * @param loginName
     * @param loginPasswd
     * @return
     */
    @GetMapping("/getUserByNamePasswd/{loginName}/{loginPasswd}")
    public JsonResult getUserByNamePasswd(HttpServletRequest request, @PathVariable String loginName, @PathVariable String loginPasswd) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        User user = userServiceV1.getUserRepository().getByLoginNameAndLoginPasswd(loginName, EncryptUtil.md5Encode(loginPasswd));
        jsonResult.setRespCode(RespCode.SUCCESS);
        if (user == null) {
            jsonResult.setRespCode(RespCode.LOGIN_FAIL);
        }

        return jsonResult;
    }

    /**
     * 新增用户信息
     *
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public JsonResult addUser(HttpServletRequest request, User user) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(userServiceV1.addUser(user));
        return jsonResult;
    }

    /**
     * 修改用户信息
     *
     * @param request
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public JsonResult updateUser(HttpServletRequest request, User user) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(userServiceV1.updateUser(user));
        return jsonResult;
    }

    /**
     * 删除用户
     * @param request
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteUser/{userId}")
    public JsonResult deleteUser(HttpServletRequest request, Integer userId) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(userServiceV1.deleteUser(userId));
        return jsonResult;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @param pageUtil 分页属性
     * @param user 条件查询
     * @return
     */
    @GetMapping("/getUsers")
    public JsonResult getUsers(HttpServletRequest request, PageUtil pageUtil, User user) {
        JsonResult jsonResult = new JsonResult(request.getRequestURI());
        jsonResult.setRespCode(RespCode.SUCCESS);
        jsonResult.setResultData(userServiceV1.getUsers(pageUtil, user));
        return jsonResult;
    }

}
