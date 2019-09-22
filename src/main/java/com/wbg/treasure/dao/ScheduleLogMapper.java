package com.wbg.treasure.dao;

import com.wbg.treasure.entity.ScheduleLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleLogMapper {
    /**
     * 根据主键删除操作
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
     * 根据主键全部修改操作
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