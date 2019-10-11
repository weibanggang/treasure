package com.wbg.treasure.util;

import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

public class TrustSSL {

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    public static InputStream HttpsSSL(URL strUrl){

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());
            HttpsURLConnection conn = (HttpsURLConnection) strUrl.openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            //设置超时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //读取数据超时
            conn.setReadTimeout(1000 * 60 * 5);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.connect();
            //获取服务器响应代码
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                //得到输入流
                return conn.getInputStream();
            } else {
                System.out.println("获取不到 " + strUrl + " 源码，服务器响应代码为：" + responsecode);
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static void main(String[] args) throws Exception {
        HttpsSSL(new URL("url"));
    }
}

