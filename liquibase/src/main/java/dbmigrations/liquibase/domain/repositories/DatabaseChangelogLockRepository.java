package dbmigrations.liquibase.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dbmigrations.liquibase.domain.models.DatabaseChangelogLock;

public interface DatabaseChangelogLockRepository extends PagingAndSortingRepository<DatabaseChangelogLock,Long> {
    
}
