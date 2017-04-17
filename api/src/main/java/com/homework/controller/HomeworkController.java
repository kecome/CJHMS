package com.homework.controller;

import com.homework.data.HomeworkQuestiion;
import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.model.Homework;
import com.homework.model.HomeworkClass;
import com.homework.model.Question;
import com.homework.param.HomeworkParam;
import com.homework.response.ResponseMsg;
import com.homework.service.HomeworkService;
import com.homework.util.HttpUtil;
import com.homework.util.JsonUtil;
import com.homework.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 作业controller
 *
 * @author xuke
 * @create 2017-03-30 下午 19:22
 **/
@Api("HomeworkController控制器")
@RestController
@RequestMapping(value = "/homework",produces="application/json;charset=UTF-8")
public class HomeworkController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HomeworkService homeworkService;
    @Value("${cbp_host}")
    private String host;

    /**
     * 布置作业
     * @param hq
     * @return
     * @throws Exception
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postHomework(@Validated @RequestBody HomeworkQuestiion hq) throws Exception {
        if(hq.getClassIds() == null || hq.getClassIds().size() == 0 ) {
            throw new BusinessException(ErrorInfo.ClASSID_IS_NULL.code, ErrorInfo.ClASSID_IS_NULL.desc);
        }
        logger.info("请求方法postHomework参数---->" + JsonUtil.beanToJson(hq));
        homeworkService.postHomework(hq);
        ResponseMsg msg = new ResponseMsg();
        logger.info("请求方法postHomework返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 作业列表
     * @param param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取作业列表",notes = "直接请求")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getHomeworkList(@RequestBody HomeworkParam param) throws Exception{
        logger.info("请求方法getHomeworkList参数---->" + JsonUtil.beanToJson(param));
        if(param.getTeacherId() == null) {
            param.setTeacherId(UserUtil.getUser().getId()); //查询当前老师发布的作业
        }
        Page<Homework> page = homeworkService.selectHomeworkList(param);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(JsonUtil.beanToJson(page));
        logger.info("请求方法getHomeworkList返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 根据id获取作业包括题目
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id获取作业", httpMethod = "GET" ,notes = "携带作业id")
    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getHomework(Long id) throws Exception{
        logger.info("请求方法getHomework参数---->" + id);
        if(id == null || id < 0) {
            throw new BusinessException(ErrorInfo.ID_IS_NULL.code, ErrorInfo.ID_IS_NULL.desc);
        }
        HomeworkQuestiion hq = homeworkService.getHomework(id);
        String data = JsonUtil.beanToJson(hq);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(data);
        logger.info("请求方法getHomework返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 查询学生
     * @param ids  班级id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/students", method = RequestMethod.POST)
    public Object getStudents(@RequestBody List<Long> ids) throws Exception{
        TreeMap<String, Object> param = new TreeMap();
       // param.put("teacherIds[]", "323,434,545");
        param.put("classIds[]", StringUtils.join(ids,","));
        String json = HttpUtil.send(host+"student/queryStudent.cbp",param,HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        return json;
    }

//    public static void main(String[] args) throws  Exception {
//        List<Long> ids = new ArrayList<>();
//        ids.add(23432L);
//        ids.add(4343L);
//       System.out.println(JsonUtil.beanToJson(ids));
//        System.out.println(buildHomeworkQuestion());
//    }

    private static String buildHomeworkClass() throws Exception {
        HomeworkClass hc = new HomeworkClass();
        hc.setClassId(43434L);
        hc.setHomeworkId(555L);
        return JsonUtil.beanToJson(hc);
    }

    private static String buildHomeworkQuestion() throws Exception{
        HomeworkQuestiion hq = new HomeworkQuestiion();
        //作业信息
        Homework homework = new Homework();
        homework.setTeacherId(65545L);
        homework.setSubjectId(46565L);
        homework.setTitle("12-23 数学作业");
        homework.setKnowledge("数学知识点");
        homework.setSpentTime(120);
        homework.setEndTime(new Date());
        homework.setPublicTime(new Date());

        //班级
        List<Map<Long, List<Long>>> classIds = new ArrayList<>();

        Map<Long, List<Long>> map1 = new HashMap<>();
        List<Long> sids1 = new ArrayList<>();
        sids1.add(666L);
        map1.put(1111L, sids1);
        classIds.add(map1);

        Map<Long, List<Long>> map2 = new HashMap<>();
        List<Long> sids2 = new ArrayList<>();
        sids2.add(556L);
        sids2.add(555L);
        sids2.add(444L);
        map2.put(2222L, sids2);
        classIds.add(map2);

        List<Question> questions = new ArrayList<>();
        Question q1 = new Question();
        q1.setSeq(1);
        q1.setType(0);
        q1.setTitle("1+1=?");
        q1.setAnswer("A");
        q1.setItem("ABCD");
        questions.add(q1);

        Question q2 = new Question();
        q2.setSeq(2);
        q1.setType(0);
        q1.setTitle("1+2=?");
        q1.setAnswer("B");
        q1.setItem("ABCDEF");
        questions.add(q2);

        hq.setClassIds(classIds);
        hq.setHomework(homework);
        hq.setQuestions(questions);

        return JsonUtil.beanToJson(hq);
    }

}
