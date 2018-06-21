/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.medata.util.util;

import java.util.UUID;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/21 下午7:57  </pre>
 * <pre> Project: metadata  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class IDUtil {

    /**
     * 32 位随机编码
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
