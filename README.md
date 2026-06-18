# proveedor-service
Microservicio encargado de registrar, listar, actualizar y eliminar proveedores del minimarket. Entrega información de proveedores para otros microservicios, como producto y compra.
## Puerto
```
8085
```
## Tecnologías
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Eureka Client
- Swagger/OpenAPI
- HATEOAS
- Mockito/JUnit
- Docker
- Railway

## Base de datos
```
db_minimarket
```
## Endpoints V1
```
GET /api/v1/proveedores
GET /api/v1/proveedores/{id}
GET /api/v1/proveedores/rut/{rut}
GET /api/v1/proveedores/nombre/{nombre}
GET /api/v1/proveedores/correo/{correo}
POST /api/v1/proveedores
PUT /api/v1/proveedores/{id}
DELETE /api/v1/proveedores/{id}
```
## Endpoints V2 HATEOAS
```
GET /api/v2/proveedores
GET /api/v2/proveedores/{id}
GET /api/v2/proveedores/rut/{rut}
GET /api/v2/proveedores/nombre/{nombre}
POST /api/v2/proveedores
PUT /api/v2/proveedores/{id}
DELETE /api/v2/proveedores/{id}
```
## Swagger
```
http://localhost:8085/swagger-ui.html
```
## Ejemplo JSON
```json
{
  "rut": "12.345.678-9",
  "nombre": "Distribuidora Central",
  "email": "contacto@central.cl",
  "telefono": "+56911111111",
  "direccion": "Santiago"
}
```
## Ejecutar pruebas
```bash
mvn test
```
## Ejecutar localmente
```bash
mvn spring-boot:run
```
## Configuración Railway
```properties
server.port=${PORT:8085}
```
Variables recomendadas:

```properties
SPRING_DATASOURCE_URL=jdbc:mysql://HOST:PORT/railway?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false
SPRING_DATASOURCE_USERNAME=TU_USUARIO
SPRING_DATASOURCE_PASSWORD=TU_PASSWORD
EUREKA_CLIENT_ENABLED=false
```
