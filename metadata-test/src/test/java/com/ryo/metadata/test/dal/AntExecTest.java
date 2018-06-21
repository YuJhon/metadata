package com.ryo.metadata.test.dal;

import com.ryo.metadata.core.constant.DriverNameConstant;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.junit.Test;

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

    @Test
    public void sqlServerTest() {
        SQLExec sqlExec = new SQLExec();

        //设置数据库参数
        sqlExec.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        sqlExec.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=test");
        sqlExec.setUserid("sa");
        sqlExec.setPassword("hydb001*");

        //要执行的脚本
        sqlExec.setSrc(new File("D:\\CODE\\metadata\\metadata-core\\src\\main\\resources\\coreSqlServer.sql"));

        //如果有出错的语句继续执行.
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
        sqlExec.execute();
    }

    @Test
    public void oracleTest() {
        SQLExec sqlExec = new SQLExec();

        //设置数据库参数
        sqlExec.setDriver(DriverNameConstant.ORACLE);
        sqlExec.setUrl("jdbc:oracle:thin:@127.0.0.1:49161:XE");
        sqlExec.setUserid("system");
        sqlExec.setPassword("123456");

        //要执行的脚本
        sqlExec.setSrc(new File("/Users/houbinbin/code/_github/metadata/metadata-core/src/main/resources/coreOracle.sql"));

        //如果有出错的语句继续执行.
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
        sqlExec.execute();
    }


//    jdbc.driverClassName=oracle.jdbc.OracleDriver
//    jdbc.url=jdbc:oracle:thin:@127.0.0.1:49161:XE
//    jdbc.username=system
//    jdbc.password=123456
//
//    jdbc.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
//    jdbc.url=jdbc:sqlserver://localhost:1433;DatabaseName=doc
//    jdbc.username=sa
//    jdbc.password=123456


    //1. 读取 inputStream

    //2. 创建临时文件

    //3. 执行 SQL 脚本

    //4. 删除文件

}
