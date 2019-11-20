package com.wbg.treasure.dao;

import com.wbg.treasure.entity.GetContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GetContentMapper {
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
     * @param getContent
     * @return
     */
    int insert(GetContent getContent);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    GetContent selectByPrimaryKey(String id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<GetContent> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param getContent
     * @return
     */
    int updateByPrimaryKey(GetContent getContent);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}