# SPRING_BOOT_RESTAPI_CRUD

Crud con 2 tablas (client,role) las cuales se realizaran con sus respectivos test con junit,mockito, jacoco y pruebas de ello con SonarQube.

# Identificar el proyecto

* Lenguaje: Java
* Versión de Lenguaje: 17
* Framework: Spring Boot
* Versión de Framework: 3.1.4
* Base de Datos: Oracle
* Versión de Base de Datos: 19c

## Utilidades para Testing

* Jacoco: version 0.8.10
* mockito: versión 5.4.0
* Junit
* SonarQube: 10.2.1.78527
* Maven: 3.9.5 (mvn)

## Pasos previos.

1. Tener instalado SonarQube
2. Tener instalado Postman para pruebas del Api
3. Tener instalado la versión de Java requerido
4. Tener instalado la en variables de entorno el maven requerido
5. Tener instalado Oracle Database
6. Montar la base de datos de la carpeta Backup.

## Modificaciónes del proyecto

En application.properties modificar en base a su configuración de la Base de Datos Oracle.

* spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl
* spring.datasource.username=bryanar_
* spring.datasource.password=123456
* spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

En el pom.xml modificar lo siguiente:

* <sonar.login>squ_2f429fbe4890ef8817dfd2aac4927e425b74a017</sonar.login>
* El token empleado lo obtendras en base a tu propio SonarQube

## Arquitectura básica del proyecto

El proyecto cuenta con la siguiente arquitectura.

* Domain (Client - Class, Role - Class)
* Dao (ClientDao - Interface, RoleDao - Interface)
* Service (ClienteService - Interface,RoleService - Interface)
  * Imp (ClientServiceImp - Class, RoleServiceImp - Class)
* Controller (ClienteController - Class, RoleController - Class)

La interacción es (Controller -> Imp -> (Service,Dao) -> Domain )

# Rutas del proyecto

Proyecto en ruta: localhost:8080

* ClientController:
  * Get: /clients	/client/{id}
  * Post: /client
  * Put: /client/{id}
  * Delete: /client/{id}
* RoleController:
  * Get: /roles	/role/{id}
  * Post: /role
  * Put: /role/{id}
  * Delete: /role/{id}

# Testing realizados

* Archivos Dao
* Archivos ServiceImp
* Archivos Controller

# Pasos para testing con SonarQube

1. Con Maven del proyecto, realizar clean and install.
2. Ejecutar el siguiente comando en terminal del proyecto: mvn sonar:sonar
3. Ir a la ruta de SonarQube: localhost:9000
4. Incluir usuario y contraseña: Si es por defecto con las siguiente:
   1. Usuario: admin
   2. Contraseña: admin
5. Verificar el analisis del proyecto.
