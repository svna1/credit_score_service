package com.demo.creditscore.db.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan("com.demo.creditscore.db.entity")
@EnableJpaRepositories(basePackages = "com.demo.creditscore.db.repository")
@ComponentScan({"com.demo.creditscore.db"})
@PropertySource(value = {"classpath:/application.properties"})
public class CreditScoreDbConfig {
}
