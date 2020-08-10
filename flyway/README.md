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

Se puede cambiar la configuracion por defecto desde cualquier origen de configuracion de spring, utilizando las propiedades con prefijo spring.flyway, las propiedades configurables estan definidas en el bean [FlywayProperties](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/flyway/FlywayProperties.html)

Se pueden incorporar migraciones complejas si se quiere mediante codigo java implementando una interfaz: [JavaMigration](https://flywaydb.org/documentation/api/javadoc/org/flywaydb/core/api/migration/JavaMigration)

# Cheatsheet nomenclatura

Scripts versionables ``V{VERSION}__{DESCRIPCION}.sql``

* solo se ejecutan si la version se incrementa  
* se controla que la base de datos este en la versión esperada comprobando el checksum de los scripts

Scripts repetibles ``R_{descripcion}.sql``

* se ejecutan siempre que el checksum cambie

# Referencias
* https://flywaydb.org/
* https://www.baeldung.com/database-migrations-with-flyway
* https://www.adictosaltrabajo.com/2018/12/18/flyway-y-spring-boot-con-multiples-bases-de-datos/
