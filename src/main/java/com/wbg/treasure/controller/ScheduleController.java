package com.wbg.treasure.controller;

import com.github.pagehelper.PageHelper;
import com.sun.net.httpserver.HttpServer;
import com.wbg.treasure.entity.Schedule;
import com.wbg.treasure.service.ScheduleService;
import com.wbg.treasure.util.Result;
import com.wbg.treasure.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController  implements ServletContextListener {
    @Autowired
    private  ScheduleService scheduleService;

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("销毁");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        System.out.println("开始执行周期任务");
        scheduleService.start(); ;
    }

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(int id) {
        try {
            return scheduleService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象schedule
     *
     * @param schedule
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Schedule schedule) {
        try {
            return scheduleService.insert(schedule) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }

    }
    /**
     * 根据主键查找对象  最多只能返回一个对象
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int id) {
        try {
            Schedule schedule1 = scheduleService.selectByPrimaryKey(id);
            if (schedule1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(schedule1);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<Schedule> list = scheduleService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 根据主键修改全部字段
     *
     * @param schedule
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody Schedule schedule) {
        try {
            return scheduleService.updateByPrimaryKey(schedule) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }


    /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        try {
            PageHelper.startPage(page, limit);
            List<Schedule> list = scheduleService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(0, "ok", list, scheduleService.count());
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}

