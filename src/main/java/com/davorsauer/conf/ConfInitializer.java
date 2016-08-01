package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Davor Sauer
 */
public class ConfInitializer implements ApplicationContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ConfInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        environment.getPropertySources().forEach(propertySource -> logger.info("Property source: {}", propertySource.getName()));

        Iterator<String> beanNamesIterator = applicationContext.getBeanFactory().getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) {
            logger.info("Bean: {}", beanNamesIterator.next());
        }
    }
}
