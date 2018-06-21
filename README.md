# MetaData

元数据管理小工具。

作用：可以自动将所有的**表信息和字段信息存入元数据表**中，便于统一查阅。(注释需要保证库本身已经包含了对于表和字段的注释)

## 方式

用于为数据库提供两张表。 `meta_field`, `meta_model`。

| 表名称 | 说明 | 
|:----|:----|
| meta_model | 用于存放所有的表信息。(包含元数据表) |
| meta_field | 用于存放所有的表字段信息。(包含元数据表) |

[MYSQL DLL](https://github.com/houbb/metadata/blob/master/metadata-core/src/main/resources/coreMysql.sql)

[SQL SERVER DLL](https://github.com/houbb/metadata/blob/master/metadata-core/src/main/resources/coreSqlServer.sql)

[ORACLE DLL](https://github.com/houbb/metadata/blob/master/metadata-core/src/main/resources/coreOracle.sql)

# 变更日志

> [变更日志](ChangeLog.md)

# 环境

## 支持数据库

| 数据库 | 是否支持 | 测试版本 | 
|:----|:----|:-----|
| [MySQL](https://www.mysql.com/) | YES | 5.5, 5.6 |
| [SQL Server](https://www.microsoft.com/en-us/sql-server/) | YES | 2008 R2 |
| [Oracle](https://www.oracle.com/index.html) | NO | |
| [MongoDB](https://www.mongodb.com/) | NO | |

备注：测试条件限制，只对手中的数据库版本进行了测试验证。

问题：有没有一种环境，可以模拟各个版本的数据库？

- 思考

数据库的种类繁多，不胜枚举。应该为每一个数据库提供好可覆写的方法。脚本分类提前留好地方。

不同的数据库脚本是不同的。可以使用 PowerDesigner 进行统一生成设计。(mac DbWrench Database)

## JDK

测试为 [Jdk1.7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html), 
[JDK1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

# 快速开始

## Before Start

你需要熟悉 maven，tomcat，jdk 

## Download

- 方式一

直接点击下载本项目：[https://github.com/houbb/metadata](https://github.com/houbb/metadata)。

- 方式二

使用 [Git](https://git-scm.com/) 直接签入到本地：

```
git clone https://github.com/houbb/metadata
```

## 编译

将下载的项目用编辑器打开。推荐 [IDEA](https://www.jetbrains.com/idea/)

本项目 jar 管理使用 [Maven](https://maven.apache.org/)，请确保已正确配置。

在根目录运行命令打包：

```
mvn clean install
```

## 部署运行

有两种方式：

- tomcat

将编译生成的 `war` 部署到 [Tomcat](http://tomcat.apache.org/) 运行。

- tomcat7 plugin(推荐)

运行模块 [metadata-web](https://github.com/houbb/metadata/blob/master/metadata-web/pom.xml) 下的 
[maven tomcat7](http://tomcat.apache.org/maven-plugin-2.0-beta-1/) 插件。

## 访问

默认的 tomcat7 plugin 端口号为 18082。

任意浏览器打开 [http://localhost:18082/](http://localhost:18082/) 即可。


# 使用方式

## MySql 使用方式

在 [mysql](http://localhost:18082/metadata/mysql) 页面指定数据库链接信息，点击【Execute】按钮即可。

- 属性说明

| 属性| 描述 | 默认值 |
|:----|:----|:----|
| Host        | 地址              | localhost | 
| Port        | 端口号             | 3306 | 
| DataBase    | 数据库名称   | |
| Username    | 用户名称 | root |
| Password    | 密码 | 123456 |

![metadata-mysql.png](metadata-mysql.png)


## 测试案例

### 测试前

- 测试脚本

```sql
CREATE DATABASE `metadata-test`
  DEFAULT CHARACTER SET UTF8;
USE `metadata-test`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户表';
```

原始数据如下：

```
mysql> show tables;
+----------------+
| Tables_in_metadata-test |
+----------------+
| user           |
+----------------+
```

### 执行脚本后

```
mysql> show tables;
+-------------------------+
| Tables_in_metadata-test |
+-------------------------+
| meta_field              |
| meta_model              |
| user                    |
+-------------------------+
```

- meta_model 内容

```
mysql> SELECT * FROM meta_model \G;
*************************** 1. row ***************************
          ID: 1
         uid: cdae6d5ab5bd4e8fac974e6cbfb0d9c1
        name: meta_field
dbObjectName: NULL
       alias: NULL
 description: 元数据字段表
    category: NULL
   isVisible: 0
  isEditable: 0
  createTime: 2018-01-18 09:48:20
  updateTime: 2018-01-18 09:48:20
*************************** 2. row ***************************
          ID: 2
         uid: 0869811946e34d9bae22d2ac7940a8d7
        name: meta_model
dbObjectName: NULL
       alias: NULL
 description: 元数据实体表
    category: NULL
   isVisible: 0
  isEditable: 0
  createTime: 2018-01-18 09:48:20
  updateTime: 2018-01-18 09:48:20
*************************** 3. row ***************************
          ID: 3
         uid: c94524236260483e9494aa6e5596a787
        name: user
dbObjectName: NULL
       alias: NULL
 description: 用户表
    category: NULL
   isVisible: 0
  isEditable: 0
  createTime: 2018-01-18 09:48:20
  updateTime: 2018-01-18 09:48:20
3 rows in set (0.01 sec)
```



## SQL Server 的使用方式

和上面类似。

在 [sql server](http://localhost:18082/metadata/sql-server) 页面指定数据库链接信息，点击【Execute】按钮即可。

- 属性说明

| 属性| 描述 | 默认值 |
|:----|:----|:----|
| Host        | 地址 | localhost | 
| Port        | 端口号 | 1433 | 
| DataBase    | 数据库名称   | |
| Username    | 用户名称 | sa |
| Password    | 密码 | 123456 |


## Oracle 的使用方式

和上面类似。

在 [oracle](http://localhost:18082/metadata/oracle) 页面指定数据库链接信息，点击【Execute】按钮即可。

- 属性说明

| 属性| 描述 | 默认值 |
|:----|:----|:----|
| Host        | 地址 | localhost | 
| Port        | 端口号 | 49161 | 
| Service    | oralce 服务名称   | XE |
| Username    | 用户名称 | system |
| Password    | 密码 | 123456 |