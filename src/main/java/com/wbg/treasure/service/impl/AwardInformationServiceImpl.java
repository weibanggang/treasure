package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.AwardInformationMapper;
import com.wbg.treasure.entity.AwardInformation;
import com.wbg.treasure.service.AwardInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardInformationServiceImpl implements AwardInformationService {

    @Autowired
    private AwardInformationMapper awardInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return awardInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param awardInformation
     * @return
     */
    @Override
    public int insert(AwardInformation awardInformation) {
        return awardInformationMapper.insert(awardInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public AwardInformation selectByPrimaryKey(int id) {
        return awardInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<AwardInformation> selectAll() {
        return awardInformationMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param awardInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(AwardInformation awardInformation) {
        return awardInformationMapper.updateByPrimaryKey(awardInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return awardInformationMapper.count();
    }

    @Override
    public String getjs() {
        return awardInformationMapper.getjs();
    }
}
