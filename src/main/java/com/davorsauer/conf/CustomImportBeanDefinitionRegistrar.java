package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Davor Sauer
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(CustomImportBeanDefinitionRegistrar.class);
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        logger.info("2. setEnvironment");
        this.environment = environment;

        PropertiesUtil.listProeprtySources(logger, environment);
        PropertiesUtil.checkProperties(logger, environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        logger.info("2. registerBeanDefinitions");
        logger.info("2. expecting all properties are resolved");

        PropertiesUtil.listProeprtySources(logger, environment);
        PropertiesUtil.checkProperties(logger, environment);

        logger.info("!! Do something based on property 'customA.property'={}", environment.getProperty("customA.property"));
        logger.info("!! Do something based on property 'customB.property'={}", environment.getProperty("customB.property"));
    }


}
