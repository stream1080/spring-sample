# 基础镜像
FROM  openjdk:8-jre

USER root

RUN set -x \
    && rm -rf /home/admin/entrypoint.sh \
    && mkdir -p /home/admin/jar-run/ROOT

#COPY application /home/admin/application

COPY application.jar /home/admin/jar-run/ROOT/application.jar
COPY entrypoint.sh /home/admin/

RUN set -x \
    && chown -R admin.admin /home/admin/jar-run

USER admin

WORKDIR /home/admin