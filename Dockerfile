# 基础镜像
FROM  openjdk:8-jre

# author
MAINTAINER stream1080

# 定义工作目录
WORKDIR /home/admin

# 复制 jar 文件到容器中
COPY ./target/*.jar /home/admin/app.jar

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

# 暴露端口
EXPOSE 8080

# 启动服务
CMD ["java", "$JAVA_OPTS", "-jar", "app.jar"]
