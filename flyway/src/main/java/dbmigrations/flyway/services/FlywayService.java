package dbmigrations.flyway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dbmigrations.flyway.domain.models.FlywaySchemaHistory;
import dbmigrations.flyway.domain.repositories.FlywaySchemaHistoryRepository;

@Service
public class FlywayService {

    @Autowired
    FlywaySchemaHistoryRepository schemaHistory;

	public Page<FlywaySchemaHistory> getHistory(Pageable p) {
		return schemaHistory.findAll(p);
	}
    
}
