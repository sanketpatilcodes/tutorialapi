package com.codeminton.tutorialapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    
    private String url;
    private String username;
    private String password;

    public String getUrl() {
        // Convert postgres:// to jdbc:postgresql:// if needed
        if (url != null && url.startsWith("postgres://")) {
            url = url.replace("postgres://", "jdbc:postgresql://");
        } else if (url != null && url.startsWith("postgresql://")) {
            url = url.replace("postgresql://", "jdbc:postgresql://");
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
