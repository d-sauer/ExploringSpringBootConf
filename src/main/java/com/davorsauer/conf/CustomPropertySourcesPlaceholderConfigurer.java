package com.davorsauer.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @author Davor Sauer
 */
public class CustomPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(CustomPropertySourcesPlaceholderConfigurer.class);

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        logger.info("1. setEnvironment");
        this.environment = environment;
        super.setEnvironment(environment);

        PropertiesUtil.listProeprtySources(logger, environment);
        PropertiesUtil.checkProperties(logger, environment);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("1. postProcessBeanFactory");

        PropertySource customPropertySource = new PropertiesPropertySource("customCProperties", PropertiesUtil.customProperties(logger, "customC.properties"));
        addLastPropertySource(customPropertySource);

        super.postProcessBeanFactory(beanFactory);

        PropertiesUtil.listProeprtySources(logger, environment);
        PropertiesUtil.checkProperties(logger, environment);
    }



    private void addLastPropertySource(PropertySource propertySources) {
        MutablePropertySources envPropSources = ((ConfigurableEnvironment) environment).getPropertySources();
        envPropSources.addLast(propertySources);
    }
}
