package dbmigrations.liquibase.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import dbmigrations.liquibase.domain.model.DatabaseChangelogLock;

public interface DatabaseChangelogLockRepository extends PagingAndSortingRepository<DatabaseChangelogLock,Integer> {
    
}
