package org.example.config;

import org.example.dao.DaoApi;
import org.example.dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoAliasConfig {
    // Alias "org.example.dao" qui renvoie DaoApi comme implémentation
    @Bean(name = "dao")
    public IDao daoAlias(DaoApi target) {
        return target; // alias "org.example.dao" -> "daoApi"
    }
}
