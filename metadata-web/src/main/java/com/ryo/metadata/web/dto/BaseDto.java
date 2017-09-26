package com.ryo.metadata.web.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author houbinbin
 * @version 1.0
 * @on 2017/9/7
 * @since 1.7
 */
public abstract class BaseDto implements Serializable {

    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        } catch (Exception var2) {
            var2.printStackTrace();
            return super.toString();
        }
    }

}
