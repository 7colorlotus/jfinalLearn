package com.lotus.web.config;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByActionKeyRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.lotus.entitiy.User;
import com.lotus.web.controller.front.IndexController;
import com.lotus.web.handler.ResourceHandler;
import com.lotus.web.interceptor.AuthInterceptor;
import com.lotus.web.routes.AdminRoutes;
import com.lotus.web.routes.FrontRoutes;

/**
 * JFinal配置入口类，必须在web.xml中指定为过滤器的configClass初始参数值才能起效果
 */
public class MyJFinalConfig extends JFinalConfig {
    public void configRoute(Routes me) {
        me.add(new FrontRoutes()); // 前端路由
        me.add(new AdminRoutes()); // 后端路由
    }

    public void configConstant(Constants me) {
        /*me.setBaseUploadPath("F:\\upload");*/
    }

    public void configEngine(Engine me) {
        /*me.addSharedFunction("/_view/common/__layout.html");
        me.addSharedFunction("/_view/common/_paginate.html");
        me.addSharedFunction("/_view/common/__admin_layout.html");*/
    }

    public void configPlugin(Plugins me) {
        loadPropertyFile("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl")
                                            ,getProperty("user")
                                            ,getProperty("password"));
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        arp.addMapping("user", User.class);
    }

    public void configInterceptor(Interceptors me) {
        me.add(new TxByActionKeyRegex("/testDeclareTx"));
        me.add(new AuthInterceptor());
    }

    public void configHandler(Handlers me) {
        /*me.add(new ResourceHandler());*/
    }
}