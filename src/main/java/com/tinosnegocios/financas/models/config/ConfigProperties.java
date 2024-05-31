package com.tinosnegocios.financas.models.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class ConfigProperties {
    @Value("${omdbApiKey}")
    private String omdbKey;

    public String getOmdbKey() {
        return omdbKey;
    }
}
