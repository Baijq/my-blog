package com.biubiu.myblog.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger 配置类
 *
 * @author biubiu
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerRestFulApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot 测试使用")
                .description("API 描述")
                .version("1.0")
                .build();

    }

}

