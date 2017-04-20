package com.homework.data;

import com.homework.model.Question;
import com.homework.model.Studentanswer;

/**
 * @author xuke
 * @create 2017-04-20 下午 19:30
 **/
public class AnswerQuestion {
    private Studentanswer studentanswer;
    private Question question;

    public Studentanswer getStudentanswer() {
        return studentanswer;
    }

    public void setStudentanswer(Studentanswer studentanswer) {
        this.studentanswer = studentanswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
