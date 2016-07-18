log4jdbc
特性：
1.支持JDBC3和JDBC4，其中JDBC3对应于JDK 1.4 or 1.5，JDBC4要求JDK 1.6或以上。
2.支持现有大部分JDBC驱动。
3.易于配置（在大部分情况下，只需要改变驱动类名并在jdbc url前加上”jdbc:log4“，设置好日志输出级别）。
4.能够自动把SQL变量值加到SQL输出日志中，改进易读和方便调试。
5.能够快速标识出应用程序中执行比较慢的sql语句

使用步骤如下：
1、到官网下载log4jdbc和slf4j的jar包；比如log4jdbc3-1.1.jar、slf4j-api-1.5.0.jar，
     对于slf4j要看系统到底使用什么log框架，若使用log4j，则需要slf4j-log4j12-1.5.0.jar；
     
2、将应用中的driver-class设置为net.sf.log4jdbc.DriverSpy；对于常用的jdbc驱动无需其他设置，
     比如oracle驱动oracle.jdbc.driver.OracleDriver也已经在默认设置中了，若默认驱动列表中不包括你的应用系统中需要的驱动，
     那需要通过-Dlog4jdbc.drivers来进行设置系统属性，支持的默认驱动列表可到官网查看；
     
3、在应用中jdbc url最前面添加jdbc:log4；
     比如oracle的url就变成了jdbc:log4jdbc:oracle:thin:@x.x.x.x:1521:dbname；
     
4、在日志系统中设置jdbc.sqlonly、jdbc.sqltiming等日志级别
   #log4jdbc设置
   log4j.logger.jdbc.sqlonly=off
   log4j.logger.jdbc.sqltiming=info
   log4j.logger.jdbc.audit=off
   log4j.logger.jdbc.resultset=off
   log4j.logger.jdbc.connection=off
   
5、运行系统测试，到对应日志文件中查看SQL相关信息；

前面提到了基本的log4jdbc配置和功能，除了这些之外，他也还提供一些比较复杂的功能，
比如可以通过-Dlog4jdbc.sqltiming.warn.threshold参数设置SQL耗时多少以上就打印warn信息；
另外，log4jdbc还提供了一个对日志文件进行分析的脚本；
经测试，log4jdbc不能在XA环境下使用，比如jboss datasource配置中，
若以非XA形式local-tx-datasource配置，则没有问题；若以xa-datasource配置，则无法生效，
系统运行时不能取得connection；究其原因的话，是由于log4jdbc针对的是driver-class的封装，而不是datasource-class；
所以对系统中使用XA的用户来说就无法使用该框架了，若一定要使用，只能修改成local-tx-datasource类型；

摘自：http://blog.csdn.net/sfdev/article/details/2317861