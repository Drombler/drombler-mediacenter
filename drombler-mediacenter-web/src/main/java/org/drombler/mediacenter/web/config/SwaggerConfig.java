package org.drombler.mediacenter.web.config;

import org.drombler.mediacenter.web.controller.RestControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Florian
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public Docket v1Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(RestControllerUtils.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo())
                .groupName(RestControllerUtils.V1_PATH_SEGMENT);
    }

    private ApiInfo createApiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        return builder.title("Drombler MediaCenter API")
                .version(buildProperties.getVersion())
                .build();
    }
}
