/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.util;

import com.ryo.medata.util.util.DateUtil;
import com.ryo.medata.util.util.MapUtil;
import com.ryo.medata.util.util.StringUtil;
import com.ryo.metadata.core.dal.JdbcMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * 数据库 SQL 工具类
 *
 * @author bbhou
 * @date 2017/8/1
 */
public class DatabaseSqlUtil {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseSqlUtil.class);

    /**
     * 清空一张表
     * @param tableName 表名称
     * @return SQL
     */
    public static String truncateTable(String tableName) {
        return String.format("TRUNCATE TABLE %s;", tableName);
    }

    /**
     * 插入
     * 1. 将对象直接插入到对应的表中。
     * insert into _table (field_a, field_b) VALES (value_a, value_b);
     * todo: 优化支持多条。
     * @param jdbcMapper jdbc mapper
     * @param tableName 表名称
     * @param object 对象信息
     * @param fieldsMapping 字段信息
     * @return sql
     */
    public static String insert(JdbcMapper jdbcMapper,
                                String tableName, Object object, Map<String, String> fieldsMapping) throws IllegalAccessException {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ").append(tableName);
        String fields = buildFields(jdbcMapper, object, fieldsMapping);
        sqlBuilder.append(fields).append(";");
        return sqlBuilder.toString();
    }

    /**
     * 更新
     * update _table SET field_a=value_a, field_b=value_b [where ...]
     * @param tableName
     * @param object
     * @param whereClause
     * @return
     */
    public static String update(String tableName, Object object, String whereClause) {
        return "";
    }

    /**
     * 删除
     * @param tableName
     * @param whereClause
     * @return
     */
    public static String delete(String tableName, String whereClause) {
        return "";
    }


    /**
     * 构建字段信息
     * @param jdbcMapper jdbc 信息
     * @param object 对象信息
     * @param fieldsMapping 字段映射
     * @return 字段拼接
     */
    public static String buildFields(JdbcMapper jdbcMapper,
                                     Object object,
                                     Map<String, String> fieldsMapping) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder fieldsBuilder = new StringBuilder("( ");
        StringBuilder valuesBuilder = new StringBuilder("( ");
        for(Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue = field.get(object);

            //1. null 校验
            if(null == fieldValue) {
                continue;
            }

            Class fieldType = field.getType();
            String tableFieldName = getTableFieldName(fieldName, fieldsMapping);
            String fieldValueStr = getFieldValueStr(fieldType, fieldValue);

            String actualFieldName = jdbcMapper.getColumn(tableFieldName);
            fieldsBuilder.append(actualFieldName).append(", ");
            valuesBuilder.append("'").append(fieldValueStr).append("', ");
        }

        //2. 去掉多余的 ,
        fieldsBuilder.deleteCharAt(fieldsBuilder.lastIndexOf(","));
        valuesBuilder.deleteCharAt(valuesBuilder.lastIndexOf(","));
        fieldsBuilder.append(" )");
        valuesBuilder.append(" )");

        return String.format("%s VALUES %s", fieldsBuilder.toString(), valuesBuilder.toString());
    }

    /**
     * 获取数据库中对应的字段名称
     * @param fieldName 字段名称
     * @param fieldsMapping 字段映射。
     * @return
     */
    private static String getTableFieldName(String fieldName, Map<String, String> fieldsMapping) {
        if(MapUtil.isNotEmpty(fieldsMapping)) {
            String tableMapping = fieldsMapping.get(fieldName);
            if(StringUtil.isNotEmpty(tableMapping)) {
                return tableMapping;
            }
        }
        return fieldName;
    }

    /**
     * 获取字段值对应的字符串形式
     * @param fieldType
     * @param fieldValue
     * @return
     */
    private static String getFieldValueStr(Class fieldType, Object fieldValue) {
        //1. date
        if(Date.class.equals(fieldType)) {
            return DateUtil.getDateStr((Date) fieldValue);
        }
        //2. boolean
        if(fieldType == Boolean.class
                || fieldType == boolean.class) {
            if((Boolean) fieldValue) {
                return "1";
            } else {
                return "0";
            }
        }

        return String.valueOf(fieldValue);
    }


}
