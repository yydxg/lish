package com.pingan.cn.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.pingan.cn.controller"))
//                    .apis(RequestHandlerSelectors.basePackage("com.pingan.cn.ningbomap.controller"))
//                    .apis(RequestHandlerSelectors.basePackage("com.pingan.cn.guanqu"))
//                    .apis(RequestHandlerSelectors.basePackage("com.pingan.cn.supermap"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("springboot利用swagger构建api文档")
                    .description("简单优雅的restfun风格")
                    .termsOfServiceUrl("")
                    .version("1.0")
                    .build();
        }


}
