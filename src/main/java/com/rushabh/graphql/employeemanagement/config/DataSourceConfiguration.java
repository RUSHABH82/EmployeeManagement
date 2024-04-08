package com.rushabh.graphql.employeemanagement.config;


import com.rushabh.graphql.employeemanagement.constance.EmployeeManagementConst;
import com.rushabh.graphql.employeemanagement.exception.EmployeeManagementException;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableWebMvc
public class DataSourceConfiguration implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);
    private final EmployeeManagementConfiguration employeeManagementConfiguration;

    public DataSourceConfiguration(EmployeeManagementConfiguration employeeManagementConfiguration) {
        this.employeeManagementConfiguration = employeeManagementConfiguration;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Bean(name = "datasource")
    public DataSource getDataSource() throws EmployeeManagementException {
        try {
            DataSourceBuilder<?> builder = DataSourceBuilder.create();
            builder.url(employeeManagementConfiguration.getDatabaseUrl());
            builder.username(employeeManagementConfiguration.getDatabaseUsername());
            builder.password(employeeManagementConfiguration.getDatabasePassword());

            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setDataSource(builder.build());
            hikariDataSource.setMaxLifetime(employeeManagementConfiguration.getHikariMaxLifeTime());
            hikariDataSource.setValidationTimeout(employeeManagementConfiguration.getHikariValidationLifeTime());
            hikariDataSource.setConnectionTimeout(employeeManagementConfiguration.getHikariConnectionTimOutTime());
            return hikariDataSource;
        } catch (Exception e) {
            LOGGER.error(EmployeeManagementConst.CLS_MET_ERROR, DataSourceConfiguration.class, "getDataSource", ExceptionUtils.getStackTrace(e));
            throw new EmployeeManagementException("Database Initialization Error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws EmployeeManagementException {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan(EmployeeManagementConst.ENTITY_PACKAGE);
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalJpaProperties());
        return entityManagerFactoryBean;
    }

    private Properties additionalJpaProperties() {
        final Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", EmployeeManagementConst.MYSQL_HBM2DDL);
        jpaProperties.setProperty("hibernate.dialect", EmployeeManagementConst.MYSQL_DIALECT);
        jpaProperties.setProperty("defer-datasource-initialization", Boolean.FALSE.toString());
        return jpaProperties;
    }

}
