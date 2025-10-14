package org.example.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoImpl")  // Changé de "org.example.dao" à "daoImpl"
@Profile("prod")
public class DaoImpl implements IDao {
    @Override
    public double getValue(){
        return 100.0;
    }
}