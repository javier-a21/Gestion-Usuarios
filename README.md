Aplicacion web programada en java. La aplicacion consite en un log de usuarios, el usuario tiene la capacidad de Insertar un usuario, ver el listado de usuarios, buscar usuarios por nombre y borrar usuarios por ID.
En esta version de la aplicacion se ha migrado de java 8 u jboss 7 a la version de java 21 y jboss 8, para esto se ha cambiado la libreria de javax a jakarta.
Para desplegar la aplicacion deberas usar Java 21 y jboss-eap-8.0.

src/main/webapp: Esta carpeta contiene los archivos de configuración que son utilizados por la aplicación. Aquí se encuentran los archivos necesarios para configurar la conexión a la base de datos y otras propiedades necesarias para la aplicación.

persistence.xml: Este archivo se encuentra dentro de src/main/resources/META-INF y configura la unidad de persistencia, que es responsable de conectar la aplicación con la base de datos a través de JPA (Java Persistence API).
src/main/resources: Contiene configuraciones de la base de datos y otras propiedades.

pom.xml: El archivo de configuración de Maven que maneja las dependencias del proyecto.
