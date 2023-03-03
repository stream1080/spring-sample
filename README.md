# SpringBoot 工程模版

## 项目简介

该项目是一款基于`SpringBoot` 的工程脚手架，集成常用的开发组件集，公共配置，日志处理等，旨在快速生产一个 `SpringBoot` 工程

## 主要特性

- 集成 `SpringBoot` 常用开发组件集、公共配置、`AOP` 日志等
- 集成 `MyBatis-Plus` 快速完成 `dao` 操作
- 集成 `dao` 代码代码生成器
- 集成 `Swagger3`，可自动生成在线 `api` 文档
- 集成 `Redis` 缓存
- 集成 `RabbitMq` 消息队列
- 集成数据库连接连接池
- 使用 `assembly maven` 插件打包部署
- 包含启动、重启和停止命令的部署脚本，`docker` 打包构建脚本

## 项目结构

```
└── demo
    ├── annnotation
    ├── aspect
    ├── config
    ├── controller
    ├── enums
    ├── exception
    ├── filter
    ├── handler
    ├── mapper
    ├── model
    ├── service
    ├── utils
    ├── validator
    ├── vo
    └── DemoApplication.java
```
