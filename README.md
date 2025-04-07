Aplicacion web programada en java. La aplicacion consite en un log de usuarios, el usuario tiene la capacidad de Insertar un usuario, insertar 10 usuarios de ejemplo, ver el listado de usuarios, buscar usuarios por nombre y borrar usuarios por ID. El programa esta hecho con la version de java: Java version: 1.8.0_202. La version de jboos: jboss-eap-7.4 y la version de maven de Apache Maven 3.6.1, en el entorno de desarroyo de Eclipse.

El programa se despliega en el servidor de aplicaciones jboss utilizando el archivo war del proyecto. La version de jboss deve ser jboss-eap-7.4 para que despliegue correctamente la version del proyecto de la rama main. Para desplegar las otras dos versiones de las ramas jakarta-DataBaseH2 y migracion-jakarta, deberas usar Java 21 y jboss-eap-8.0.

En el repositorio hay tres Branches: ** Main**, la aplicacion original hecha en Java version: 1.8.0_202 y jboss 7 sin base de datos que utiliza las librerias de Javax para las rutas web. migracion-jakarta, mismo programa pero migrado a Java 21, el servidor Jboss 8 y migracion de Javax a Jakarta, y jakarta-DataBaseH2 mismo programa y versiones que migracion-jakarta pero con uso de base de datos, como base de datos se utilizara H2 Database guardando los datos en memoria.

La estructura del proyecto es la siguiente: Estructura del Proyecto src/main/java: Contiene el código fuente de la aplicación, como los modelos, controladores y servicios.

src/main/webapp: Esta carpeta contiene los archivos de configuración que son utilizados por la aplicación. Aquí se encuentran los archivos necesarios para configurar la conexión a la base de datos y otras propiedades necesarias para la aplicación.

persistence.xml: Este archivo se encuentra dentro de src/main/resources/META-INF y configura la unidad de persistencia, que es responsable de conectar la aplicación con la base de datos a través de JPA (Java Persistence API).
src/main/resources: Contiene configuraciones de la base de datos y otras propiedades.

pom.xml: El archivo de configuración de Maven que maneja las dependencias del proyecto.
