package org.example.config;

import org.example.dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

    // Injection de tous les beans IDao indexés par id de bean
    private final Map<String, IDao> candidates;

    public PropertyDrivenConfig(Map<String, IDao> candidates) {
        this.candidates = candidates;
    }

    @Value("${dao.target:daoImpl}")
    private String target;

    @Bean(name = "dao")
    @Primary  // Marque ce bean comme primaire pour résoudre les ambiguïtés
    public IDao selectedDao() {
        IDao bean = candidates.get(target);
        if (bean == null) {
            throw new IllegalArgumentException("Implémentation inconnue: " + target + " (daoImpl|daoImpl2|daoFile|daoApi)");
        }
        return bean;
    }

    // Résolution des placeholders @Value sans Spring Boot
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}