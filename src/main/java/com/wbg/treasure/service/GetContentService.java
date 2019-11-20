package com.wbg.treasure.service;

import com.wbg.treasure.entity.GetContent;

import java.util.List;

public interface GetContentService {

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
     * 修改操作
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
    int getContent(String Number);
}