package com.ruben.utils.other.utils.alipay;

import java.util.*;

public class Sign {

    //核心: 创建alipay_MD5签名
    public static String creatSign(SortedMap<Object, Object> parameters, String Key) {
        //遍历排序的字典,并拼接成"key=value"格式
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String param = sb.toString();
        System.out.println("param: " + param);  // 执行,测试
        param = param.substring(0, param.length() - 1) + Key;
        System.out.println("p+key: " + param);  // 执行,测试
        //MD5加密
        String sign = MD5Util.MD5Encode(param, "UTF-8");
        return sign;
    }

    //weixin签名 测试
    public static void Sign() {
        //签名参与字符串
        String appid = "wxd930ea5d5a258f4f";                //18位
        String mch_id = "10000100";
        String device_info = "1000";
        String body = "test";
        String nonce_str = "ibuaiVcKdpRxkhJA";                  //16位
        String key = "192006250b4c09247ec02edce69f6a2d";  //32位
        //将参数以参数名字典升序排序   拼接TreeMap 默认:key升序排序
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("device_info", device_info);
        parameters.put("body", body);
        parameters.put("nonce_str", nonce_str);
        // 执行,测试
        String sign = creatSign(parameters, key);
        System.out.println("sign: " + sign);
    }

    public static void main(String[] args) {
        Sign();
        //param: appid=.&body=.&device_info=.&mch_id=.&nonce_str=.&
        //+key:  appid=.&body=.&device_info=.&mch_id=.&nonce_str=.192006250b4c09247ec02edce69f6a2d
        //sign: 559c38432f72a2aebb96328109843601
    }

}


