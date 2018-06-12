# 一个简易的论坛系统
该项目开发IDE为IntelliJ IDEA，通过Maven构建，后台采用Spring、SpringMVC、MyBatis，数据库采用MySQL、Redis，前台用到jQuery。

# 作者：李梦婷 学号：1120151451
# 系统：ubuntu-17.10 
# IDE: IntelliJ idea 
程序运行操作步骤：
1） 安装JRE/JDK 1.8。
2) 下载tomcat服务器。
3) 安装redis并运行。
4） 将程序文件复制到计算机上
5) 用idea打开程序所在目录
6) 刷新maven项目，等待maven下载依赖包
7) 配置tomcat服务器 
8) 将df.sql文件中内容写入本地mysql数据库进行建表
9) 将forum\src\main\java\com\limengting\util中的MyConstant.java中的IP改成本机IP，即将下面一行中的IP地址进行修改；
public static final String DOMAIN_NAME = "http://192.168.1.104:8080/";
10) 将本地mysql的配置文件中的sever编码方式由默认的latin1改成utf-8
11) 运行程序。
