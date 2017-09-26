package com.ryo.metadata.test.dal;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import java.io.File;

/**
 * 使用 ant 解析 SQL 脚本
 * Created by bbhou on 2017/9/26.
 */
public class AntExecTest {

    public static void main(String[] args) {
        SQLExec sqlExec = new SQLExec();

        //设置数据库参数
        sqlExec.setDriver("com.mysql.jdbc.Driver");
        sqlExec.setUrl("jdbc:mysql://127.0.0.1:3306/doc?useUnicode=true&characterEncoding=UTF-8&useOldAlias");
        sqlExec.setUserid("root");
        sqlExec.setPassword("123456");

        //要执行的脚本
        sqlExec.setSrc(new File("D:\\CODE\\metadata\\metadata-test\\src\\main\\resources\\sql\\mysql\\init.sql"));

        //如果有出错的语句继续执行.
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "continue")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
        sqlExec.execute();
    }

}
