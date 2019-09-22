package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.ScheduleLogMapper;
import com.wbg.treasure.entity.ScheduleLog;
import com.wbg.treasure.service.ScheduleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleLogServiceImpl implements ScheduleLogService {

    @Autowired
    private ScheduleLogMapper scheduleLogMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return scheduleLogMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param scheduleLog
     * @return
     */
    @Override
    public int insert(ScheduleLog scheduleLog) {
        return scheduleLogMapper.insert(scheduleLog);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public ScheduleLog selectByPrimaryKey(int id) {
        return scheduleLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<ScheduleLog> selectAll() {
        return scheduleLogMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param scheduleLog
     * @return
     */
    @Override
    public int updateByPrimaryKey(ScheduleLog scheduleLog) {
        return scheduleLogMapper.updateByPrimaryKey(scheduleLog);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return scheduleLogMapper.count();
    }

}
