

# "北理人"校内论坛项目
## 功能

登录、注册、发送激活邮件、修改个人信息、发帖、给帖子点赞、回复帖子、关注取关用户等

## 开发环境

操作系统：macOS Mojave 10.14.2  

IDE： IntelliJ idea

## 技术栈  
框架：SpringMVC  

构建工具：Maven

持久层：MyBatis  

数据库：MySQL / Redis  

前端：jQuery  

## 在线访问

https://bithelper.site

## 本地迁移到vps的部署过程

https://limengting.site/2019/03/10/%E5%9C%A8vps%E4%B8%8A%E9%83%A8%E7%BD%B2SpringMVC-Mysql-Tomcat%E5%8A%A8%E6%80%81%E7%BD%91%E7%AB%99

## 程序本地运行操作步骤

1. 安装JDK 1.8
2. 安装tomcat服务器
3. 安装并运行redis服务器
4. 将程序文件用idea打开，下载maven依赖
5. 安装mysql，将df.sql文件中内容写入数据库建库建表
6. idea内配置tomcat服务器，选择部署时打war包 
7. 运行程序