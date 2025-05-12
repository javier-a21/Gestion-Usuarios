package com.usuarios.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@DataSourceDefinition(
    name = "java:/jdbc/MySQLDS",
    className = "com.mysql.cj.jdbc.MysqlDataSource",
    url = "jdbc:mysql://localhost:3306/project_usuarios"
        + "?useSSL=false"
        + "&serverTimezone=UTC"
        + "&allowPublicKeyRetrieval=true",
    user = "root",
    password = "root"
)
public class DataSourceConfig {
    // Este bean se inicializa al desplegar el WAR y registra el DataSource
    // en JNDI bajo java:/jdbc/MySQLDS
}
