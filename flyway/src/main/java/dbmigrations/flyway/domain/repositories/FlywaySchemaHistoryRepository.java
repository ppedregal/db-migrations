package dbmigrations.flyway.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dbmigrations.flyway.domain.models.FlywaySchemaHistory;

public interface FlywaySchemaHistoryRepository extends PagingAndSortingRepository<FlywaySchemaHistory,Long> {
    
}
