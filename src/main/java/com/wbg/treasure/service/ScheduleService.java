package com.wbg.treasure.service;

import com.wbg.treasure.entity.Schedule;

import java.util.List;

public interface ScheduleService {

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
     * @param schedule
     * @return
     */
    int insert(Schedule schedule);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Schedule selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Schedule> selectAll();

    /**
     * 修改操作
     *
     * @param schedule
     * @return
     */
    int updateByPrimaryKey(Schedule schedule);

    /**
     * 查询所有数据
     * @return
     */
    int count();
    void start();

}