package xyz.yuzh.learning.groovy.combininggroovyjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.yuzh.learning.groovy.combininggroovyjava.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ApplicationProperties.class})
public class CombiningGroovyJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombiningGroovyJavaApplication.class, args);
    }

}
