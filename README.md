Aplicacion web programada en java. La aplicacion consite en un log de usuarios, el usuario tiene la capacidad de Insertar un usuario, ver el listado de usuarios, buscar usuarios por nombre y borrar usuarios por ID.

Para desplegar la aplicacion deberas usar Java 21 y jboss-eap-8.0.

La aplicación fue configurada para utilizar una base de datos H2 embebida autogestionada por el propio proyecto, evitando dependencias externas de datasources configurados en el servidor de aplicaciones (JBoss EAP 8.0). Se adaptó la configuración de concurrency y logging para asegurar compatibilidad plena.

Para que desplegara correctamente en jboss hubo que solucionar conflictos entre la version de jboss y spring boot.
Se solucionó el error de LoggerFactory:

Se eliminó Logback del WAR (o se excluyó del spring-boot-starter-logging).

Así JBoss usó su propio slf4j-jboss-logmanager sin que Spring Boot se rompiera.

