package com.ryo.metadata.core.domain;

import java.util.Date;

/**
 * 原生字段
 *
 * @author bbhou
 * @date 2017/7/31
 */
public class MetaField {

    /**
     * 唯一标识
     */
    private String uid;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 数据库名称
     */
    private String dbObjectName;

    /**
     * 字段描述
     */
    private String description;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 是否可以为空
     */
    private boolean isNullable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDbObjectName() {
        return dbObjectName;
    }

    public void setDbObjectName(String dbObjectName) {
        this.dbObjectName = dbObjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MetaField{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", dbObjectName='" + dbObjectName + '\'' +
                ", description='" + description + '\'' +
                ", dataType='" + dataType + '\'' +
                ", isNullable=" + isNullable +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
