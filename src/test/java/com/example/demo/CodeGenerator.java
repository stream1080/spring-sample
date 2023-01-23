package com.example.demo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @author stream
 * @since 2021/7/31 12:25
 */
public class CodeGenerator {

    private static String author = "stream";
    private static String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "123456";
    private static String packageName = "com.example.demo";
    private static String xmlPath = "src/main/resources/mapper";
    private static List<String> tables = Lists.newArrayList("demo_user");


    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author(author)
                            // 覆盖已生成文件
                            .fileOverride()
                            // 开启 swagger 模式
                            .enableSwagger()
                            // 指定输出目录
                            .outputDir(String.format("%s/src/main/java", System.getProperty("user.dir")));
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent(packageName)
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tables);

                    builder.entityBuilder()
                            .enableChainModel()
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation();

                    builder.serviceBuilder()
                            //去掉IService前面的I
                            .formatServiceFileName("%sService");

                    builder.controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle();
                })
                .execute();
    }
}

