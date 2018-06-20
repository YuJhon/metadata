package com.ryo.metadata.core.util;

import com.ryo.metadata.core.util.vo.JdbcVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * 数据库脚本执行工具类
 * @author houbinbin
 * @see MybatisSqlExecUtil 通过 mybatis 的实现。
 */
@Deprecated
public class SqlExecUtil {

    private static final Logger LOGGER = LogManager.getLogger(SqlExecUtil.class);

    /**
     * 执行
     *
     * @param jdbcVo      数据库连接信息
     * @param inputStream 待执行的脚本信息
     */
    public static void execute(JdbcVo jdbcVo, InputStream inputStream) throws Exception {
        //1. 读取 inputStream
        byte[] bytes = readStream(inputStream);

        //2. 创建临时文件
        File tempFile = File.createTempFile("tempSql", ".sql");
        Files.write(tempFile.toPath(), bytes);

        //3. 执行 SQL 脚本
        SQLExec sqlExec = new SQLExec();

        //设置数据库参数
        sqlExec.setDriver(jdbcVo.getDriverClassName());
        sqlExec.setUrl(jdbcVo.getUrl());
        sqlExec.setUserid(jdbcVo.getUsername());
        sqlExec.setPassword(jdbcVo.getPassword());

        //要执行的脚本
        sqlExec.setSrc(tempFile);

        //如果有出错的语句继续执行.
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "continue")));
        sqlExec.setPrint(true);
        // 要指定这个属性，不然会出错
        sqlExec.setProject(new Project());
        sqlExec.execute();

        //4. 删除文件
        if(tempFile.exists()) {
            boolean isDelete = tempFile.delete();
            LOGGER.info("Temp file delete result: " + isDelete);
        }
    }


    /**
     * TODO: 这段代码需要优化。
     * @param inStream
     * @return
     * @throws Exception
     */
    private static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

}
