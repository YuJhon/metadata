package com.ryo.metadata.test.util;

import com.ryo.metadata.core.util.SingletonUtil;
import com.ryo.metadata.test.service.UserService;
import org.junit.Test;

/**
 * Created by bbhou on 2017/8/2.
 */
public class SingletonUtilTest {

    @Test
    public void singleTest() {
        UserService userService = SingletonUtil.getSingleInstance(UserService.class);
        userService.showName();

        UserService userServiceTwo = SingletonUtil.getSingleInstance(UserService.class);
        userServiceTwo.showName();

        System.out.println(userService == userServiceTwo);
    }

}
