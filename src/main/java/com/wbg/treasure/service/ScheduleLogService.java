package com.wbg.treasure.service;

import com.wbg.treasure.entity.ScheduleLog;

import java.util.List;

public interface ScheduleLogService {

    /**
     * 删除操作 根据主键
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加操作
     *
     * @param scheduleLog
     * @return
     */
    int insert(ScheduleLog scheduleLog);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    ScheduleLog selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<ScheduleLog> selectAll();

    /**
     * 修改操作
     *
     * @param scheduleLog
     * @return
     */
    int updateByPrimaryKey(ScheduleLog scheduleLog);

    /**
     * 查询所有数据
     * @return
     */
    int count();

}