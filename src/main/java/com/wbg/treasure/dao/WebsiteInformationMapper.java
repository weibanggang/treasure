package com.wbg.treasure.dao;

import com.wbg.treasure.entity.WebsiteInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsiteInformationMapper {
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
     * 根据主键全部修改操作
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