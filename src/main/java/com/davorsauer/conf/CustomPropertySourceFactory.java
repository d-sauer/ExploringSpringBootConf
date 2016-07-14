package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;

/**
 * @author Davor Sauer
 */
public class CustomPropertySourceFactory implements PropertySourceFactory {

    private static final Logger logger = LoggerFactory.getLogger(CustomPropertySourceFactory.class);

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        logger.info("0. createPropertySource = customA.properties");
        return new PropertiesPropertySource("customAProperties", PropertiesUtil.customProperties("customA.properties"));
    }
}
