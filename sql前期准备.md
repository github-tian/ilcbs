## 数据库准备相关
----

*  创建数据库表空间

create tablespace ilcbs
datafile 'C:\oracle\TZS.dbf'
size 50M
autoextend on
next 10M

create user ilcbs
identified by ilcbs
default tablespace ilcbs

grant dba to ilcbs

grant resource , connect to ilcbs

----

* 导入数据库文件

  后台系统数据库
