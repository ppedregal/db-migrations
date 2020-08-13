# Database Migrations

## Liquibase vs Flyway
Son dos herramientas muy similares que permiten gestionar la base de datos asociada a nuestros proyectos de una forma lo mas automatizada posible.  
Liquibase es mas complejo pero ofrece un conjunto de funcionalidades adicionales sobre flyway
* Ambos permiten utilizar scripts sql para definir nuestros esquemas y aplicar cambios en la bd que seran aplicados:
    * Flyway controla la ejecucion de estos scripts unicamente mediante nomeclatura
    * Liquibase no impone ninguna nomenclatura pero nos obliga en cambio a tener un fichero maestro donde identificamos los cambios y el orden en el que deben ser aplicados
* Liquibase permite definir los conjuntos de cambios con su propio lenguaje xml, el cual nos permite entre otras cosas:
    * automatizar la creacion de los scripts de sql para hacer y deshacer los cambios
    * independencia de la base de datos, ya que se encargara de la traduccion desde el xml intermedio al sql especifico de la plataforma
Generalmente si solo estamos interesados en scripts sql, flyway deberia ser la alternativa al ser tambien mas sencillo requerir apenas configuracion.

