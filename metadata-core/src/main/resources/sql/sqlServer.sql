if exists (select 1
            from  sysobjects
           where  id = object_id('meta_field')
            and   type = 'U')
   drop table meta_field
go

if exists (select 1
            from  sysobjects
           where  id = object_id('meta_model')
            and   type = 'U')
   drop table meta_model
go

/*==============================================================*/
/* Table: meta_field                                            */
/*==============================================================*/
create table meta_field (
   ID                   int                  identity,
   uid                  varchar(128)          null,
   name                 varchar(128)         null,
   dbObjectName         varchar(128)          null,
   alias                varchar(128)         null,
   description          varchar(255)         null,
   isNullable           bit                  null,
   dataType             varchar(128)          null,
   createTime           datetime             null,
   updateTime           datetime             null,
   constraint PK_META_FIELD primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('meta_field') and minor_id = 0)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '元数据字段表',
   'user', @CurrentUser, 'table', 'meta_field'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '自增长主键',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'uid')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'uid'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '唯一标识',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'uid'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'name')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'name'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '名称',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'name'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'dbObjectName')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'dbObjectName'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '数据库表名',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'dbObjectName'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'alias')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'alias'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'alias'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'description')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'description'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'description'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'isNullable')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'isNullable'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '是否可为空',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'isNullable'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'dataType')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'dataType'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '数据类型',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'dataType'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'createTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'createTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_field')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'updateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '更新时间',
   'user', @CurrentUser, 'table', 'meta_field', 'column', 'updateTime'
go

/*==============================================================*/
/* Table: meta_model                                            */
/*==============================================================*/
create table meta_model (
   ID                   int                  identity,
   uid                  varchar(128)          null,
   name                 varchar(128)         null,
   dbObjectName         varchar(128)          null,
   alias                varchar(128)         null,
   description          varchar(255)         null,
   category             varchar(128)          null,
   isVisible            bit                  null,
   isEditable           bit                  null,
   createTime           datetime             null,
   updateTime           datetime             null,
   constraint PK_META_MODEL primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('meta_model') and minor_id = 0)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '元数据实体表',
   'user', @CurrentUser, 'table', 'meta_model'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '自增长主键',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'uid')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'uid'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '唯一标识',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'uid'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'name')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'name'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '名称',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'name'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'dbObjectName')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'dbObjectName'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '数据库表名',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'dbObjectName'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'alias')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'alias'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '别名',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'alias'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'description')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'description'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '描述',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'description'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'category')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'category'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '分类',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'category'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'isVisible')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'isVisible'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '是否可查询',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'isVisible'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'isEditable')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'isEditable'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '是否可编辑',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'isEditable'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'createTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '创建时间',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'createTime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('meta_model')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updateTime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'updateTime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description',
   '更新时间',
   'user', @CurrentUser, 'table', 'meta_model', 'column', 'updateTime'
go
