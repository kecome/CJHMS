package com.homework.param;

import com.homework.model.AnswerLog;
import com.homework.model.BaseModel;
import com.homework.model.Studentanswer;

/**
 * @author xuke
 * @create 2017-04-21 下午 21:32
 **/
public class StudentanswerLog extends BaseModel{
    private Studentanswer studentanswer;
    private AnswerLog answerLog;

    public Studentanswer getStudentanswer() {
        return studentanswer;
    }

    public void setStudentanswer(Studentanswer studentanswer) {
        this.studentanswer = studentanswer;
    }

    public AnswerLog getAnswerLog() {
        return answerLog;
    }

    public void setAnswerLog(AnswerLog answerLog) {
        this.answerLog = answerLog;
    }
}
