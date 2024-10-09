package com.aqdai.pigeonweb.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/pigeon?serverTimezone=Asia/Shanghai&characterEncoding=utf8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("aqdai") // 设置作者
                            .outputDir(System.getProperty("user.dir") + "/pigeon-web/src/main/java") // 输出目录
                            .disableOpenDir() // 生成后不自动打开目录
                            .commentDate("yyyy-MM-dd"); // 注释日期
                })
                .packageConfig(builder -> {
                    builder.parent("com.aqdai.pigeonweb") // 设置父包名
                            .entity("entity") // 实体类包名
                            .mapper("dao") // Mapper 接口包名
                            .service("service") // Service 包名
                            .serviceImpl("service.impl") // Service 实现类包名
                            .controller("controller") // Controller 包名
                            .xml("dao.mappers"); // Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("channel_type") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .enableChainModel() // 启用链式调用模式
                            .logicDeleteColumnName("deleted") // 启用逻辑删除字段
                            .idType(com.baomidou.mybatisplus.annotation.IdType.AUTO) // 设置主键策略
                            .controllerBuilder()
                            .enableRestStyle() // 启用 Rest 风格
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .build()
                            .serviceBuilder() // 启用 ServiceBuilder
                            .formatServiceFileName("%sService") // 自定义 Service 文件名
                            .formatServiceImplFileName("%sServiceImpl") // 自定义 Service 实现类文件名
                            .build()
                            .mapperBuilder()
                            .enableBaseResultMap() // 启用 BaseResultMap
                            .enableBaseColumnList(); // 启用 BaseColumnList
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用 Velocity 模板引擎
                .execute(); // 执行生成
    }
}
