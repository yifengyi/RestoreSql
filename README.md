Restore Sql
===============  

This is a plugin for JetBrains IDEs that restore the ibatis/mybatis generate sql to orignal whole sql.  
It will generate restore.sql file on the project base path. and replace ? to the really param value.  
Through the "Tools -> Tail restore.sql In Console" menu you can tail the sql file.  
You can selected the "Format Sql" button on the left to format the generate sql statements.  
~~When the file length more then 50000 characters, the file contents will be cleared, and generate again.~~

===============
这是一个Intellij的插件，主要作用是把ibatis/mybatis生成的preparedstatement语句恢复成原始完整的sql语句。  
它将在项目的根目录下生成一个restore.sql文件，保存替换后的sql语句(把问号替换成参数值)。  
通过 "Tools -> Tail restore.sql In Console" 这个菜单可以实时输出restore.sql里面的内容。  
左边加了几个按钮，分别是：  
* Rerun: 重新启动restore.sql的输出
* Stop: 停止输出
* Format Sql: 格式化**后续**输出的Sql语句
* Clear Sql: 清空restore.sql里面的内容
* Close: 关闭该窗口  

~~当restore.sql内容超过50000个字符时，为了性能考虑，程序会自动清空文件内容。~~

===============  
所支持ibatis/mybatis的输出格式如下：  

`2016-10-21 16:46:29.316 DEBUG c.o.f.b.s.B.testMethod -  -  - ==>  Preparing: SELECT * FROM tablename where id = ?  `
`2016-10-21 16:46:29.343 DEBUG c.o.f.b.s.B.testMethod -  -  - ==> Parameters: 123(Integer)`  
以 "Preparing:" 和 "Parameters:" 作为分割符进行解析。  

===============  
[插件下载](https://github.com/kookob/RestoreSql/blob/storage2File/RestoreSql.jar?raw=true "Download Plugin")

===============  

**(This plugin requires your IDE to run on Java 7 or higher.)**  

Reference and copy some code from below list:  
https://github.com/JetBrains/intellij-community  
https://github.com/krasa/GrepConsole  
https://github.com/hibernate/hibernate-orm  
