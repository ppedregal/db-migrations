package dbmigrations.liquibase.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dbmigrations.liquibase.domain.models.DatabaseChangelog;

public interface DatabaseChangelogRepository extends PagingAndSortingRepository<DatabaseChangelog,String> {
    
}
