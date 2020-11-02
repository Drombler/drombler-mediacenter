package org.drombler.photocenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(PhotoCenterAppConfigurationProperties.class)
@SpringBootApplication
public class PhotoCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(PhotoCenterApp.class, args);
    }

}
