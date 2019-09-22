package com.wbg.treasure.task;

import com.wbg.treasure.entity.AwardInformation;
import com.wbg.treasure.service.AwardInformationService;
import com.wbg.treasure.service.GetContentService;
import com.wbg.treasure.service.ScheduleLogService;
import com.wbg.treasure.util.Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.wbg.treasure.util.Util.unicodeToUtf8;
@Component
public class NumberOfPeriods implements Job {
    private static AwardInformationService awardInformationService;
    private static boolean RunStatus =  false;
    @Autowired
    public void setGetContentService(AwardInformationService awardInformationService) {
        NumberOfPeriods.awardInformationService = awardInformationService;
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        AwardInformation awardInformation = new AwardInformation();
        String str = unicodeToUtf8(getJsonTM());
        awardInformation.setGetStatis("true");
        try {
            if(Util.checkNull(str) == ""){
                System.out.println("NumberOfPeriods-----------------Util.checkNull(str) == null------------");
                return;
            }
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
            c.printStackTrace();
        }finally {
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
