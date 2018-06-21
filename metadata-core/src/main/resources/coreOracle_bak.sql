/*==============================================================*/
/* 清空原来的表信息                                            */
/*==============================================================*/
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('meta_field');
    if num > 0 then
        execute immediate 'drop table meta_field' ;
    end if;
end;

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('meta_model');
    if num > 0 then
        execute immediate 'drop table meta_model' ;
    end if;
end;


/*==============================================================*/
/* Table: meta_field                                            */
/*==============================================================*/
create table meta_field
(
  ID           int           not null primary key,
  "uid"          varchar(36),
  name          varchar(125),
  dbObjectName        varchar(64),
  "alias" varchar(255),
  description varchar(255),
  isNullable        NUMBER(1),
  dataType          varchar(36),
  createTime   date,
  updateTime   date
)
/

comment on column meta_field.ID
is '物理主键'
/

comment on column meta_field."uid"
is '唯一标识'
/

comment on column meta_field.name
is '名称'
/

comment on column meta_field.dbObjectName
is '数据库表名'
/

comment on column meta_field."alias"
is '别名'
/

comment on column meta_field.description
is '字段描述'
/

comment on column meta_field.isNullable
is '是否可以为空'
/

comment on column meta_field.dataType
is '数据类型'
/

comment on column meta_field.createTime
is '创建时间'
/

comment on column meta_field.updateTime
is '更新时间'
/

/*==============================================================*/
/* Table: meta_model                                            */
/*==============================================================*/
create table meta_model
(
   ID                   int not null primary key,
   "uid"                  varchar(36),
   name                 varchar(125),
   dbObjectName         varchar(64),
   "alias"                varchar(125),
   description          varchar(255),
   category             varchar(36),
   isVisible            NUMBER(1),
   isEditable           NUMBER(1),
   createTime           date,
   updateTime           date
)/

comment on column meta_model.ID
is '物理主键'
/

comment on column meta_model."uid"
is '唯一标识'
/

comment on column meta_model.name
is '名称'
/

comment on column meta_model.dbObjectName
is '数据库表名'
/

comment on column meta_model."alias"
is '别名'
/

comment on column meta_model.description
is '字段描述'
/

comment on column meta_model.category
is '分类'
/

comment on column meta_model.isVisible
is '是否可见'
/

comment on column meta_model.isEditable
is '是否可编辑'
/

comment on column meta_model.createTime
is '创建时间'
/

comment on column meta_model.updateTime
is '更新时间'
/