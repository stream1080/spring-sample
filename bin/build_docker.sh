#!/usr/bin/env bash

# 输入参数校验
if [ $# -lt 1 ]; then
    echo "Usage: $0  IMAGE"
    echo " E.G.: $0  demo-service:latest"
    exit 1
fi

IMAGE=$1

# 获取 commit id
cid=`git rev-parse --short HEAD`
echo "git commit id: ${cid}"

# maven build
mvn clean package -Dmaven.test.skip=true -f ../pom.xml

# docker build
docker build -t ${IMAGE} .
[ "$?" -ne 0 ] && {

    echo "build ${IMAGE} error."

    # Delete image <${IMAGE}>
    docker rmi ${IMAGE}

    exit 103
} || {
    echo "create image ${IMAGE} successfully."
}
