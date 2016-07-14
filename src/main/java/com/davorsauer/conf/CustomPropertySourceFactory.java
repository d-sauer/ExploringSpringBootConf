package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;

/**
 * @author Davor Sauer
 */
public class CustomPropertySourceFactory implements PropertySourceFactory, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(CustomPropertySourceFactory.class);

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        logger.info("0. setEnvironment");
    }

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        logger.info("0. createPropertySource = customA.properties");


        PropertiesPropertySource propertySourceA = new PropertiesPropertySource("customAProperties", PropertiesUtil.customProperties(logger, "customA.properties"));
        // Depend on Environment properties or default value from 'propertySource'
        // load some another PropertiesSource
        PropertiesPropertySource propertySourceA2 = new PropertiesPropertySource("customA2Properties", PropertiesUtil.customProperties(logger, "customA2.properties"));

        CompositePropertySource compositePropertySource = new CompositePropertySource("customProperties");
        compositePropertySource.addFirstPropertySource(propertySourceA);
        compositePropertySource.addFirstPropertySource(propertySourceA2);

        return compositePropertySource;
    }
}
