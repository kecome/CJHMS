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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录controller
 *
 * @author xuke
 * @create 2017-04-11 下午 13:47
 **/
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    /**
     * 用户登录
     * @param param
     * @return
     * @throws Exception
     */
    @LoginIgnore
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object login(@RequestBody UserParam param) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", param.getUsername());
        map.put("password", param.getPassword());
        String json = HttpUtil.send("http://10.0.3.69:8090/unlogin/login.cbp",map, HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        System.out.println("json------->" + json);
        JSONObject obj = JSONObject.fromObject(json);
        if(obj.getInt("code") == 0) {   //请求正常
            JSONObject data = obj.getJSONObject("data");
            String token = data.getString("token");
            TokenInfo info = new TokenInfo(token, true, null);
            msg.setData(JsonUtil.beanToJson(info));
            return msg;
        }
        return json;
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @RequestMapping(value = "loginOut", method = RequestMethod.POST)
    public Object loginOut(@RequestHeader String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        String json = HttpUtil.send("http://10.0.3.69:8090/unlogin/loginout.cbp",map, HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        System.out.println(json);
        return json;
    }

}
