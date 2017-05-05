package com.homework.controller;

import com.homework.annotation.LoginIgnore;
import com.homework.data.TokenInfo;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.param.UserParam;
import com.homework.response.ResponseMsg;
import com.homework.util.HttpUtil;
import com.homework.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

/**
 * 用户登录controller
 *
 * @author xuke
 * @create 2017-04-11 下午 13:47
 **/
@Api(value="Login-api" ,description="登录相关操作")
@RestController
@RequestMapping(value = "/login",produces="application/json;charset=UTF-8")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${cbp_host}")
    private String host;

    /**
     * 用户登录
     * @param param
     * @return
     * @throws Exception
     */
    @LoginIgnore
    @ApiOperation(value="登录" ,notes="用户登录")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object login(@ApiParam("用户信息") @RequestBody UserParam param) throws Exception {
        logger.info("请求方法login参数---->" + JsonUtil.beanToJson(param));
        ResponseMsg msg = new ResponseMsg();
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("userName", param.getUsername());
        map.put("password", param.getPassword());
        logger.info("cbp请求地址---->" + host+"unlogin/login.cbp" + "参数---->" + JsonUtil.beanToJson(map));
        String json = HttpUtil.send(host+"unlogin/login.cbp",map, HttpUtil.POST);
        logger.info("cbp请求地址---->" + host+"unlogin/login.cbp" + "返回---->" + json);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        JSONObject obj = JSONObject.fromObject(json);
        if(obj.getInt("code") == 0) {   //请求正常
            JSONObject data = obj.getJSONObject("data");
            String token = data.getString("token");
            TokenInfo info = new TokenInfo(token, true, null);
            msg.setData(JsonUtil.beanToJson(info));
            logger.info("请求方法login返回---->" + JsonUtil.beanToJson(info));
            return msg;
        }
        logger.info("请求方法login返回---->" + json);
        return json;
    }

    /**
     * 退出登录
     * @param
     * @return
     */
    @LoginIgnore
    @ApiOperation(value="退出登录" ,notes="用户退出登录")
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Object loginOut(@ApiParam("token") @RequestHeader String token) {
        logger.info("请求方法login参数---->" + token);
        TreeMap<String, Object> param = new TreeMap<>();
        String json = HttpUtil.send(host + "unlogin/loginout.cbp",param, HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        logger.info("请求方法login返回---->" + json);
        return json;
    }
}
