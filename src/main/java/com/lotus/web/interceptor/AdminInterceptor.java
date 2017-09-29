package com.lotus.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Created by zhusheng on 2017/9/27 0027.
 */
public class AdminInterceptor implements Interceptor {

    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
