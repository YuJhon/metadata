package com.ryo.metadata.core.util;

import com.ryo.medata.util.util.FileUtil;

/**
 *
 * 彻底搞清楚 web 项目中路径问题。
 * Created by bbhou on 2017/9/26.
 */
public class SqlPathUtil {

    private static String MYSQL_PATH = "";

    private static String SQL_SERVER_PATH = "";

    static {
        String getPath = getPath();

        String URIPath = SqlPathUtil.class.getResource("/").toString();
        String URIPath2 = SqlPathUtil.class.getResource("").toString();
        String URIPath3 = SqlPathUtil.class.getResource("/sql/mysql.sql").toString();
        String URIPath4 = SqlPathUtil.class.getResource("/sql/mysql.sql").toString();
//
//
        String fileURL = null;
            fileURL = SqlPathUtil.class.getResource("/sql/mysql.sql").getPath();

        String content = FileUtil.getFileContent(fileURL);
//        System.out.println(">>>>>>>>> content" + content);
//        System.out.println(URIPath);


        String path = fileURL.substring(5);

//        file:/Users/houbinbin/IT/tools/tomcat/tomcat8/webapps/ROOT/WEB-INF/lib/metadata-core-1.0-SNAPSHOT.jar!/sql/mysql.sql


//            URL mysqlUrl = SqlPathUtil.class.getResource("sql/mysql.sql");
//            URL sqlServerUrl = SqlPathUtil.class.getResource("sql/sqlServer.sql");
//        try {
//            MYSQL_PATH = SqlPathUtil.class.getResource("/sql/mysql.sql").getPath();
//            SQL_SERVER_PATH = SqlPathUtil.class.getResource("/sql/sqlServer.sql").getPath();
//
//
//            String tempPath = SqlPathUtil.class.getResource("").toURI().getPath();
//
//            System.out.println(tempPath);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }



//        MYSQL_PATH = getPath() + "/src/main/resources/sql/mysql.sql";
//        SQL_SERVER_PATH = getPath() + "/src/main/resources/sql/sqlServer.sql";

    }

    public static String getMysqlPath() {

        return MYSQL_PATH;
    }

    public static String getSqlServerPath() {
        return SQL_SERVER_PATH;
    }

    public static String getPath() {
        return System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        String string ="file:/Users/houbinbin/IT/tools/tomcat/tomcat8/webapps/ROOT/WEB-INF/lib/metadata-core-1.0-SNAPSHOT.jar!/sql/mysql.sql";
        System.out.println(string.substring(5));
        System.out.println(FileUtil.getFileContent(string.substring(5)));
//        System.out.println(getMysqlPath());
//        System.out.println(getSqlServerPath());

        //编译后的项目路径

//        /Users/houbinbin/IT/fork/metadata/metadata-web/target/metadata-web.war

//        /Users/houbinbin/IT/fork/metadata/metadata-core/target/classes/sql/mysql.sql


//        String path = SqlPathUtil.class.getClassLoader().getResource("/sql/mysql.sql").getPath();
//        System.out.println(path);

//        System.out.println(SqlPathUtil.class.getResource("/sql/mysql.sql").getPath());


//        final String path = "/Users/houbinbin/IT/tools/tomcat/tomcat8/webapps/ROOT/WEB-INF/lib/metadata-core-1.0-SNAPSHOT.jar!/sql/mysql.sql";
//        System.out.println(FileUtil.getFileContent(path));


//        s
    }
}
