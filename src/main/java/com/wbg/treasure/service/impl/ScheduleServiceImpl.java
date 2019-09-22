package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.ScheduleMapper;
import com.wbg.treasure.entity.Schedule;
import com.wbg.treasure.schedule.QuartzManager;
import com.wbg.treasure.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        int delete = scheduleMapper.deleteByPrimaryKey(id);
        if (delete > 0){
            QuartzManager quartzManager = new QuartzManager();
            quartzManager.schedule(scheduleMapper.selectAll());
        }
        return delete;
    }

    /**
     * 添加操作
     *
     * @param schedule
     * @return
     */
    @Override
    public int insert(Schedule schedule) {
        int insert = scheduleMapper.insert(schedule);
        if (insert > 0) {
            QuartzManager quartzManager = new QuartzManager();
            quartzManager.schedule(scheduleMapper.selectAll());
        }
        return insert;
    }
    /**
     *
     *
     * @param
     * @return
     */
    public void start() {
            QuartzManager quartzManager = new QuartzManager();
            quartzManager.schedule(scheduleMapper.selectAll());
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Schedule selectByPrimaryKey(int id) {
        return scheduleMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Schedule> selectAll() {
        return scheduleMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param schedule
     * @return
     */
    @Override
    public int updateByPrimaryKey(Schedule schedule) {
        int update = scheduleMapper.updateByPrimaryKey(schedule);
        if (update > 0){
            QuartzManager quartzManager = new QuartzManager();
            quartzManager.schedule(scheduleMapper.selectAll());
        }
        return update;
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return scheduleMapper.count();
    }
}
