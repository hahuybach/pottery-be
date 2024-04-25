package com.example.pottery.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.pottery.db1",
        entityManagerFactoryRef = "dataSource1EntityManagerFactory",
        transactionManagerRef = "dataSource1TransactionManager"
)
public class DataSource1Config {
    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.db1")
    public DataSourceProperties dataSource1Properties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource1() {
        return dataSource1Properties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean dataSource1EntityManagerFactory(
            @Qualifier("dataSource1") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(dataSource)
                .packages("com.example.pottery.db1")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager dataSource1TransactionManager(
            @Qualifier("dataSource1EntityManagerFactory") LocalContainerEntityManagerFactoryBean dataSource1EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(dataSource1EntityManagerFactory.getObject()));
    }
}
