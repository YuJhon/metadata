declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('meta_field');
    if num > 0 then
        execute immediate 'drop table sys_area' ;
    end if;
end;

declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('meta_model');
    if num > 0 then
        execute immediate 'drop table sys_area' ;
    end if;
end;
