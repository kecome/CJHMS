package com.homework.Schedul;

import com.homework.service.HomeworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置类
 *
 * @author xuke
 * @create 2017-04-10 上午 11:00
 **/
@Configuration
@EnableScheduling
public class SchedulingConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HomeworkService homeworkService;

    // 每30秒执行一次
    //@Scheduled(cron = "0/30 * * * * ?")
    public void scheduler() {
        homeworkService.updateStatus();
        logger.info(">>>>>>>>>>>>> 每30秒更新一次homework表，将预发布时间到期的作业进行发布 ");
    }
}
