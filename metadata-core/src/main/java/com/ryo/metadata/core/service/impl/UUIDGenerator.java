package com.ryo.metadata.core.service.impl;

import com.ryo.metadata.core.service.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

/**
 * Created by bbhou on 2017/8/2.
 */
public class UUIDGenerator implements IdGenerator {

    private static final Logger LOGGER = LogManager.getLogger(UUIDGenerator.class);

    @Override
    public String genId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        LOGGER.debug("genId: "+uuid);
        return uuid;
    }

}
