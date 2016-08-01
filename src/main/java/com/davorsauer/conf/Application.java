package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "customA", factory = CustomPropertySourceFactory.class)
@Import({CustomPropertySourcesPlaceholderConfigurer.class, CustomImportBeanDefinitionRegistrar.class})
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @CustomAnnotation
    public Object testObject() {
        logger.info("Init testObject bean");
        return new Object();
    }
}
