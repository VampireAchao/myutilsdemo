package com.ruben.utils.other.utils.alipay;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpsRequest {

    public static String httpsRequest(String requestUrl, String requestMethod, String outStr) throws Exception {
        //建立连接

        //创建 SSLContext
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        TrustManager[] tm = {new MyX509TrustManager()};
        //初始化
        sslContext.init(null, tm, new java.security.SecureRandom());
        //获取sslSOCKETfactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        //获取当前实例使用sslSOCKETfactory
        StringBuffer buffer = null;
        URL url = new URL(requestUrl);                                       //修改  为https路径
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(); //修改  换成Https..
        conn.setRequestMethod(requestMethod);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setSSLSocketFactory(ssf); //新增
        conn.connect();

        //发送数据
        if (outStr != null) {
            OutputStream os = conn.getOutputStream();
            os.write(outStr.getBytes(StandardCharsets.UTF_8));
            os.close();
        }

        //读取服务器的内容
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        buffer = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }

        //打印数据
        System.out.println("buffer.toString(): " + buffer.toString());

        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        httpsRequest("https://www.baidu.com/", "GET", "success"); //回调,需回复支付宝"success"
    }

}


