#!/usr/bin/env bash

source /etc/profile

# 获取 commit id
cid=`git rev-parse --short HEAD`
echo "git commit id: ${cid}"

# maven 构建
mvn clean package -Dmaven.test.skip=true

