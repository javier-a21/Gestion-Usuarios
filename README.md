Aplicacion web programada en java. La aplicacion consite en un log de usuarios, el usuario tiene la capacidad de Insertar un usuario, insertar 10 usuarios de ejemplo, ver el listado de
usuarios, buscar usuarios por nombre y borrar usuarios por ID.
El programa esta hecho con la version de java: Java version: 1.8.0_202. La version de jboos: jboss-eap-7.4 y la version de maven de Apache Maven 3.6.1, en el entorno de desarroyo de Eclipse.
Como base de datos se utilizara H2 Database guardando los datos en memoria. 

El programa se despliega en el servidor de aplicaciones jboss utilizando el archivo war del proyecto. La version de jboss deve ser jboss-eap-7.4 para que despliegue correctamente.

La estructura del proyecto es la siguiente: 
Estructura del Proyecto
src/main/java: Contiene el código fuente de la aplicación, como los modelos, controladores y servicios.

src/main/webapp: Contiene las páginas JSP y archivos relacionados con la interfaz web.

src/main/resources: Contiene configuraciones de la base de datos y otras propiedades.

pom.xml: El archivo de configuración de Maven que maneja las dependencias del proyecto.

