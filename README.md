Restore Sql
===============

This is a plugin for JetBrains IDEs that restore the ibatis/mybatis generate sql to orignal whole sql.  
It will generate restore.sql file on the project base path. and replace ? to the really param value.  
Through the "Tools -> Tail restore.sql In Console" menu you can tail the sql file.  
You can selected the "Format Sql" button on the left to format the generate sql statements.  
When the file length more then 50000 characters, the file contents will be cleared, and generate again.  
  
**(This plugin requires your IDE to run on Java 7 or higher.)**  

Reference list:
https://github.com/JetBrains/intellij-community  
https://github.com/krasa/GrepConsole  
https://github.com/hibernate/hibernate-orm  
