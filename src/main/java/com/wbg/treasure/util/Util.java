package com.wbg.treasure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Util {
    /**
     * @param strUrl url地址
     * @return InputStream
     */
    public static InputStream getInputStream(String strUrl) {
        URL url = null;
        InputStream inputStream = null;
        try {
            url = new URL(strUrl);
            if ("https".equalsIgnoreCase(url.getProtocol())) {
                return TrustSSL.HttpsSSL(url);
            } else {
              return getHttp(url);
            }

        } catch (MalformedURLException e) {
            System.out.println("获取页面URL异常" + e.getMessage());
            return inputStream;
        }
    }

    private static InputStream getHttp(URL strUrl) {
        try {
            HttpURLConnection conn = (HttpURLConnection) strUrl.openConnection();
            //设置超时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取服务器响应代码
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                //得到输入流
                return conn.getInputStream();
            } else {
                System.out.println("获取不到 " + strUrl + " 源码，服务器响应代码为：" + responsecode);
                return null;
            }
        } catch (Exception e) {
            System.out.println("获取页面异常" + e.getMessage());
            return null;
        }

    }

    /**
     * @param inputStream
     * @return 网页内容
     */
    public static byte[] readInputStream(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("获取网页内容异常");
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bos.toByteArray();
    }

    /**
     * 导出文件
     *
     * @param context 内容
     * @param filename 路径
     */
    public static void outFile(String context, String filename) {
        try {
            File file = new File(filename);
            //获取文件
            File parent = file.getParentFile();
            //如果是目录
            if (parent != null) {
                //创建目录
                parent.mkdirs();
            }
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(context);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可以通过分析header，查找字符编码
     *
     * @param url
     * @return
     */
    public static String getEncodingByHeader(URL url) {
        String strEnCoding = null;
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestProperty("User-agent", "Mozilla/4.0");
            Map<String, List<String>> map = httpConn.getHeaderFields();

            Set<String> keys = map.keySet();
            Iterator<String> iterator = keys.iterator();

            // 遍历,查找字符编码
            String key = null;
            String tmp = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                tmp = map.get(key).toString().toLowerCase();
                // 获取content-type charset
                if (key != null && key.equals("Content-Type")) {
                    int m = tmp.indexOf("charset=");
                    if (m != -1) {
                        strEnCoding = tmp.substring(m + 8).replace("]", "");
                        return strEnCoding;
                    }
                }
            }
        } catch (IOException e) {
            strEnCoding = null;
            System.out.println("可以通过分析header异常" + e.getMessage());
        } finally {
            try {
                if (httpConn != null)
                    httpConn.disconnect();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return strEnCoding;
    }

    /**
     * 从网页meta中解析出charset
     *
     * @param inputs
     * @return
     */
    public static String getEncodingByMeta(InputStream inputs) {
        String strEnCoding = null;
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(inputs));
            while ((line = in.readLine()) != null) {

                if (line.contains("<meta") || line.contains("<META")) {
                    // 解析html源码，取出<meta />区域，并取出charset
                    line = line.toLowerCase();
                    String strbegin = "<meta";
                    String strend = ">";
                    String strtmp;
                    int begin = line.indexOf(strbegin);
                    int end = -1;
                    int inttmp;
                    while (begin > -1) {
                        end = line.substring(begin).indexOf(strend);
                        if (begin > -1 && end > -1) {
                            strtmp = line.substring(begin, begin + end)
                                    .toLowerCase();
                            inttmp = strtmp.indexOf("charset");
                            if (inttmp > -1) {
                                strEnCoding = strtmp.substring(inttmp + 7, end)
                                        .replace("=", "").replace("/", "")
                                        .replace("\"", "").replace("\'", "");
                                if (strEnCoding.indexOf(" ") != -1) {
                                    strEnCoding = strEnCoding.substring(0, strEnCoding.indexOf(" "));
                                }
                                return strEnCoding;
                            }
                        }
                        line = line.substring(begin);
                        begin = line.indexOf(strbegin);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }

    /**
     * 空值检查
     *
     * @param o
     * @return
     */
    public static String checkNull(Object o) {
        if (o == null) {
            return "";
        } else if ("null".equals(o.toString().toLowerCase().trim())) {
            return "";
        } else {
            return o.toString().trim();
        }
    }

    /**
     * 向指定的URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return 远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader bufferedReader = null;
        try {
            //1、读取初始URL
            String urlNameString = url + "?" + param;
            //2、将url转变为URL类对象
            URL realUrl = new URL(urlNameString);

            //3、打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //4、设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //5、建立实际的连接
            connection.connect();
            //获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //6、定义BufferedReader输入流来读取URL的响应内容 ，UTF-8是后续自己加的设置编码格式，也可以去掉这个参数
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = "";
            while (null != (line = bufferedReader.readLine())) {
                result += line;
            }
//			int tmp;
//            while((tmp = bufferedReader.read()) != -1){
//                result += (char)tmp;
//            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("发送GET请求出现异常！！！" + e);
            e.printStackTrace();
        } finally {        //使用finally块来关闭输入流
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return result;
    }

    //Date类型转换成String类型
    public static String toJson(Object obj) {
        String reuqest = null;
        //对象映射
        ObjectMapper mapper = new ObjectMapper();
        //设置时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        mapper.setDateFormat(dateFormat);
        try {
            reuqest = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reuqest;
    }

    /**
     * @param strUrl url地址
     * @return 编码
     */
    public static String getEncoding(String strUrl) {
        //获取编码
        String enCoding = null;
        try {
            enCoding = Util.checkNull(Util.getEncodingByHeader(new URL(strUrl)));
            if (enCoding == "") {
                enCoding = Util.getEncodingByMeta(Util.getInputStream(strUrl));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return enCoding;
        }
        return enCoding;
    }

    /**
     * 转换编码
     * @param theString
     * @return
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

}
