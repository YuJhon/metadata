# MetaData

元数据。用于为数据库提供两张表。 meta_field, meta_model

- meta_model

用于存放所有的表信息。(包含元数据表)

- meta_field

用于存放所有的表字段信息。(包含元数据表)

作用：可以自动将所有的表信息和字段信息存入对应的表中。(注释需要保证库本身已经包含了对于表和字段的注释)

# 支持数据库

1. mysql （√）

2. sql server（√）

3. oracle

4. mongodb

数据库的种类繁多，不胜枚举。应该为每一个数据库提供好可覆写的方法。脚本分类提前留好地方。

不同的数据库脚本是不同的。可以使用 powerDesigner 进行统一生成设计。(mac DbWrench Database)

# 表结构的设计

见脚本

- data_table

- data_field

# 文档说明

(本项目依赖于maven)

## MySql 使用方式

- 执行脚本

```
${ROOT}\metadata-test\src\main\resources\sql\mysql\init.sql
```

- 配置数据库连接

```
${ROOT}\metadata-test\src\main\resources\jdbc_mysql.properties
```

- 运行测试案例

```
${ROOT}\metadata-test\src\test\java\com\ryo\metadata\test\core\service\MySqlDBServiceTest.java
```

## SQL Server 的使用方式

- lib 

首先将 lib 文件下的 sqljdbc4.jar 文件加在到项目中。后面与 mysql 一致。

- 执行脚本

```
${ROOT}\metadata-test\src\main\resources\sql\sqlserver\init.sql
```

- 配置数据库连接

```
${ROOT}\metadata-test\src\main\resources\jdbc_mysql.properties
```

- 运行测试案例

```
${ROOT}\metadata-test\src\test\java\com\ryo\metadata\test\core\service\SqlServerDBServiceTest.java
```


# TODO

- 触发器

当添加字段时候，自动更新对应 metadata 表信息。

- 数据库连接池

为提高性能，手写个数据库连接池。

- 同时执行多条SQL

为简化调用，同时执行多条SQL;

