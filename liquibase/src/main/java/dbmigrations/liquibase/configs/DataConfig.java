package dbmigrations.liquibase.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import dbmigrations.liquibase.domain.repositories.DatabaseChangelogRepository;

@Configuration
@EnableJdbcRepositories(basePackageClasses = DatabaseChangelogRepository.class)
public class DataConfig {
    
}
