package com.codeminton.tutorialapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
@Profile("prod")
public class SchemaInitializer {

    @Bean
    public CommandLineRunner initializeSchema(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement()) {
                
                // Create tutorials table if it doesn't exist
                String sql = """
                    CREATE TABLE IF NOT EXISTS tutorials (
                        id BIGSERIAL PRIMARY KEY,
                        question VARCHAR(255) NOT NULL,
                        description TEXT,
                        created_at TIMESTAMP,
                        updated_at TIMESTAMP
                    )
                    """;
                
                stmt.execute(sql);
                System.out.println("✓ Database schema initialized successfully");
                
            } catch (Exception e) {
                System.err.println("⚠ Schema initialization warning: " + e.getMessage());
                // Don't fail if table already exists
            }
        };
    }
}
