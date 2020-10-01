package dbmigrations.flyway.domain.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data 
@Table("flyway_schema_history")
public class FlywaySchemaHistory {

    @Id
    @Column("installed_rank")
    Long id;
    @Column("version")
    String version;
    @Column("description")
    String description;
    @Column("type")
    String type;
    @Column("script")
    String script;
    @Column("checksum")
    Long checksum;
    @Column("installed_by")
    String installedBy;
    @Column("installed_on")
    Date installedAt;
    @Column("execution_time")
    Long executionTime;
    @Column("success")
    Boolean success;

    
}
