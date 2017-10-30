package com.lotus.web.controller.front;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.json.Json;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
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
     *  Db + Record  模式
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


        //编程事务
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

    /**
     * 测试查询数据库分页
     */
    public void testDbPaginate(){
        User dao = new User();
        //普通分页查询
        Page<User> users =  dao.paginate(1,10,"select *", "from user where age > ?",18);

        //带有group by 的分页查询
        users =  dao.paginate(1,10,true,"select *", "from user where age > ? group by age",18);

        //带有统计总数 的分页查询

        String from = "from user where age > ? ";
        String totalRowSql = "select count(*) " + from;
        String findSql = "select * " + from + " order by age";

        users =  dao.paginateByFullSql(1,10,totalRowSql, findSql,18);

        renderText("pageNum: " + users.getPageNumber()
                +",pageSize: " + users.getPageSize()
                +",pageValue: " + Json.getJson().toJson(users.getList())
                +",pageValueSize: " + users.getList().size()
                +",totalRow: " + users.getTotalRow()
        );

    }

    /**
     * 测试声明式事务
     */
//    @Before(Tx.class)
    public void testDeclareTx(){
        Integer transAmt = getParaToInt("transAmt");
        Integer fromUserId = getParaToInt("fromUserId");
        Integer toUserId = getParaToInt("toUserId");

        Db.update("update user set cash = cash - ? where id = ?", transAmt, fromUserId);
        System.out.println(1/0);
        Db.update("update user set cash = cash + ? where id = ?", transAmt, toUserId);

        renderText("testDeclareTx success");
    }

}