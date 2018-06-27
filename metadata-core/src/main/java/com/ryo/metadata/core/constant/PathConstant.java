/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.constant;

/**
 * 脚本路径常量
 *
 * @author bbhou
 * @date 2017/8/2
 */
public interface PathConstant {

    /**
     * 脚本
     */
    interface Sql {
        /**
         * mysql
         */
         String MYSQL = "sql/mysql.sql";

        /**
         * oracle
         */
         String ORACLE = "sql/oracle.sql";

        /**
         * sql server
         */
         String SQL_SERVER = "sql/sqlServer.sql";
    }

    /**
     * 关键字
     */
    interface Keyword {
        /**
         * mysql
         */
        String MYSQL = "keyword/mysql.txt";

        /**
         * oracle
         */
        String ORACLE = "keyword/oracle.txt";

        /**
         * sql server
         */
        String SQL_SERVER = "keyword/sqlServer.txt";
    }

}
