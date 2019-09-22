package com.wbg.treasure.util;

import com.wbg.treasure.entity.GetContent;
import com.wbg.treasure.entity.WebsiteInformation;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 设置内容
 *
 */
public class SetObject {
    /**
     * 获取网页内容
     * 给一个websiteInformation对象+期数  返回GetContent对象
     * @param websiteInformation
     * @param Number
     * @return
     */
    public GetContent getPageContent(WebsiteInformation websiteInformation, String Number){
        //获取网页编码
        String endCoding = Util.checkNull(Util.getEncoding(websiteInformation.getPageAddress()));
        //如果为空的时候，设置默认为UTF-8
        if(endCoding == ""){
            endCoding = "UTF-8";
        }
        GetContent getContent = new GetContent();
        getContent.setPageUuid(websiteInformation.getUuid().toString());//网页id
        getContent.setGetStatis("true");//状态
        getContent.setNumberOfPeriods(Number);//期数
        getContent.setCharsetCode(endCoding);//编码
        getContent.setPageAddress(websiteInformation.getPageAddress());//网页地址
        //获取网页内容
        InputStream inputStream = Util.getInputStream(websiteInformation.getPageAddress());
        //如果出现null或者异常的时候
        if(inputStream == null){
            getContent.setPageConnent("inputStream == null");//网页内容
            getContent.setGetStatis("false");//状态
        }else{
            try {
                byte[] bytes = Util.readInputStream(inputStream);
                String content = new String(bytes, endCoding);
                //切换编码为UTF-8
                content.replace(endCoding,"UTF-8");
                getContent.setPageConnent(new String(bytes, endCoding));//网页内容
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                getContent.setGetStatis("false");//状态
                return getContent;
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return getContent;
    }

}
