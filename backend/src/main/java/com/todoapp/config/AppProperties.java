package com.todoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;
    private Security security = new Security();
    private Database database = new Database();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public static class Security {
        private boolean enableCors = true;
        private String[] allowedOrigins = {"*"};

        public boolean isEnableCors() {
            return enableCors;
        }

        public void setEnableCors(boolean enableCors) {
            this.enableCors = enableCors;
        }

        public String[] getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(String[] allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }
    }

    public static class Database {
        private String uri;
        private String databaseName;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getDatabaseName() {
            return databaseName;
        }

        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }
    }
} 