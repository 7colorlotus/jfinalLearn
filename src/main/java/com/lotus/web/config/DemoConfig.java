package com.lotus.web.config;

import com.jfinal.config.*;
import com.jfinal.template.Engine;
import com.lotus.web.controller.front.IndexController;
import com.lotus.web.interceptor.FrontInterceptor;

public class DemoConfig extends JFinalConfig {
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }

    public void configRoute(Routes me) {
        me.setBaseViewPath("/view");
        me.addInterceptor(new FrontInterceptor());
        me.add("/hello", IndexController.class);
    }

    public void configEngine(Engine me) {
    }

    public void configPlugin(Plugins me) {
    }

    public void configInterceptor(Interceptors me) {
    }

    public void configHandler(Handlers me) {
    }
}