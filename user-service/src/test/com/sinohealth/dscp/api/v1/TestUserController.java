package com.sinohealth.dscp.api.v1;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Auther: Administrator
 * @Date: 2018/5/24
 * @Description: 用户控制器测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TestUserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetUserById() {
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/v1/getUserById/").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //.andExpect(MockMvcResultMatchers.content().string("abc"))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            logger.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail("测试失败！" + e.getLocalizedMessage());
        }
    }

    @Test
    public void testGetUserByNamePasswd() {
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/v1/getUserByNamePasswd/").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //.andExpect(MockMvcResultMatchers.content().string("abc"))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            logger.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail("测试失败！" + e.getLocalizedMessage());
        }
    }

    public int cycleRandom(int initNum) {
        int randomId = new Random().nextInt(initNum) + 1;
        logger.info("随机数为：" + randomId);
        return randomId;
    }

    @Test
    public void testGetUsers() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageNumber",1);
        params.put("pageSize",5);
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/v1/getUsers").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(params)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //.andExpect(MockMvcResultMatchers.content().string("abc"))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andReturn();
            logger.info(result.getResponse().getContentAsString());
        } catch (Exception e) {
            Assert.fail("测试失败！" + e.getLocalizedMessage());
        }
    }
}
