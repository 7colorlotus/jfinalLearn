package com.lotus.entitiy;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by zhusheng on 2017/9/27 0027.
 */
public class User extends Model<User> {
    public static final User dao = new User().dao();


}
