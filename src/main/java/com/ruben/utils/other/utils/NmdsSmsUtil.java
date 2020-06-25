package com.ruben.utils.other.utils;

import java.io.IOException;

/**
 * @ClassName: NmdsSmsUtil
 * @Date: 2020/6/24 0024 13:58
 * @Description:
 * @Author: <achao1441470436@gmail.com>
 */
public class NmdsSmsUtil {

    public static boolean sendSms(String phoneNumber, String text) {
        try {
            String sn = "SDK-DGG-010-00324";
            String pwd = "Nhh5dpl7";
            Client client = null;
            client = new Client(sn, pwd);
            //短信发送
            String content = java.net.URLEncoder.encode("[筑建供应链]您的验证码为：" + text, "utf-8");
            String resultMt = client.mdsmssend(phoneNumber, content, "", "", "", "");
            System.out.print(resultMt);
            //个性短信发送
//		String result_gxmt = client.mdgxsend("138*****000", "测试", "", "", "", "");
//		System.out.print(result_gxmt);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        try {
            String sn = "SDK-DGG-010-00324";
            String pwd = "Nhh5dpl7";
            Client client = null;
            client = new Client(sn, pwd);
            //短信发送
            String content = java.net.URLEncoder.encode("接口测试[筑建供应链]", "utf-8");
            String resultMt = client.mdsmssend("17623535129", content, "", "", "", "");
            System.out.print(resultMt);
            //个性短信发送
//		String result_gxmt = client.mdgxsend("138*****000", "测试", "", "", "", "");
//		System.out.print(result_gxmt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
