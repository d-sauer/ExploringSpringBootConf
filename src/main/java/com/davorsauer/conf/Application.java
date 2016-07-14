package com.davorsauer.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "customA", factory = CustomPropertySourceFactory.class)
@Import({CustomPropertySourcesPlaceholderConfigurer.class, CustomImportBeanDefinitionRegistrar.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
