package com.wbg.treasure.schedule;

import com.wbg.treasure.controller.ScheduleController;
import com.wbg.treasure.dao.ScheduleMapper;
import com.wbg.treasure.entity.Schedule;
import com.wbg.treasure.service.ScheduleService;
import com.wbg.treasure.service.impl.ScheduleServiceImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 定时任务管理
 * @author WBG
 */
public class QuartzManager  {

    //private static final long serialVersionUID = 6371546053335370908L;

    public static Scheduler scheduler;
    static{
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description: 添加一个定时任务 注册调度任务
     * @param triggerName 触发器名 同组中的触发器名不能重复
     * @param class_name  类路径名  com.wbg.xxx.java
     * @param cronExpression   时间设置，参考quartz说明文档
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addJob(String triggerName, String class_name, String cronExpression, String param) {
        try {
            Class classPath = Class.forName(class_name);
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(classPath).withIdentity(triggerName, scheduler.DEFAULT_GROUP).build();

            // 在任务中可以通过 arg0.getJobDetail().getJobDataMap().getString("param") 获取
            if(param == null){
                param = "";
            }
            jobDetail.getJobDataMap().put("param",param);

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, scheduler.DEFAULT_GROUP);
            //使用这句可以防止定时器弥补
            triggerBuilder.startNow();
            // 触发器时间设定,立即执行
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加任务失败" + class_name + " |----| " + triggerName + "cron" + cronExpression +" Exception " +e);
        }
    }
    /**
     *@Description: 停止触发器
     * @param triggerName  触发器名 同组中的触发器名不能重复
     * @return
     */
    public static boolean pauseTrigger(String triggerName) {
        boolean status = false;
        try {
            TriggerKey triggerKey = new TriggerKey(triggerName, scheduler.DEFAULT_GROUP);
            scheduler.pauseTrigger(triggerKey);
            status = true;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        if (status) {
            System.out.println("触发器：" + triggerName + "停止成功！");
        } else {
            System.out.println("触发器：" + triggerName + "停止失败！");
        }
        return status;
    }
    /**
     *@Description: 重启触发器
     * @param triggerName  触发器名称
     * @return
     */
    public static void resumeTrigger(String triggerName) {
        try {
            // 重启触发器
            TriggerKey triggerKey = new TriggerKey(triggerName, scheduler.DEFAULT_GROUP);
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *@Description: 删除触发器
     * @param triggerName  触发器名称
     * @return
     */
    public static boolean removeTrigger(String triggerName) {
        try {

            System.out.println("删除触发器: " + triggerName);
            //先停止后移除
            // 停止触发器
            TriggerKey triggerKey = new TriggerKey(triggerName, scheduler.DEFAULT_GROUP);
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            return scheduler.unscheduleJob(triggerKey);

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     *@Description: 删除所有触发器和任务
     *@throws Exception
     */
    public void removeAllTriggersAndJobs() throws Exception {
        try {
            List<String> groups = scheduler.getTriggerGroupNames();
            for (int i = 0; i < groups.size(); i++) {
                GroupMatcher<TriggerKey> group = GroupMatcher.triggerGroupEquals(groups.get(i));
                Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(group);
                for(TriggerKey key : triggerKeys){
                    System.out.println(("remove:trigger:Name=" + key.getName() + ":group=" + key.getGroup()));
                    // 移除触发器
                    scheduler.unscheduleJob(key);
                }
            }

            groups = scheduler.getJobGroupNames();
            for (int i = 0; i < groups.size(); i++) {
                GroupMatcher<JobKey> group = GroupMatcher.jobGroupEquals(groups.get(i));
                Set<JobKey> jobKeys = scheduler.getJobKeys(group);
                for(JobKey key : jobKeys){
                    System.out.println(("remove:job:Name=" + key.getName() + ":group=" + key.getGroup()));
                    //删除任务
                    scheduler.deleteJob(key);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(("移除任务失败：" + ex));
            throw new Exception();
        }
    }
    /**
     * @Description: 获得所有触发器列表
     * @return
     */
    public static List<Trigger> getAllTriggers() {

        List<Trigger> triggers = new ArrayList<>();
        try {
            List<String> groups = scheduler.getTriggerGroupNames();
            for (int i = 0; i < groups.size(); i++) {
                GroupMatcher<TriggerKey> group = GroupMatcher.triggerGroupEquals(groups.get(i));
                Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(group);
                for(TriggerKey key : triggerKeys){
                    Trigger trigger = scheduler.getTrigger(key);
                    triggers.add(trigger);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return triggers;
    }

    /**
     * @Description: 检查触发器是否存在
     * @param triggerName
     * @return
     */
    public static boolean isJobExist(String triggerName) {
        boolean status = false;

        try {
            JobKey jobKey = new JobKey(triggerName, scheduler.DEFAULT_GROUP);
            JobDetail jd = scheduler.getJobDetail(jobKey);
            if (null != jd) {
                status = true;
                System.out.println(("触发器：" + triggerName + "已经存在！"));
            }else{
                System.out.println(("触发器：" + triggerName + "不存在！"));
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return status;
    }
    /**
     * @Description: 数据库读取任务列表
     */
    public void schedule(List<Schedule> schedule){

        try{
            //先清除所有任务
            this.removeAllTriggersAndJobs();
            System.out.println(schedule.size());
            for(int i=0;schedule!=null & i<schedule.size() ; i++){
                if("启用".equals(schedule.get(i).getTaskStatus())){
                    addJob(schedule.get(i).getTaskName(),
                            schedule.get(i).getClassName(),
                            schedule.get(i).getCronExpression(),
                            schedule.get(i).getParam());
                }

            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(("启动任务出错：" + e));
        }

    }
}
