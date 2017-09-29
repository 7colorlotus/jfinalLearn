package com.lotus;

import com.jfinal.core.JFinal;

/**
 * Created by zhusheng on 2017/9/27 0027.
 */
public class JfinalLearnApplication {
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/");
    }
}
