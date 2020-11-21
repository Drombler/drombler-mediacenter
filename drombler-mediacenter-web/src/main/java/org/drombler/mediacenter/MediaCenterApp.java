package org.drombler.mediacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(MediaCenterAppConfigurationProperties.class)
@SpringBootApplication
public class MediaCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(MediaCenterApp.class, args);
    }

}
