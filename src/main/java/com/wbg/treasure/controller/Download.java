package com.wbg.treasure.controller;

import com.wbg.treasure.entity.GetContent;
import com.wbg.treasure.service.GetContentService;
import com.wbg.treasure.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.regex.Matcher;

@Controller
public class Download {

    @Autowired
    private GetContentService getContentService;
    /**
     * 文件下载
     * @throws IOException
     */
    @RequestMapping(value="/download",method=RequestMethod.GET)
    public void download(@RequestParam(value="uuid")String filename,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        String filePath = this.getClass().getResource("/").getPath();
        //  G:/treasure/target/classes/
        filePath += "html"+ File.separator + filename+".html";
        filePath = filePath.replaceAll("\\/|"+ Matcher.quoteReplacement("\\"),Matcher.quoteReplacement(File.separator));
        File file  = new File(filePath);
        if(!file.exists()){
           GetContent getContent = getContentService.selectByPrimaryKey(filename);
            Util.outFile(getContent.getPageConnent(),filePath);
            System.out.println("创建成功"+filename+filePath);
        }
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(file));
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename+".html");
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
    }

}
