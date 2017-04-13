package com.homework.service;

import com.homework.data.HomeworkQuestiion;
import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.mapper.HomeworkClassMapper;
import com.homework.mapper.HomeworkMapper;
import com.homework.mapper.QuestionMapper;
import com.homework.mapper.StudentworkMapper;
import com.homework.model.Homework;
import com.homework.model.HomeworkClass;
import com.homework.model.Studentwork;
import com.homework.param.HomeworkParam;
import com.homework.util.HttpUtil;
import com.homework.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.TreeMap;

/**
 * 作业service
 *
 * @author
 * @create 2017-03-30 下午 19:21
 **/
@Transactional
@Service
public class HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private HomeworkClassMapper homeworkClassMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentworkMapper studentworkMapper;

    @Value("${cbp_host}")
    private String host;

    /**
     * 发布作业
     * @param hq
     */
    public void postHomework(HomeworkQuestiion hq) {
        TreeMap<String, Object> param = new TreeMap();
        param.put("token-header", UserUtil.getUser().getToken());
        param.put("teacherIds", new Long[] {UserUtil.getUser().getId()});
        param.put("classIds", hq.getClassIds().toArray());
        String json = HttpUtil.send(host+"class/queryClass.cbp",param, HttpUtil.POST);
        if(StringUtils.isEmpty(json)) {
            throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
        }

        //插入作业记录
        Homework homework = hq.getHomework();
      //  homeworkMapper.insertHomework(homework);
        //插入作业班级关联记录
        HomeworkClass hc = null;
        for(Long classId : hq.getClassIds()) {
            hc = new HomeworkClass();
            hc.setClassId(classId);
            hc.setHomeworkId(homework.getId());
          //  homeworkClassMapper.insertHomeworkClass(hc);

            //插入作业学生关联记录
            Long[] studentIds = {222L, 333L, 444L};
            Studentwork sw = null;
            for(Long studentId : studentIds) {
                sw = new Studentwork();
                sw.setHomeworkId(homework.getId());
                sw.setStudentId(studentId);
                sw.setClassId(classId);
                sw.setSubmit(0);
                sw.setMark(0);
               // studentworkMapper.insertStudentwork(sw);
            }
        }
    }

    /**
     * 定时任务调用  将作业改为发布状态
     * @return
     */
    public int updateStatus() {
        return homeworkMapper.updateStatus();
    }

    public Homework selectHomework(Long id) {
        return homeworkMapper.selectHomework(id);
    }

    public Page<Homework> selectHomeworkList(HomeworkParam param) {
        Page<Homework> page = new Page<>();
        List<Homework> list = homeworkMapper.selectHomeworkList(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(homeworkMapper.count(param));
            return page;
        }
        page.setTotal(list.size());
        return page;
    }
    public int insertHomework(Homework homework) {
        return homeworkMapper.insertHomework(homework);
    }
}
