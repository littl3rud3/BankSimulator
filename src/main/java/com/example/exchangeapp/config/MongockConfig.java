package com.example.exchangeapp.config;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import io.mongock.driver.mongodb.reactive.driver.MongoReactiveDriver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongockConfig {

    @Value("${mongock.database_url}")
    private String mongoDbConnection;

    @Value("${mongock.migration-scan-package}")
    private String scanPackage;

    @Value("${mongock.database_name}")
    private String databaseName;

    @Value("${mongock.insert_count}")
    public static int insertCount = 10;

    @Bean
    public MongockInitializingBeanRunner getBuilder(MongoClient reactiveMongoClient,
                                                    ApplicationContext context) {

        return MongockSpringboot.builder()
                                .setDriver(MongoReactiveDriver.withDefaultLock(reactiveMongoClient, databaseName))
                                .addMigrationScanPackage(scanPackage)
                                .setSpringContext(context)
                                .setTransactionEnabled(true)
                                .buildInitializingBeanRunner();
    }

    @Bean
    MongoClient mongoClient() {

        CodecRegistry codecRegistry = fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        return MongoClients.create(MongoClientSettings.builder()
                                                      .applyConnectionString(
                                                          new ConnectionString(mongoDbConnection))
                                                      .codecRegistry(codecRegistry)
                                                      .build());
    }
}
