package com.ruben.utils.other.utils;

public interface Constant {
    /**
     * 用户激活状态
     */
    int USER_IS_ACTIVE = 1;
    /**
     * 勾选了自动登录
     */
    String IS_AUTO_LOGIN = "ok";
    /**
     * 勾选了记住用户名
     */
    String IS_SAVE_NAME = "ok";

    String CURRENT_USER_INFO = "_CURRENT_USER";
    /**
     * 过滤器 过滤路径 正则
     */
    String NO_INTERCEPTOR_PATH = "(.*)/[(toMain)(dept)(user)(role)(module)](.*)";
}
