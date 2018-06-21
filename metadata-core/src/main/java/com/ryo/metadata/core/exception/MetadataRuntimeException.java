/*
 * Copyright (c)  2018. houbinbin Inc.
 * metadata All rights reserved.
 */

package com.ryo.metadata.core.exception;

/**
 * <p> </p>
 *
 * <pre> Created: 2018/6/21 下午8:38  </pre>
 * <pre> Project: metadata  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class MetadataRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -8015935221126893221L;

    public MetadataRuntimeException() {
    }

    public MetadataRuntimeException(String message) {
        super(message);
    }

    public MetadataRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetadataRuntimeException(Throwable cause) {
        super(cause);
    }

    public MetadataRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
