package dbmigrations.liquibase.domain.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("DATABASECHANGELOGLOCK")
public class DatabaseChangelogLock {

    @Id
    @Column("ID")
    Long id;
    
    @Column("LOCKED")
    Boolean locked;

    @Column("LOCKGRANTED")
    Date lockedAt;

    @Column("LOCKEDBY")
    String lockedBy;
    
}
