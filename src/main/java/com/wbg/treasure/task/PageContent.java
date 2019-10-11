package com.wbg.treasure.task;

import com.wbg.treasure.entity.ScheduleLog;
import com.wbg.treasure.service.AwardInformationService;
import com.wbg.treasure.service.GetContentService;
import com.wbg.treasure.service.ScheduleLogService;
import com.wbg.treasure.util.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class PageContent implements Job {

    private static ScheduleLogService scheduleLogService;
    private static GetContentService getContentService;
    private static AwardInformationService awardInformationService;
    private static boolean RunStatus =  false;
    @Autowired
    public void setGetContentService(AwardInformationService awardInformationService) {
        PageContent.awardInformationService = awardInformationService;
    }
    @Autowired
    public void setScheduleLogService(ScheduleLogService scheduleLogService) {
        PageContent.scheduleLogService = scheduleLogService;
    }
    @Autowired
    public void setGetContentService(GetContentService getContentService) {
        PageContent.getContentService = getContentService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
        if(RunStatus){
            System.out.println("程序正在跑");
            return;
        }
        RunStatus = true;
        String startTime = Util.toJson(new Date());
        boolean status = true;
        int Number =0;
        ScheduleLog scheduleLog = new ScheduleLog();
        try {
            Number = getContentService.getContent(awardInformationService.getjs());



        }catch (Exception c){
            status = false;
            c.printStackTrace();
        }finally {
            scheduleLog.setClassName(this.getClass().getName());
            scheduleLog.setStartTime(startTime);
            scheduleLog.setEndTime(Util.toJson(new Date()));
            scheduleLog.setDatasource(Integer.toString(Number));
            scheduleLog.setStatusLog(Boolean.toString(status));
            int a = scheduleLogService.insert(scheduleLog);
            if(a > 1){
                System.out.println("成功添加日志");
            }
            RunStatus = false;
        }

    }

    public static void main(String[] args) {

    }
}
