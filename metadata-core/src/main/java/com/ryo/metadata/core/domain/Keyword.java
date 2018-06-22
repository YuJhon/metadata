/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.domain;

/**
 * 关键字处理
 * 1. 对于不同的数据库，关键字需要做特殊的处理
 * @author bbhou
 * @date 2017/8/1
 */
public class Keyword {

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 是否为保留字
     */
    private boolean reserved;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
