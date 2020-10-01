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

# Alters
Debemos tener presentes que los changesets se aplicaran en orden incremental siempre que no se haya ejecutado anteriormente por tanto esperan encontrar la base de datos en un estado concreto o si no el script fallara necesariamente impidiendo que se aplique la migracion.
Por ello el orden de los distintos scripts es relevante y aplican las mismas normas que con sql basico, por ejemplo si queremos realizar un alter sobre una tabla esta debe existir previamente, etc.

# Baselines
En liquibase no existe el concepto de baseline, todo se gestiona con changelogs, un changelog que refleje el estado de la base de datos debe crearse previo a utilizar las migraciones con liquibase, liquibase proporciona un comando "generateChangelog" que permite la generacion automatica de estos changelogs.
```
liquibase --changeLogFile=dbchangelog.xml generateChangeLog
```
Una vez que este changelog inicial se ha generado se aplican la operativa normal de liquibase aplicando los distintos changesets de forma incremental y activando las migraciones.

Del mismo modo si en algun momento queremos resetear el uso de liquibase y generar distintos snapshots del estado de la bd se puede utilizar este comando para que nos genere distintas lineas base.

# Rollbacks
En liquibase se pueden generar scripts de rollback automaticamente que pueden aplicarse para deshacer un cambio que se hara realizado previamente.
Los scripts de rollback ademas de ser generados automaticamente tambien pueden ser creados manualmente y liquibase se encargaria de aplicar uno u otro para retroceder la base de datos a una determinada version, no obstante para utilizar esta funcionalidad debe recordarse que estas versiones tienen que ser todas controladas mediante liquibase.

```
<changeSet id="test_rollback" author="vass">
    <createTable tableName="liquibase_tutorial">
        <column name="id" type="int"/>
        <column name="nombre" type="varchar(36)"/>
        <column name="descripion" type="varchar(36)"/>
    </createTable>
    <rollback>
        <dropTable tableName="liquibase_tutorial"/>
    </rollback>
</changeSet>
```
Sobre el ejemplo anterior podriamos omitir el tag ``<rollback>`` ya que seria generado automaticamente.

Una vez tenemos generados los rollbacks podemos aplicarlos utilizando el comando especifico de rollbackindicando una version, fecha o numero de rollbacks a aplicar:

rollback a tag:
```
mvn liquibase:rollback "-Dliquibase.rollbackTag=1.0"
```

rollback a fecha:
```
mvn liquibase:rollback "-Dliquibase.rollbackDate=Jun 03, 2017"
```

rollback a numero:
```
mvn liquibase:rollback "-Dliquibase.rollbackCount=2"
```

generar sql rollbacks:
```
mvn liquibase:rollbacksql 
```

La integracion con spring boot no aplica automaticamente los rollbacks unicamente las migraciones, no obstante se puede configurar el plugin de maven para utilizar los comandos anteriores y generar los ficheros de rollback y/o aplicarlos, la generacion del fichero de rollback puede realizarse automaticamente desde la integracion con springboot mediante la propiedad ``spring.liquibase.rollback-file``

```
<plugin>  
    <groupId>org.liquibase</groupId>  
    <artifactId>liquibase-maven-plugin</artifactId>  
    <configuration>  
    <propertyFileWillOverride>true</propertyFileWillOverride>  
    <propertyFile>liquibase.properties</propertyFile>  
    </configuration>  
</plugin>  
```

# Referencias
* https://www.liquibase.org/
* https://www.baeldung.com/liquibase-refactor-schema-of-java-app
* https://www.baeldung.com/liquibase-rollback
* https://reflectoring.io/database-migration-spring-boot-liquibase/
* https://www.adictosaltrabajo.com/2010/09/10/liquibase/

