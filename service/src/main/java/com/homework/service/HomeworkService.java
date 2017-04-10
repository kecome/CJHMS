package com.homework.service;

import com.homework.data.HomeworkQuestiion;
import com.homework.mapper.HomeworkClassMapper;
import com.homework.mapper.QuestionMapper;
import com.homework.mapper.StudentworkMapper;
import com.homework.model.HomeworkClass;
import com.homework.model.Studentwork;
import com.homework.param.HomeworkParam;
import com.homework.data.Page;
import com.homework.mapper.HomeworkMapper;
import com.homework.model.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 发布作业
     * @param hq
     */
    public void postHomework(HomeworkQuestiion hq) {
        //插入作业记录
        Homework homework = hq.getHomework();
        homeworkMapper.insertHomework(homework);
        //插入作业班级关联记录
        if(hq.getClassIds() != null && hq.getClassIds().size() > 0) {
            HomeworkClass hc = null;
            for(Long classId : hq.getClassIds()) {
                hc = new HomeworkClass();
                hc.setHomeworkId(4545L);
                hc.setClassId(65454L);
                hc.setHomeworkId(homework.getId());
                homeworkClassMapper.insertHomeworkClass(hc);
            }
        }
        //插入作业学生关联记录
        Long[] studentIds = {222L, 333L, 444L};
        Studentwork sw = null;
        for(Long studentId : studentIds) {
            sw = new Studentwork();
            sw.setHomeworkId(homework.getId());
            sw.setStudentId(studentId);
            sw.setSubmit(0);
            sw.setMark(0);
            studentworkMapper.insertStudentwork(sw);
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
