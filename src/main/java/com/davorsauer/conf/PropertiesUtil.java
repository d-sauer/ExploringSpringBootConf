package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Davor Sauer
 */
public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static void checkProperties(Environment environment) {
        logger.info("PropertyA: {}", environment.getProperty("customA.property"));

        logger.info("PropertyB: {}", environment.getProperty("defaultB.property"));

        logger.info("PropertyC: {}", environment.getProperty("customC.property"));
    }

    public static void listProeprtySources(Environment environment) {
        ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
        configurableEnvironment.getPropertySources().forEach(propertySource -> {
            logger.info("  PropertySource: {}", propertySource.getName());
            if (propertySource instanceof MapPropertySource) {
                logger.info("           properties: {}", ((MapPropertySource) propertySource).getPropertyNames());
            }
        });
    }

    public static Properties customProperties(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource(fileName);

        final Properties properties = new Properties();
        try (final InputStream stream = classPathResource.getInputStream()) {
            properties.load(stream);
        } catch (IOException e) {
            logger.error("Can't load 'custom.properties", e);
        }

        return properties;
    }
}
