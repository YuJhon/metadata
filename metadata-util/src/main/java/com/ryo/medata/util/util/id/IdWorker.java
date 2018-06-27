/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.medata.util.util.id;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/15 下午3:41  </pre>
 * <pre> Project: lombok-ex  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public final class IdWorker {

    /**
     * 主机和进程的机器码
     */
    private static final Sequence worker = new Sequence();


    public static long nextId() {
        return worker.nextId();
    }

}
