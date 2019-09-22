package com.wbg.treasure.service;

import com.wbg.treasure.entity.WebsiteInformation;

import java.util.List;

public interface WebsiteInformationService {

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
     * @param websiteInformation
     * @return
     */
    int insert(WebsiteInformation websiteInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    WebsiteInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<WebsiteInformation> selectAll();

    /**
     * 修改操作
     *
     * @param websiteInformation
     * @return
     */
    int updateByPrimaryKey(WebsiteInformation websiteInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}