package com.lotus.web.routes;

import com.jfinal.config.Routes;
import com.lotus.web.controller.front.BlogController;
import com.lotus.web.controller.front.IndexController;

/**
 * Created by zhusheng on 2017/9/27 0027.
 */
public class FrontRoutes extends Routes {
    public void config() {
        setBaseViewPath("/view/front");
        add("/", IndexController.class);
        add("/blog", BlogController.class);
    }
}
