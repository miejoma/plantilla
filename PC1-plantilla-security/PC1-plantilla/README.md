# Plantilla PC1 + Spring Security (JWT)

Es el ejercicio PC1 E2 (CRUD de Observacion con FK a Astronomo y Telescopio + reporte de facturación)
con la capa de seguridad del taller de la semana 4 ya integrada, todo comentado.

Stack (el mismo de tu PC1): Spring Boot 4.1.1 · Java 21 · PostgreSQL · springdoc 3.0.0 · jjwt 0.12.5.
Paquete base: `org.example.pc1` (si cambias el paquete, TODO debe quedar dentro de él, incluido `security/`).

## Pasos para levantarlo
1. Crea una BD vacía en PostgreSQL (el nombre debe coincidir con `spring.datasource.url`) y ajusta usuario/clave en `application.properties`.
2. Ejecuta la app UNA vez (Hibernate crea `astronomo`, `telescopio`, `observacion`, `users`, `roles`, `user_role`) y deténla.
3. Ejecuta `data-inicial.sql` en tu cliente SQL (roles, usuarios con clave 12345 y datos de prueba).
4. Vuelve a ejecutar la app. Swagger: http://localhost:8080/swagger-ui.html
5. `POST /ingresos` con `{"username":"admin1","password":"12345"}` -> copia el token -> botón **Authorize** -> prueba los endpoints.
   (O usa `pruebas.http` en IntelliJ: ya trae todas las peticiones, incluidas las negativas 401/403/400/404.)

## Permisos de ejemplo
| Endpoint | Rol(es) |
|---|---|
| POST /api/observacion/registrar | ADMINISTRADOR, OPERADOR |
| GET /api/observacion/listar | ADMINISTRADOR, OPERADOR |
| PUT /api/observacion/actualizar/{id} | ADMINISTRADOR |
| DELETE /api/observacion/eliminar/{id} | ADMINISTRADOR |
| GET /api/observacion/facturacionAstronomoCategoria | ADMINISTRADOR |
| POST /ingresos | público |

Usuarios: `admin1` (ADMINISTRADOR) y `operador1` (OPERADOR), ambos con clave `12345`.

## Qué es de PC1 y qué es nuevo
- **De PC1 (sin cambiar la lógica):** entity, dto, repository (+projection), service, exception.
- **Nuevo:** carpeta `security/` (las 10 clases del taller: solo cambió el `package`, el código es idéntico; se agregaron comentarios),
  `@PreAuthorize` en el controller, `config/OpenApiConfig` (botón Authorize en Swagger), dependencias de seguridad/JWT en el `pom.xml`,
  `jwt.secret` en `application.properties` y `data-inicial.sql`.

👉 Lee **MODIFICACIONES.md**: cómo adaptar la plantilla a otro dominio y las variaciones que pueden pedir en el examen.
