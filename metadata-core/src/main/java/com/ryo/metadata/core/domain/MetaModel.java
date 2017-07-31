package com.ryo.metadata.core.domain;

import java.util.Date;

/**
 * 原生-实体类
 * Created by bbhou on 2017/7/31.
 */
public class MetaModel {

    /**
     * 唯一标识
     */
    private String uid;

    /**
     * 实体名称
     */
    private String name;

    /**
     * 实体别名
     */
    private String alias;

    /**
     * 实体对应数据库表
     */
    private String dbObjectName;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类
     */
    private String category;

    /**
     * 是否可见
     */
    private boolean isVisible;

    /**
     * 是否可编辑
     */
    private boolean isEditable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
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
        return "MetaModel{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", dbObjectName='" + dbObjectName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", isVisible=" + isVisible +
                ", isEditable=" + isEditable +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
