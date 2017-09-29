package com.lotus.web.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.lotus.entitiy.User;

public class IndexController extends Controller {
    Log log = Log.getLog(IndexController.class);

    public void index() {
        renderText("Hello JFinal World.");
    }

    /**
     * 添加了jfinal-java8依赖后，可以在Action方法中加形参
     *
     * @param user
     */
    public void save(User user) {
        user.save();
        render("index.html");
    }

    public void testGetPara() {
        String param1 = getPara("param1");
        String param2 = getPara("param2");
        String param = getPara();
        log.info("param1:" + param1);
        log.info("param2:" + param2);
        renderText("param1:" + param1+"param2:" + param2+",param:"+param);
    }
}