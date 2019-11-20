package com.wbg.treasure.util;

import com.wbg.treasure.entity.GetContent;
import com.wbg.treasure.entity.WebsiteInformation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;

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
        String uuid = UUID.randomUUID().toString();
        getContent.setUuid(uuid);
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
                String filePath = this.getClass().getResource("/").getPath();
                //  G:/treasure/target/classes/
                filePath += "html"+ File.separator + uuid+".html";
                filePath = filePath.replaceAll("\\/|"+Matcher.quoteReplacement("\\"),Matcher.quoteReplacement(File.separator));
                content = new String(bytes, endCoding);

                Util.outFile(content,filePath);
                getContent.setPageConnent(content);//网页内容
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
    private  void tests(){
        String filePath = this.getClass().getResource("/").getPath();
        filePath = filePath.replaceAll("\\/|"+Matcher.quoteReplacement("\\"),Matcher.quoteReplacement(File.separator));

        System.out.println(filePath+"html" +File.separator+"56465");

    }


    public static void main(String[] args) {
        String path = "A:\\apache-tomcat-8.5.29\\webapps\\ROOT\\WEB-INF\\classes\\html\\48b077e8-0c65-40c2a-af95" +
                "-335b2e32e7c4.html";
        File file = new File(path);
        System.out.println(file.exists());
        System.out.println(path);
    }
}
