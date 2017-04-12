package com.homework.controller;

import com.homework.annotation.LoginIgnore;
import com.homework.data.TokenInfo;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.param.UserParam;
import com.homework.response.ResponseMsg;
import com.homework.util.HttpUtil;
import com.homework.util.JsonUtil;
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
@RestController
@RequestMapping(value = "/login")
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
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object login(@RequestBody UserParam param) throws Exception {
        logger.info("请求方法login参数---->" + JsonUtil.beanToJson(param));
        ResponseMsg msg = new ResponseMsg();
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("userName", param.getUsername());
        map.put("password", param.getPassword());
        logger.info("cbp请求地址---->" + host+"unlogin/login.cbp" + "参数---->" + JsonUtil.beanToJson(map));
        String json = HttpUtil.send(host+"unlogin/login.cbp",map,null, HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        JSONObject obj = JSONObject.fromObject(json);
        if(obj.getInt("code") == 0) {   //请求正常
            JSONObject data = obj.getJSONObject("data");
            String token = data.getString("token");
            TokenInfo info = new TokenInfo(token, true, null);
            msg.setData(JsonUtil.beanToJson(info));
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
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public Object loginOut(@RequestHeader String token) {
        logger.info("请求方法login参数---->" + token);
        TreeMap<String, Object> header = new TreeMap<>();
        header.put("token", token);
        String json = HttpUtil.send(host + "unlogin/loginout.cbp",null,header, HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        logger.info("请求方法login返回---->" + json);
        return json;
    }
}
