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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Keyword keyword1 = (Keyword) o;

        if (reserved != keyword1.reserved) {
            return false;
        }
        return keyword != null ? keyword.equals(keyword1.keyword) : keyword1.keyword == null;
    }

    @Override
    public int hashCode() {
        int result = keyword != null ? keyword.hashCode() : 0;
        result = 31 * result + (reserved ? 1 : 0);
        return result;
    }
}
