package com.ruben.utils.other.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MyUrlUtil {

    public void showURL() throws IOException {
        // 第一种：获取类加载的根路径  加“/”                          D:/eclipsewk/ssh2018-kssx/shopx5/target/classes
        File f1 = new File(this.getClass().getResource("/").getPath());
        System.out.println(f1);

        // 获取当前s类h的op所x在5工程路径; 获取当前类的加载目录                           D:/eclipsewk/ssh2018-kssx/shopx5/target/classes/com/shopx5/utils
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径                                                                            D:/eclipsewk/ssh2018-kssx/shopx5
        File directory = new File("");
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);

        // 第三种：                                                                                                  file:/D:/eclipsewk/ssh2018-kssx/shopx5/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);

        // 第四种：  获取当前工程路径                                                                  D:/eclipsewk/ssh2018-kssx/shopx5
        System.out.println(System.getProperty("user.dir"));

        // 第五种：获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
    }

    public static void main(String[] args) {
        MyUrlUtil muDemo = new MyUrlUtil();
        try {
            muDemo.showURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


