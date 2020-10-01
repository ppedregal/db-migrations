package dbmigrations.flyway.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import dbmigrations.flyway.domain.repositories.FlywaySchemaHistoryRepository;

@Configuration
@EnableJdbcRepositories(basePackageClasses = FlywaySchemaHistoryRepository.class)
public class DataConfig {
    
}
