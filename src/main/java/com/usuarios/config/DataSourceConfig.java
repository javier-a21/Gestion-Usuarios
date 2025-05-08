package com.usuarios.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
@DataSourceDefinition(
    // debe coincidir con tu persistence.xml:
    name       = "java:/jdbc/MySQLDS",
    className  = "com.mysql.cj.jdbc.MysqlDataSource",
    // añadimos allowPublicKeyRetrieval para evitar el error
    url        = "jdbc:mysql://localhost:3306/project_usuarios"
               + "?useSSL=false"
               + "&serverTimezone=UTC"
               + "&allowPublicKeyRetrieval=true",
    user       = "root",
    password   = "root"
)
public class DataSourceConfig {
    // JBoss levantará este bean al desplegar el WAR y registrará
    // el DataSource en JNDI bajo java:/jdbc/MySQLDS
}
