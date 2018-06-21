/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.web.support.jdbc;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/20 下午4:07  </pre>
 * <pre> Project: metadata  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public interface IJdbc {

    /** 构建 JDBC 信息
     * @param host     服务地址
     * @param port     端口号
     * @param database 数据库
     * @param username 用户名称
     * @param password 密码
     * @throws Exception 执行异常
     */
    void execute(final String host, final String port,
                 final String database, final String username,
                 final String password) throws Exception;

}
