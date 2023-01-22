package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author junxi.liang
 * @since 2022-12-30 11:22
 */
@Configuration
public class SwaggerConfig {

    /**
     * 未测试通过，可能是 SpringBoot 版本兼容问题
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("示例接口文档")
                        .build());
    }

}
