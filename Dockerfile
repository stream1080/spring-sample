# 基础镜像
FROM  openjdk:8-jre

# author
MAINTAINER stream1080

RUN mkdir -p /home/admin

# 定义工作目录
WORKDIR /home/admin

# 复制 jar 文件到容器中
COPY ./target/*.jar /home/admin/app.jar

# 暴露端口
EXPOSE 8080

# 启动服务
CMD ["java","-jar","app.jar"]