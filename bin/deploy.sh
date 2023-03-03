#!/bin/bash
# 这里可替换为你自己的执行程序，其他代码无需更改,绝对路径相对路径均可。
# 若使用jenkins等工具远程构建，则使用绝对路径，下面的日志输出路径同！
# 项目名称
APP_NAME=demo
# 主启动 jar 名称
JAR_NAME=demo-0.0.1-SNAPSHOT.jar
# 项目目录
BASE_DIR=`cd $(dirname $0)/..; pwd`
# 配置文件目录
CONFIG_DIR=${BASE_DIR}"/conf/"
# 启动参数
JAVA_OPTS="-Xms128m -Xmx128m -Xmn512m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=256m -XX:-OmitStackTraceInFastThrow"

# 使用说明，用来提示输入参数 仅在输入错误时提示使用。
usage() {
 echo "Usage: sh 脚本名.sh [start|stop|restart|status]"
 exit 1
}

#检查程序是否在运行
is_exist(){
 pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
 #如果不存在返回1，存在返回0
 if [ -z "${pid}" ]; then
 return 1
 else
 return 0
 fi
}

#输出运行状态
status(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "=====> ${APP_NAME} is running. Pid is ${pid}"
 else
 echo "=====> ${APP_NAME} is NOT running."
 fi
}

#启动方法
start(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "=====> ${APP_NAME} is already running. pid=${pid} ."
 else
  echo "nohup java ${JAVA_OPTS} -jar ${BASE_DIR}/lib/${JAR_NAME} --spring.config.location=${CONFIG_DIR} >/dev/null 2>&1 &"
  nohup java ${JAVA_OPTS} -jar ${BASE_DIR}/lib/${JAR_NAME} --spring.config.location=${CONFIG_DIR} > /dev/null 2>&1 &
  echo "=====> ${APP_NAME} starting"
  sleep 3
  status
 fi
}

#停止方法
stop(){
 is_exist
 if [ $? -eq "0" ]; then
 kill -9 $pid
 else
 echo "=====> ${APP_NAME} is not running"
 fi
}

#重启
restart(){
 stop
 sleep 3
 start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
 "start")
 start
 ;;
 "stop")
 stop
 ;;
 "status")
 status
 ;;
 "restart")
 restart
 ;;
 *)
 usage
 ;;
esac
