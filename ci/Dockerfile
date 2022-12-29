# 基础镜像
FROM  openjdk:8-jre

# author
MAINTAINER stream1080

RUN mkdir -p /home/admin

WORKDIR /home/admin

# 复制jar文件到路径
ADD app.jar /home/admin/app.jar

EXPOSE 8080
# 启动服务
ENTRYPOINT ["java","-jar","app.jar"]