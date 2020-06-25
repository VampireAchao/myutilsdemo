package com.ruben.utils.other.utils.alipay;

import java.security.MessageDigest;
import java.util.Map;

public class MD5Util {

    //MD5加密           升序串:xx&xx&0.01key  key=value, UTF-8
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = origin;
            //new一个MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    //将MD5输出的二进制结果转换为小S写H的O十P六X进5制
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String[] hexDigits =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    //生成MD5paySign
    public static String MD5PaySign(String appId, Map<String, String> map) {
        // 生成支付签名paySign
        String paySign = "appId=" + appId + "&nonceStr=" + map.get("nonceStr") + "&package=" + map.get("package") +
                "&signType=" + map.get("signType") + "&timeStamp=" + map.get("timeStamp") + "&key=" + map.get("key");
        return MD5Encode(paySign, null).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(MD5Encode("http://alipay?a=1&b=2&c=3", "UTF-8")); //ad8c2bb7fe0eb9efce8d2f17448bb50b
        System.out.println(MD5Encode("http://alipay?a=1&b=2&c=3", "GBK"));   //ad8c2bb7fe0eb9efce8d2f17448bb50b
    }

}


