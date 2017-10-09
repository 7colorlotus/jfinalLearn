package com.lotus.web.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lotus.entitiy.User;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * 测试请求参数
     */
    public void testGetPara() {
        String param1 = getPara("param1");
        String param2 = getPara("param2");
        String param = getPara();
        log.info("param1:" + param1);
        log.info("param2:" + param2);
        renderText("param1:" + param1 + "param2:" + param2 + ",param:" + param);
    }


    /**
     * 测试数据库操作
     */
    public void testDb() {
        new User().set("name", "Lotus").set("age", 29).save();

        //根据id删除记录
        User.dao.deleteById(1);

        //查询id值为2的User将其name属性改为James并更新到数据库
        User.dao.findById(2).set("name", "James").update();

        User user = User.dao.findByIdLoadColumns(2, "name,age");
        String username = user.getStr("name");
        Integer userage = user.getInt("age");
        log.info("username:" + username + ",userage:" + userage);

        Page<Record> userPage = Db.paginate(1, 10, "select *", "from user where age > ?", 18);
        List<Record> recordList = userPage.getList();
        for (Record record : recordList) {
            String tempName = record.getStr("name");
            Integer tempAge = record.getInt("age");
            System.out.println("tempName:" + tempName + ",tempAge:" + tempAge);
        }

        boolean succeed = Db.tx(new IAtom() {
            public boolean run() throws SQLException {
                int count = Db.update("update user set name=? where id=?", "Jacky", 3);
                int count2 = Db.update("update user set name=? where id=?", "Tom", 4);
                return count == 1 && count2 == 1;
            }
        });
        System.out.println("succeed:" + succeed);

        renderText("testDb success!!");
    }
}