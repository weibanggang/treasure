package com.wbg.treasure.service.impl;

import com.wbg.treasure.dao.GetContentMapper;
import com.wbg.treasure.entity.GetContent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;


public class GetContentServiceImplTest {
    @Autowired
    private GetContentMapper getContentMapper;

    @Test
    public void selectAll() {
        List<GetContent> list =  getContentMapper.selectAll();
        for (GetContent getContent : list) {
            System.out.println(getContent.toString());
        }
    }
}