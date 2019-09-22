package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.AwardInformationMapper;
import com.wbg.treasure.dao.WebsiteInformationMapper;
import com.wbg.treasure.entity.AwardInformation;
import com.wbg.treasure.entity.WebsiteInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)// SpringJUnit支持，由此引入Spring-Test框架支持！ 
@ContextConfiguration({"classpath:spring/*.xml","classpath:mybatis-config.xml"})//用于加载bean
public class AwardInformationServiceImplTest {

    @Autowired
    private AwardInformationMapper awardInformationMapper;
    @Autowired
    private WebsiteInformationMapper websiteInformationMapperl;
    @Test
    public void selectAll() {
        List<WebsiteInformation> list =  websiteInformationMapperl.selectAll();
        for (WebsiteInformation awardInformation : list) {
            System.out.println(awardInformation);
        }
    }
}