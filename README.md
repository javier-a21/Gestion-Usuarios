Aplicacion web programada en java. La aplicacion consite en un log de usuarios, el usuario tiene la capacidad de Insertar un usuario, ver el listado de usuarios, buscar usuarios por nombre y borrar usuarios por ID. El programa esta hecho con la version de java: Java version: 1.8.0_202, y utiliza la base de datos sql.

La version de jboss deve ser jboss-eap-7.4 para que despliegue correctamente.

La estructura del proyecto es la siguiente: Estructura del Proyecto src/main/java: Contiene el código fuente de la aplicación, como los modelos, controladores y servicios.

src/main/webapp: Esta carpeta contiene los archivos de configuración que son utilizados por la aplicación. Aquí se encuentran los archivos necesarios para configurar la conexión a la base de datos y otras propiedades necesarias para la aplicación.

persistence.xml: Este archivo se encuentra dentro de src/main/resources/META-INF y configura la unidad de persistencia, que es responsable de conectar la aplicación con la base de datos a través de JPA (Java Persistence API).
src/main/resources: Contiene configuraciones de la base de datos y otras propiedades.

pom.xml: El archivo de configuración de Maven que maneja las dependencias del proyecto.
