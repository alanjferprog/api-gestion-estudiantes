# 📚 API de Gestión de Estudiantes y Cursos

Este proyecto es una API REST creada con **Spring Boot** que permite gestionar estudiantes y cursos. Incluye funcionalidades de autenticación mediante **JWT**, validaciones, manejo de errores personalizado, y separación clara mediante **DTOs** y capas de servicio.

---

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA (con Hibernate como proveedor de JPA)
- Spring Security
- JWT (Json Web Token)
- MySQL
- Maven
- Postman (para pruebas)
- Lombok

---

## Funcionalidades

### Autenticación

- Registro de usuarios
- Login y generación de token JWT
- Acceso protegido a endpoints mediante token

### Estudiantes

- Crear estudiante
- Obtener todos los estudiantes
- Actualizar estudiante
- Eliminar estudiante

### Cursos

- Crear curso
- Obtener todos los cursos
- Actualizar curso
- Eliminar curso

---

## Requisitos previos

- Java 17 o superior
- MySQL instalado y corriendo
- Postman o similar para probar endpoints
- IDE (Eclipse, IntelliJ, VS Code)

---

## Configuración

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/alanjferprog/api-gestion-estudiantes
   ```

2. Configurá la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nombre_basedatos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

3. Instalá dependencias y ejecutá la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---

## Uso de JWT

1. Registrate con `/auth/registro`
2. Iniciá sesión con `/auth/login`
3. Copiá el token generado
4. En Postman, agregá el token en la pestaña **Authorization** usando tipo **Bearer Token**

---

## Pruebas con Postman

Incluye una colección de Postman lista para importar en la carpeta `/postman`.

---

## Autor

- Alan Fernandez
- LinkedIn: [Alanjferprog](https://www.linkedin.com/in/alanjferprog/)
- GitHub: [Alanjferprog](https://github.com/alanjferprog)
