package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.WebsiteInformationMapper;
import com.wbg.treasure.entity.WebsiteInformation;
import com.wbg.treasure.service.WebsiteInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteInformationServiceImpl implements WebsiteInformationService {

    @Autowired
    private WebsiteInformationMapper websiteInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return websiteInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param websiteInformation
     * @return
     */
    @Override
    public int insert(WebsiteInformation websiteInformation) {
        return websiteInformationMapper.insert(websiteInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public WebsiteInformation selectByPrimaryKey(int id) {
        return websiteInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<WebsiteInformation> selectAll() {
        return websiteInformationMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param websiteInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(WebsiteInformation websiteInformation) {
        return websiteInformationMapper.updateByPrimaryKey(websiteInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return websiteInformationMapper.count();
    }
}
