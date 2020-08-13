# Liquibase

## Que es

Migraciones en base de datos desde xml.

Licencia opensource, si bien existe version comercial con caracteristicas adicionales

Se puede utilizar de distintos modos:

* Directamente desde la linea de comandos: ``liquibase update``
* Integrada en nuestras herramientas como parte del proceso de build: ant, maven, gradle
* Integrada en nuestro runtime como parte de nuestros microservicios

Caracteristicas adicionales a scripts sql estandar, entre otros:

* automatizacion de despliegues
* parametros
* ademas de scripts sql, soporta changesets estructurados con xml (o yaml, json equivalente) lo cual permite automatizar la generacion de los scripts de cambios y rollback de los mismos ademas de ser agnostico en cuanto a la base de datos, estos changesets estructurados se pueden generar automaticamente desde una base de datos ya existente

## Integracion en microservicios

El objetivo de esta integracion es que cuando se despliegue una nueva version de un microservicio y este tenga modificaciones en el modelo de datos este modelo se actualice automaticamente como parte del proceso de arranque del microservicio, de esta manera conseguimos:

* Que cada microservicio sea el responsable de su propio modelo de datos
* Que cada vez que un microservicio se arranque si hay cambios estos sean aplicados automaticamente

La integracion con spring boot es minima:

Añadir dependencia maven:

```xml
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>
```

Liquibase require un fichero de changelog que controla las migraciones, por defecto ubicado en: ``db/changelog/db.changelog-master.yaml`` desde este fichero indicaremos los cambios a aplicar, los conjuntos de cambios se añaden a este mismo fichero si bien se pueden realizar includes a otros ficheros, pudiendo estos ser simplemente scripts sql o bien xml con la sintaxis especifica de liquibase.

Se puede cambiar la configuracion por defecto desde cualquier origen de configuracion de spring, utilizando las propiedades con prefijo spring.liquibase, las propiedades configurables estan definidas en el bean [LiquibaseProperties](https://raw.githubusercontent.com/spring-projects/spring-boot/master/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/liquibase/LiquibaseProperties.java)

Se pueden incorporar migraciones complejas si se quiere mediante codigo java implementando una interfaz: [CustomTaskChange](https://github.com/liquibase/liquibase/blob/master/liquibase-core/src/main/java/liquibase/change/custom/CustomTaskChange.java), para mas detalle consultar informacion acerca del tag [customChange](https://docs.liquibase.com/change-types/community/custom-change.html)

# Cheatsheet changesets
Los changesets se pueden especificar en distintos lenguajes: sql, xml, json, yaml.
Los changesets sql no tienen nada especial son simples scripts sql que se ejecutan y se controlan los cambios mediante checksums, es necesario crear el script de rollback manualmente.
Los changesets no sql definen una estructura de cambios compleja, de la cual se deriva el sql del cambio y el del rollback cuando es posible.

# Referencias
* https://www.liquibase.org/
* https://www.baeldung.com/liquibase-refactor-schema-of-java-app
* https://reflectoring.io/database-migration-spring-boot-liquibase/
* https://www.adictosaltrabajo.com/2010/09/10/liquibase/

