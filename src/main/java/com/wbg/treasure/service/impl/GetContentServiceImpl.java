package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.GetContentMapper;
import com.wbg.treasure.dao.WebsiteInformationMapper;
import com.wbg.treasure.entity.GetContent;
import com.wbg.treasure.entity.WebsiteInformation;
import com.wbg.treasure.service.GetContentService;
import com.wbg.treasure.util.SetObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetContentServiceImpl implements GetContentService {

    @Autowired
    private GetContentMapper getContentMapper;
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
        return getContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param getContent
     * @return
     */
    @Override
    public int insert(GetContent getContent) {
        return getContentMapper.insert(getContent);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public GetContent selectByPrimaryKey(int id) {
        return getContentMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<GetContent> selectAll() {
        return getContentMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param getContent
     * @return
     */
    @Override
    public int updateByPrimaryKey(GetContent getContent) {
        return getContentMapper.updateByPrimaryKey(getContent);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return getContentMapper.count();
    }
    public int getContent(String Number){
        SetObject setObject = new SetObject();
        List<WebsiteInformation> list = websiteInformationMapper.selectAll();
        int sum = 0;
        for (WebsiteInformation websiteInformation : list) {
            getContentMapper.insert(setObject.getPageContent(websiteInformation,Number));
            sum++;
        }
        return sum;
    }

}
