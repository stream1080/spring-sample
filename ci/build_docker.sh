#!/usr/bin/env bash

# 输入参数校验
if [ $# -lt 1 ]; then
    echo "Usage: $0  IMAGE"
    echo " E.G.: $0  demo-service:latest"
    exit 1
fi

IMAGE=$1

#准备jar包
sh package.sh
cp -r ../target/*.jar ./app.jar

#build image
docker build -t ${IMAGE} .
[ "$?" -ne 0 ] && {

    echo "build ${IMAGE} error."

    # Delete image <${IMAGE}>
    docker rmi ${IMAGE}

    exit 103
} || {

    echo "create image ${IMAGE} successfully and will push it to registry."
}
