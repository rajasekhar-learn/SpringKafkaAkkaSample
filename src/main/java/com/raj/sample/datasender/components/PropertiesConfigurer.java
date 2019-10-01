package com.raj.sample.datasender.components;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class PropertiesConfigurer extends PropertySourcesPlaceholderConfigurer
    implements EnvironmentAware, InitializingBean {


    private String[] locations;

    @Autowired
    private ResourceLoader rl;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        // save off Environment for later use
        this.environment = environment;
        super.setEnvironment(environment);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Copy property sources to Environment
        MutablePropertySources envPropSources = ((ConfigurableEnvironment) environment).getPropertySources();
        envPropSources.forEach(propertySource -> {
            if (propertySource.containsProperty("application.properties.locations")) {
                locations = ((String) propertySource.getProperty("application.properties.locations")).split(",");
                stream(locations).forEach(filename -> loadProperties(filename).forEach(source -> {
                    envPropSources.addFirst(source);
                }));
            }
        });
    }


    private List<PropertySource> loadProperties(final String filename) {
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        try {
            final Resource[] possiblePropertiesResources = ResourcePatternUtils.getResourcePatternResolver(rl).getResources(filename);
            return stream(possiblePropertiesResources)
                    .filter(Resource::exists)
                    .map(resource1 -> {
                        try {
                            return loader.load(resource1.getFilename(), resource1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).flatMap(l -> l.stream())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
