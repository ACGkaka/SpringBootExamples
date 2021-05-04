package com.demo.swagger.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> @Title SwaggerConfig
 * <p> @Description Swagger 配置类
 *
 * @author ACGkaka
 * @date 2021/4/23 15:40
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {

        /// accessToken 请求头要求
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> parameters = new ArrayList<>();
//        ticketPar.name("accessToken").description("accessToken")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//        parameters.add(ticketPar.build());

        Set<String> consumes = new HashSet<>();
        consumes.add("multipart/form-data");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(parameters)
                .consumes(consumes);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo System")
                .description("Demo System API")
                .version("V1.0")
                .build();
    }
}
