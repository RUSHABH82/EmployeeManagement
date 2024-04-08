package com.rushabh.graphql.employeemanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "employeemanagementportal")
public class EmployeeManagementConfiguration {


    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private int hikariMaxLifeTime;
    private int hikariValidationLifeTime;
    private int hikariConnectionTimOutTime;

    public EmployeeManagementConfiguration() {
        super();
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }


    public int getHikariMaxLifeTime() {
        return hikariMaxLifeTime;
    }

    public void setHikariMaxLifeTime(int hikariMaxLifeTime) {
        this.hikariMaxLifeTime = hikariMaxLifeTime;
    }

    public int getHikariValidationLifeTime() {
        return hikariValidationLifeTime;
    }

    public void setHikariValidationLifeTime(int hikariValidationLifeTime) {
        this.hikariValidationLifeTime = hikariValidationLifeTime;
    }

    public int getHikariConnectionTimOutTime() {
        return hikariConnectionTimOutTime;
    }

    public void setHikariConnectionTimOutTime(int hikariConnectionTimOutTime) {
        this.hikariConnectionTimOutTime = hikariConnectionTimOutTime;
    }
}
