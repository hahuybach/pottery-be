package com.example.pottery.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages="com.example.pottery.db2")
public class DataSource2Config {
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory() throws Exception {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/pottery");
        return new SimpleMongoClientDatabaseFactory(mongoClient, "pottery");
    }
}