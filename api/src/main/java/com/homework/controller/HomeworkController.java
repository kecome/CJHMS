package com.homework.controller;

import com.homework.data.HomeworkQuestiion;
import com.homework.data.HomeworkStudent;
import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.model.Homework;
import com.homework.param.HomeworkParam;
import com.homework.param.HomeworkQuestiionParam;
import com.homework.param.StudentanswerParam;
import com.homework.response.ResponseMsg;
import com.homework.service.HomeworkService;
import com.homework.util.HttpUtil;
import com.homework.util.JsonUtil;
import com.homework.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 作业controller
 *
 * @author xuke
 * @create 2017-03-30 下午 19:22
 **/


@Api(value = "Homework-api", description = "作业和答题相关操作")

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
    @ApiOperation(value = "布置作业", notes = "根据作业，题目数据模型布置作业")
    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postHomework(@ApiParam(value = "作业，题目数据模型", required = true)  @RequestBody @Valid HomeworkQuestiionParam hq) throws Exception {
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
    public ResponseMsg<Page<Homework>> getHomeworkList(@ApiParam("作业查询参数") @RequestBody HomeworkParam param) throws Exception{
        logger.info("请求方法getHomeworkList参数---->" + JsonUtil.beanToJson(param));
        if(param.getTeacherId() == null) {
            param.setTeacherId(UserUtil.getUser().getId()); //查询当前老师发布的作业
        }
        Page<Homework> page = homeworkService.selectHomeworkList(param);
        ResponseMsg<Page<Homework>> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getHomeworkList返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 根据id获取作业包括题目
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据id获取作业", httpMethod = "GET" ,notes = "携带作业id", authorizations = @Authorization(value = "token"))
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseMsg<HomeworkQuestiion> getHomework(@ApiParam(value = "作业id", required = true) @RequestParam Long id) throws Exception{
        logger.info("请求方法getHomework参数---->" + id);
        if(id == null || id < 0) {
            throw new BusinessException(ErrorInfo.ID_IS_NULL.code, ErrorInfo.ID_IS_NULL.desc);
        }
        HomeworkQuestiion hq = homeworkService.getHomework(id);
        String data = JsonUtil.beanToJson(hq);
        ResponseMsg<HomeworkQuestiion> msg = new ResponseMsg<>();
        msg.setData(hq);
        logger.info("请求方法getHomework返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @ApiOperation(value = "获取学生答题信息", notes = "返回学生答题信息", response = ResponseMsg.class)
    @RequestMapping(value="student", method = RequestMethod.POST)
    public ResponseMsg<HomeworkStudent> getHomeworkStudent(@ApiParam(value = "学生答题参数" ,required=true) @Validated @RequestBody StudentanswerParam param) throws Exception{
        logger.info("请求方法getHomeworkStudent参数---->" + JsonUtil.beanToJson(param));
        if(UserUtil.getUser().getRole().equals(UserUtil.STUDENT)) {
            param.setStudentId(UserUtil.getUser().getId());
        }
        HomeworkStudent hs = homeworkService.getHomeworkStudent(param);
        ResponseMsg<HomeworkStudent> msg = new ResponseMsg<>();
        msg.setData(hs);
        logger.info("请求方法getHomeworkStudent返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }
    /**
     * 查询学生
     * @param ids  班级id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询学生", notes = "根据学生ids")
    @RequestMapping(value="/stud", method = RequestMethod.POST)
    public Object getStudents(@ApiParam("学生id列表") @RequestBody List<Long> ids) throws Exception{
        TreeMap<String, Object> param = new TreeMap();
       // param.put("teacherIds[]", "323,434,545");
        param.put("classIds[]", StringUtils.join(ids,","));
        String json = HttpUtil.send(host+"student/queryStudent.cbp",param,HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }
        return json;
    }

    /**
     * 根据班级获取学生
     * @param map
     * @return
     * @throws Exception
     */
    @ApiIgnore
    @RequestMapping(value="/cs", method = RequestMethod.POST)
    public Object getClassStudent(@ApiParam("班级信息") @RequestBody Map<String, Long> map) throws Exception{
        TreeMap<String, Object> param = new TreeMap();
        param.put("classIds", map.get("classId"));
        String json = HttpUtil.send(host+"class/queryClassStudent.cbp",param,HttpUtil.POST);
        System.out.println(json);
        return json;
    }

//    public static void main(String[] args) throws  Exception {
//
//       System.out.println(buildHomeworkQuestion());
//
//    }


//    private static String buildHomeworkQuestion() throws Exception{
//        HomeworkQuestiion hq = new HomeworkQuestiion();
//        //作业信息
//        Homework homework = new Homework();
//        homework.setTeacherId(65545L);
//        homework.setSubjectId(46565L);
//        homework.setTitle("12-23 数学作业");
//        homework.setKnowledge("数学知识点");
//        homework.setSpentTime(120);
//        homework.setEndTime(new Date());
//        homework.setPublicTime(new Date());
//
//        //班级
//        List<Long> classIds = new ArrayList<>();
//
//        classIds.add(345L);
//        classIds.add(456L);
//        classIds.add(345L);
//
//        List<Question> questions = new ArrayList<>();
//        Question q1 = new Question();
//        q1.setSeq(1);
//        q1.setType(0);
//        q1.setTitle("1+1=?");
//        q1.setAnswer("A");
//        q1.setItem("ABCD");
//        questions.add(q1);
//
//        Question q2 = new Question();
//        q2.setSeq(2);
//        q1.setType(0);
//        q1.setTitle("1+2=?");
//        q1.setAnswer("B");
//        q1.setItem("ABCDEF");
//        questions.add(q2);
//
//        hq.setClassIds(classIds);
//        hq.setHomework(homework);
//        hq.setQuestions(questions);
//
//        return JsonUtil.beanToJson(hq);
//    }

}
