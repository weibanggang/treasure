package com.wbg.treasure.dao;

import com.wbg.treasure.entity.AwardInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardInformationMapper {
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
     * @param awardInformation
     * @return
     */
    int insert(AwardInformation awardInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    AwardInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<AwardInformation> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param awardInformation
     * @return
     */
    int updateByPrimaryKey(AwardInformation awardInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();

    String getjs();
}