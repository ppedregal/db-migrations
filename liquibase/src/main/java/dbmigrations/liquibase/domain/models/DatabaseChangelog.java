package dbmigrations.liquibase.domain.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("DATABASECHANGELOG")
public class DatabaseChangelog {

    @Id
    @Column("ID")
    String id;

    @Column("AUTHOR")
    String author;

    @Column("FILENAME")
    String filename;

    @Column("DATEEXECUTED")
    Date executedAt;

    @Column("ORDEREXECUTED")
    Integer executedOrder;

    @Column("EXECTYPE")
    String executedType;

    @Column("MD5SUM")
    String md5;

    @Column("DESCRIPTION")
    String description;

    @Column("COMMENTS")
    String comments;

    @Column("TAG")
    String tag;

    @Column("LIQUIBASE")
    String liquibaseVersion;

    @Column("CONTEXTS")
    String contexts;

    @Column("LABELS")
    String labels;

    @Column("DEPLOYMENT_ID")
    String deploymentId;
    
}
