package com.lotus.web.routes;

import com.jfinal.config.Routes;
import com.lotus.web.controller.admin.AdminController;
import com.lotus.web.controller.admin.UserController;
import com.lotus.web.interceptor.AdminInterceptor;

/**
 * Created by zhusheng on 2017/9/27 0027.
 */
public class AdminRoutes extends Routes {
    public void config() {
        setBaseViewPath("/view/admin");
        addInterceptor(new AdminInterceptor());
        add("/admin", AdminController.class);
        add("/admin/user", UserController.class);
    }
}
