package com.homework.Schedul;

import com.homework.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务配置类
 *
 * @author xuke
 * @create 2017-04-10 上午 11:00
 **/
@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Autowired
    private HomeworkService homeworkService;

    // 每30秒执行一次
    @Scheduled(cron = "0/30 * * * * ?")
    public void scheduler() {
        homeworkService.updateStatus();
        System.out.println(">>>>>>>>>>>>> scheduled ... ");
    }
}
