Restore Sql
===============  

This is a plugin for JetBrains IDEs that restore the ibatis/mybatis generate sql to original whole sql.
It will generate sql statements with replace ? to the really param value.
Through the "Tools -> Tail restore sql in Console" menu you can tail the sql log.
You can selected the "Format Sql" button on the left to format the generate sql statements.  

===============
这是一个Intellij的插件，主要作用是把ibatis/mybatis生成的preparedstatement语句恢复成原始完整的sql语句。  
它将用真实的参数值替换preparedstatement语句的问号占位符。
通过 "Tools -> Tail restore sql in Console" 这个菜单可以实时输出sql日志。
左边加了几个按钮，分别是：  
* Rerun: 重新启动restore.sql的输出
* Stop: 停止输出
* Format Sql: 格式化**后续**输出的Sql语句
* Close: 关闭该窗口

===============
所支持ibatis/mybatis的输出格式如下：  

`2016-10-21 16:46:29.316 DEBUG c.o.f.b.s.B.testMethod -  -  - ==>  Preparing: SELECT * FROM tablename where id = ?  `
`2016-10-21 16:46:29.343 DEBUG c.o.f.b.s.B.testMethod -  -  - ==> Parameters: 123(Integer)`  
以 "Preparing:" 和 "Parameters:" 作为分割符进行解析。  

===============  
[插件下载](https://github.com/kookob/RestoreSql/blob/master/RestoreSql.jar?raw=true "Download Plugin")  

===============  

**(This plugin requires your IDE to run on Java 7 or higher.)**  

Reference and copy some code from below list:  
https://github.com/JetBrains/intellij-community  
https://github.com/krasa/GrepConsole  
https://github.com/hibernate/hibernate-orm  
