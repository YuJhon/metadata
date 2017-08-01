package com.ryo.metadata.test.util;

import com.ryo.medata.util.util.PathUtil;
import org.junit.Test;

/**
 * Created by bbhou on 2017/8/1.
 */
public class PathUtilTest {

    @Test
    public void pathTest() {
        System.out.println(PathUtil.getPath());
        System.out.println(PathUtil.getRootPath());
//        System.out.println(PathUtil.getWebRootPath());
    }
}
