package com.lotus.web.controller.admin;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * Created by zhusheng on 2017/9/27 0027.
 */
public class UserController extends Controller{
    @ActionKey("login")
    public void login(){
        render("login.html");
    }
}
