package com.usuarios.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * Con @Singleton + @Startup JBoss/EAP inicializa este bean
 * al desplegar tu WAR y monta el DataSource en JNDI automáticamente.
 */
@Singleton
@Startup
@DataSourceDefinition(
    // ojo al name: debe ser un JNDI “java:/jdbc/…” global
    name       = "java:/jdbc/EmbeddedH2DS",
    className  = "org.h2.jdbcx.JdbcDataSource",
    url        = "jdbc:h2:./data/mydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    user       = "sa",
    password   = ""
)
public class DataSourceConfig {
    // no hace falta nada más
}
