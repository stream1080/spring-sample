#!/usr/bin/env bash

_hostname=`hostname`
_hostname=-DCONTAINER_NAME'='${_hostname}
echo ">>>>>>>>>========= $_hostname"

java $JAVA_OPTS $_hostname -jar ../target/demo-0.0.1-SNAPSHOT.jar
#java $JAVA_OPTS $_hostname -jar /home/admin/jar-run/ROOT/application.jar
#java $JAVA_OPTS $_hostname -cp $(echo /home/admin/application/lib/application.jar | tr ' ' ':') com.qxwz.it.makalu.MakaluApplication