# Flyway

## Que es

Migraciones en base de datos desde scripts SQL.

Licencia opensource, si bien existe version comercial con caracteristicas adicionales

Se puede utilizar de distintos modos:

* Directamente desde la linea de comandos: ``flyway migrate``
* Integrada en nuestras herramientas como parte del proceso de build: ant, maven, gradle
* Integrada en nuestro runtime como parte de nuestros microservicios

Caracteristicas adicionales a scripts sql estandar, entre otros:

* automatizacion de despliegues
* placeholders ${...}
* scripts segun plataforma, por defecto: db/migration/${platform}

## Integracion en microservicios

El objetivo de esta integracion es que cuando se despliegue una nueva version de un microservicio y este tenga modificaciones en el modelo de datos este modelo se actualice automaticamente con el microservicio, de esta manera conseguimos:

* Que cada microservicio sea el responsable de su propio modelo de datos
* Que cada vez que un microservicio se arranque si hay cambios estos sean aplicados automaticamente

La integracion con spring boot es minima:

Añadir dependencia maven:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

Los scripts de migraciones se deben ubicar por defecto en ``db/migration`` y deben seguir la nomenclatura establecida por [flyway](https://flywaydb.org/documentation/migrations#naming)

Se puede cambiar la configuracion por defecto desde cualquier origen de configuracion de spring, utilizando las propiedades con prefijo spring.flyway, las propiedades configurables estan definidas en el bean [FlywayProperties](https://raw.githubusercontent.com/spring-projects/spring-boot/master/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/flyway/FlywayProperties.java)

Se pueden incorporar migraciones complejas si se quiere mediante codigo java implementando una interfaz: [JavaMigration](https://flywaydb.org/documentation/api/javadoc/org/flywaydb/core/api/migration/JavaMigration)

# Cheatsheet nomenclatura

Scripts versionables ``V{VERSION}__{DESCRIPCION}.sql``

* solo se ejecutan si la version se incrementa  
* se controla que la base de datos este en la versión esperada comprobando el checksum de los scripts previamente ejecutados

Scripts repetibles ``R__{descripcion}.sql``

* se ejecutan siempre que el checksum cambie

# Alters
Debemos tener presentes que los scripts se aplicaran en orden incremental siempre que no se haya ejecutado anteriormente por tanto esperan encontrar la base de datos en un estado concreto o si no el script fallara necesariamente impidiendo que se aplique la migracion, por ejemplo si queremos realizar un alter sobre una tabla esta debe existir previamente, etc.

# Baselines
En flyway se puede establecer una linea base facilmente y comenzar a utilizar los scripts de migraciones desde un punto concreto, pudiendo mezclar migraciones manuales con automaticas a partir de un determinado baseline.
En la integracion con spring boot se deberia utilizar las propiedades: ``spring.flyway.baseline-on-migrate`` y ``spring.flyway.baseline-version`` las cuales marcarian el estado actual como baseline con una determinada version apartir de la cual se aplicarian las distintas migraciones manualmente.

# Rollbacks
En flyway los rollbacks deben ser codificados manualmente, y pueden aplicarse automaticamente si se dispone de una licencia ya que es una caracteristica de la version comercial.

# Otras consideraciones
Para las operativas no estandar de aplicar las migraciones automaticamente se recomienda utilizar no obstante la linea de comandos o el plugin de maven.

# Referencias
* https://flywaydb.org/
* https://www.baeldung.com/database-migrations-with-flyway
* https://www.adictosaltrabajo.com/2018/12/18/flyway-y-spring-boot-con-multiples-bases-de-datos/
