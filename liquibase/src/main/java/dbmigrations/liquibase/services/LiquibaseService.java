package dbmigrations.liquibase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dbmigrations.liquibase.domain.model.DatabaseChangelog;
import dbmigrations.liquibase.domain.model.DatabaseChangelogLock;
import dbmigrations.liquibase.domain.repositories.DatabaseChangelogLockRepository;
import dbmigrations.liquibase.domain.repositories.DatabaseChangelogRepository;

@Service
public class LiquibaseService {

    @Autowired
    DatabaseChangelogRepository changelogs;

    @Autowired
    DatabaseChangelogLockRepository locks;

    @Transactional(readOnly = true)
    public Page<DatabaseChangelog> findChangelogs(Pageable p) {
        return changelogs.findAll(p);
    }

    @Transactional(readOnly = true)
    public Page<DatabaseChangelogLock> findLocks(Pageable p) {
        return locks.findAll(p);
    }
    
}
