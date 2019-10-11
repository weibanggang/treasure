package com.wbg.treasure.task;

import com.wbg.treasure.entity.AwardInformation;
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

import static com.wbg.treasure.util.Util.unicodeToUtf8;
@Component
public class NumberOfPeriods implements Job {
    private static AwardInformationService awardInformationService;
    private static GetContentService getContentService;
    private static ScheduleLogService scheduleLogService;
    private static boolean RunStatus =  false;

    @Autowired
    public void setScheduleLogService(ScheduleLogService scheduleLogService) {
        NumberOfPeriods.scheduleLogService = scheduleLogService;
    }
    @Autowired
    public void setGetContentService(AwardInformationService awardInformationService) {
        NumberOfPeriods.awardInformationService = awardInformationService;
    }
    @Autowired
    public void setGetContentService(GetContentService getContentService) {
        NumberOfPeriods.getContentService = getContentService;
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        AwardInformation awardInformation = new AwardInformation();
        ScheduleLog scheduleLog = new ScheduleLog();
        String str = unicodeToUtf8(getJsonTM());
        awardInformation.setGetStatis("true");
        boolean status = true;
        String startTime = Util.toJson(new Date());
        int Number =0;
        try {
            if(Util.checkNull(str) == ""){
                System.out.println("NumberOfPeriods-----------------Util.checkNull(str) == null------------");
                return;
            }

            Number = Integer.parseInt(awardInformationService.getjs());
            awardInformation.setAllContent(str);
            String [] aa = str.split(",");
            awardInformation.setPageUuid(aa[8]);
            awardInformation.setUuid(Integer.parseInt(aa[8]) - 1);
            awardInformation.setPageConnent(aa[7]);
           int a =  awardInformationService.insert(awardInformation);
            if(a > 1){
                System.out.println("成功获取："+(Integer.parseInt(aa[8]) - 1 )+ "信息");
            }
        }catch (Exception c){
            status = false;
            c.printStackTrace();
        }finally {
            scheduleLog.setClassName(this.getClass().getName());
            scheduleLog.setStartTime(startTime);
            scheduleLog.setEndTime(Util.toJson(new Date()));
            scheduleLog.setDatasource(Integer.toString(Number));
            scheduleLog.setStatusLog(Boolean.toString(status));
            int as = scheduleLogService.insert(scheduleLog);
            if(as > 1){
                System.out.println("成功添加日志");
            }
            RunStatus = false;
        }


    }
    /**
     * 获取上期特码
     * 上一期期数,平码,平码,平码,平码,平码,平码,特码,下一期期数,月，日，星期，开奖时间
     * 090,19,30,08,44,25,09,37,091,08,10,\u516d,21\u70b930\u5206
     */
    public static String getJsonTM() {
        //获取网页JSON格式
        String str = Util.sendGet("https://41146.com/kaijiang.json", "");
        if (Util.checkNull(str) == "") {
            return null;
        }
        return str;
    }
}
