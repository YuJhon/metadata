package com.ryo.medata.util.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by houbinbin on 16/5/25.
 */
public class PathUtil {

    public static String getPath() {
        return System.getProperty("user.dir");
    }

    public static String getRootPath() {
        return Class.class.getClass().getResource("/").getPath();
    }

    public static String getWebRootPath() {
        String path = PathUtil.class.getResource("").toString();
        path = path.substring(0, path.indexOf("target/classes/com/ryo/util/"));
        String realPath = null;
        try {
            realPath = (new File((new URL(path)).toURI())).toString();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return realPath + File.separator;
    }

}


