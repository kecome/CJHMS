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
import com.homework.model.Question;
import com.homework.model.Studentwork;
import com.homework.param.HomeworkParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //插入或更新作业记录
        Homework homework = hq.getHomework();
        Long homeworkId = homework.getId();
        if(homeworkId != null) {
            Homework hk = homeworkMapper.selectHomework(homeworkId);
            if(hk.getStatus() == 1) {   //作业已经发布不可以再修改
                throw new BusinessException(ErrorInfo.HOMEWORK_PUBLIC.code, ErrorInfo.HOMEWORK_PUBLIC.desc);
            }
            homeworkMapper.updateHomework(homework);
            homeworkClassMapper.deleteHomeworkClass(homeworkId);
            studentworkMapper.deleteStudentwork(homeworkId);
            questionMapper.deleteQuestion(homeworkId);
        }else {
            homeworkMapper.insertHomework(homework);
            homeworkId = homework.getId();
        }
        //插入作业班级关联记录
        HomeworkClass hc = null;
        for(Map<Long, List<Long>> classId : hq.getClassIds()) {
            for(Map.Entry<Long, List<Long>> entry : classId.entrySet()) {
                hc = new HomeworkClass();
                hc.setClassId(entry.getKey());
                hc.setHomeworkId(homeworkId);
                homeworkClassMapper.insertHomeworkClass(hc);
                //插入作业学生关联记录
                Studentwork sw = null;
                if(entry.getValue() != null && entry.getValue().size() > 0) {
                    for(Long sid : entry.getValue()) {
                        sw = new Studentwork();
                        sw.setHomeworkId(homeworkId);
                        sw.setStudentId(sid);
                        sw.setClassId(entry.getKey());
                        sw.setSubmit(0);
                        sw.setMark(0);
                        studentworkMapper.insertStudentwork(sw);
                    }
                }
            }
        }
        //插入作业下的题目
        for(Question question : hq.getQuestions()) {
            question.setHomeworkId(homeworkId);
            questionMapper.insertQuestion(question);
        }
    }

    /**
     * 根据id查询作业包括题目
     * @param id
     * @return
     */
    public HomeworkQuestiion getHomework(Long id) {
        Homework homework = homeworkMapper.selectHomework(id);
        if(homework == null) return null;
        List<HomeworkClass> homeworkClasses = homeworkClassMapper.selectList(id);
        List<Question> questiions = questionMapper.selectList(id);
        HomeworkQuestiion hq = new HomeworkQuestiion();
        hq.setHomework(homework);
        hq.setQuestions(questiions);
        List<Map<Long, List<Long>>> classIds = new ArrayList<>();
        Map<Long, List<Long>> classId = new HashMap<>();
        for(HomeworkClass cla : homeworkClasses) {
            classId.put(cla.getClassId(), null);
        }
        classIds.add(classId);
        hq.setClassIds(classIds);
        return hq;
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
