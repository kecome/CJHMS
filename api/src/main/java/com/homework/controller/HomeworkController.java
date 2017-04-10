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
import com.homework.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * 作业controller
 *
 * @author xuke
 * @create 2017-03-30 下午 19:22
 **/
@Api("HomeworkController控制器")
@RestController
@RequestMapping(value = "/homework")
public class HomeworkController {
    Logger logger = Logger.getLogger(HomeworkController.class.getName());
    @Autowired
    private HomeworkService homeworkService;

    @ApiOperation(value = "获取作业列表",notes = "直接请求")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getHomeworkList(@RequestBody HomeworkParam param) throws Exception{
        Page<Homework> page = homeworkService.selectHomeworkList(param);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(JsonUtil.beanToJson(page));
        return msg;
    }

    /**
     * 根据id获取作业
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id获取作业", httpMethod = "GET" ,notes = "携带作业id")
    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getHomework(Long id) throws Exception{
        if(id == null || id < 0) {
            throw new BusinessException(ErrorInfo.ID_IS_NULL.code, ErrorInfo.ID_IS_NULL.desc);
        }
        Homework hk = homeworkService.selectHomework(id);
        String data = JsonUtil.beanToJson(hk);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(data);
        return msg;
    }

    /**
     * 发布作业
     * @param hq
     * @return
     * @throws Exception
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postHomework(@Validated @RequestBody HomeworkQuestiion hq) throws Exception {
        logger.info("方法postHomework()---->" + JsonUtil.beanToJson(hq));
        homeworkService.postHomework(hq);
        return new ResponseMsg();
    }

//    public static void main(String[] args) throws  Exception {
//        System.out.println(buildHomeworkClass());
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
        List<Long> classIds = new ArrayList<>();
        classIds.add(666L);
        classIds.add(444L);

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
