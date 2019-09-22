package com.wbg.treasure.dao;

import com.wbg.treasure.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleMapper {
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
     * 根据主键全部修改操作
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
}