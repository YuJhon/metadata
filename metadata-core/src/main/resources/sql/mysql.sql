drop table if exists meta_field;

drop table if exists meta_model;

/*==============================================================*/
/* Table: meta_field                                            */
/*==============================================================*/
create table meta_field
(
   ID                   int not null auto_increment comment '自增长主键',
   uid                  varchar(36) comment '唯一标识',
   name                 varchar(125) comment '名称',
   dbObjectName         varchar(36) comment '数据库表名',
   alias                varchar(125) comment '别名',
   description          varchar(255) comment '描述',
   isNullable           bool comment '是否可为空',
   dataType             varchar(36) comment '数据类型',
   createTime           datetime comment '创建时间',
   updateTime           datetime comment '更新时间',
   primary key (ID)
)
auto_increment = 1000;

alter table meta_field comment '元数据字段表';

/*==============================================================*/
/* Table: meta_model                                            */
/*==============================================================*/
create table meta_model
(
   ID                   int not null auto_increment comment '自增长主键',
   uid                  varchar(36) comment '唯一标识',
   name                 varchar(125) comment '名称',
   dbObjectName         varchar(36) comment '数据库表名',
   alias                varchar(125) comment '别名',
   description          varchar(255) comment '描述',
   category             varchar(36) comment '分类',
   isVisible            bool comment '是否可查询',
   isEditable           bool comment '是否可编辑',
   createTime           datetime comment '创建时间',
   updateTime           datetime comment '更新时间',
   primary key (ID)
)
auto_increment = 1000;

alter table meta_model comment '元数据实体表';
