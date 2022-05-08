package tech.jhipster.fullapp.technical.infrastructure.secondary.postgresql;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({ "tech.jhipster.fullapp" })
@EnableTransactionManagement
public class DatabaseConfiguration {}
