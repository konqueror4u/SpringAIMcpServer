package org.vis.mcpserver.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class DatabaseConfig {

    /**
     * Database Connection Factory Initializer Bean
     * Method Name: initializer
     * Parameters: ConnectionFactory connectionFactory - The R2DBC connection factory for PostgreSQL
     * Return Type: ConnectionFactoryInitializer - Initializer that runs SQL scripts on startup
     *
     * Usage: Configures and creates a ConnectionFactoryInitializer bean that automatically
     * executes the schema.sql file when the application starts. This initializer:
     * - Connects to the PostgreSQL database using the provided ConnectionFactory
     * - Populates the database with the schema from schema.sql (creates tables, indexes, etc.)
     * - Runs only once during application startup for database setup
     *
     * This ensures the database schema is always initialized before the application
     * attempts to query or manipulate data.
     */
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }
}
