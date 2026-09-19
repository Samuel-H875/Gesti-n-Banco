# Gestión Banco — NovaBank

Aplicación web bancaria desarrollada con **Java, Spring Boot y Thymeleaf** como proyecto académico para el parcial del corte.

## Tecnologías

- Java 21
- Spring Boot
- Spring MVC
- Thymeleaf
- Maven
- HTML y CSS
- Git y GitHub
- Docker para despliegue

## Objetivo

Crear una aplicación bancaria que permita representar operaciones básicas de un banco y separar las responsabilidades del cliente y del administrador.

El proyecto aplica conceptos de:

- Programación orientada a objetos.
- Herencia.
- Composición.
- Encapsulamiento.
- Separación entre modelo, controlador y repositorio.
- Desarrollo web con Spring Boot.
- Control de versiones con Git.

## Estructura del proyecto

```text
Gestion-Banco/
├── pom.xml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── DOCUMENTACION.md
├── README.md
└── src/
    ├── main/
    │   ├── java/co/edu/unicordoba/Gestion_Banco/
    │   │   ├── GestionBancoApplication.java
    │   │   ├── config/
    │   │   ├── controller/
    │   │   ├── ejercicios/
    │   │   ├── interfaces/
    │   │   ├── model/
    │   │   └── repository/
    │   └── resources/
    │       ├── application.properties
    │       ├── static/
    │       └── templates/
    └── test/
```

### Carpetas principales

| Carpeta | Función |
| `controller` | Gestiona las solicitudes del navegador. |
| `model` | Contiene las clases Cliente, Persona, Empleado y CuentaBancaria. |
| `repository` | Guarda y consulta datos en memoria. |
| `config` | Contiene configuraciones de la aplicación. |
| `ejercicios` | Contiene los ejercicios de herencia y composición. |
| `interfaces` | Define contratos de comportamiento. |
| `templates` | Contiene las páginas HTML de Thymeleaf. |
| `static` | Contiene CSS y recursos estáticos. |

## Funciones por rol

### Cliente

El cliente debe utilizar la aplicación para consultar su información y realizar las operaciones permitidas por la interfaz.

Las funciones pueden incluir:

- Consultar sus datos.
- Consultar su cuenta.
- Consultar el saldo.
- Realizar operaciones bancarias disponibles.
- Actualizar información permitida.
- No acceder a funciones administrativas.

### Administrador

El administrador se encarga de la gestión interna del banco.

Las funciones previstas incluyen:

- Consultar clientes.
- Consultar cuentas.
- Gestionar datos de clientes.
- Modificar o eliminar cuentas, cuando esté implementado.
- Registrar empleados.
- Consultar y administrar empleados.
- Activar o desactivar cuentas, si está implementado.

> Antes de la exposición, comprueba cada función desde el navegador y confirma que esté conectada correctamente con `BancoController.java`.

## Plantillas HTML

Las páginas se encuentran en:

```text
src/main/resources/templates/
```

| Archivo | Función |
| `index.html` | Página inicial. |
| `login.html` | Inicio de sesión. |
| `registrar.html` | Registro. |
| `panel.html` | Panel general. |
| `Banco.html` | Interfaz bancaria. |
| `clientes.html` | Consulta o gestión de clientes. |
| `cuentas.html` | Consulta o gestión de cuentas. |
| `crear-cuenta.html` | Funciones relacionadas con cuentas. |
| `operaciones.html` | Operaciones bancarias. |
| `resultado.html` | Resultados de acciones. |
| `admin.html` | Panel administrativo. |

Las rutas exactas dependen de los métodos definidos en `BancoController.java`.

## Requisitos

Instala:

1. JDK 21 o una versión compatible.
2. Git.
3. IntelliJ IDEA, Eclipse o Visual Studio Code.
4. Conexión a Internet para descargar dependencias.

Verifica las instalaciones:

```powershell
java -version
git --version
```

## Ejecutar en Windows

Abre PowerShell y entra en la carpeta que contiene `pom.xml`:

```powershell
cd "C:\Users\oh220\Desktop\Gestion-Banco"
```

Comprueba la carpeta:

```powershell
dir pom.xml
```

Ejecuta el proyecto:

```powershell
.\mvnw.cmd clean spring-boot:run
```

Cuando Spring Boot inicie correctamente, abre:

```text
http://localhost:8080
```

Para detener la aplicación, presiona:

```text
Ctrl + C
```

## Guía de uso

### 1. Página inicial

1. Abre `http://localhost:8080`.
2. Comprueba que se muestre la página principal.
3. Revisa que los enlaces funcionen.

### 2. Registro

1. Entra en la opción de registro.
2. Completa los campos obligatorios.
3. Comprueba que los datos sean válidos.
4. Envía el formulario.
5. Revisa el mensaje de resultado.

### 3. Inicio de sesión

1. Abre la página de login.
2. Introduce las credenciales configuradas.
3. Comprueba la redirección al panel correspondiente.
4. Verifica que las opciones dependan del rol.

### 4. Prueba como cliente

1. Ingresa al panel del cliente.
2. Consulta los datos personales.
3. Consulta la cuenta y el saldo.
4. Prueba las operaciones disponibles.
5. Comprueba las validaciones.
6. Verifica que no aparezcan funciones administrativas.

### 5. Prueba como administrador

1. Ingresa al panel administrativo.
2. Consulta clientes y cuentas.
3. Prueba las opciones de modificación.
4. Registra un empleado si la opción está disponible.
5. Comprueba las acciones de eliminación o bloqueo.
6. Revisa los mensajes de éxito y error.

## Pruebas antes de la presentación

Realiza las siguientes pruebas:

- Enviar formularios con campos vacíos.
- Introducir datos inválidos.
- Registrar datos válidos.
- Intentar duplicar una cuenta, si existe esa validación.
- Comprobar que el cliente no vea funciones de administrador.
- Comprobar que el administrador pueda gestionar los recursos permitidos.
- Reiniciar la aplicación y verificar qué datos permanecen.

## Almacenamiento

La aplicación utiliza listas en memoria en lugar de MySQL.

Por esta razón:

- No es necesario iniciar MySQL.
- No se necesita una base de datos externa.
- Los datos permanecen mientras la aplicación está activa.
- Los datos pueden desaparecer al reiniciar.
- No se debe utilizar esta versión para información bancaria real.

Una versión real necesitaría una base de datos persistente, cifrado de contraseñas, autorización por roles, auditoría y controles de seguridad.

## Conceptos de programación orientada a objetos

### Herencia

El proyecto contiene clases como:

- `EmpleadoEjercicio`
- `Asalariado`
- `PorHoras`
- `Comisionista`

Las clases especializadas reutilizan atributos y comportamientos de una clase base.

### Composición

La clase `Formulario` contiene objetos internos de tipo `Campo`.

El formulario crea y administra sus campos, representando una relación de composición.

## Git y GitHub

Consultar el estado:

```powershell
git status
```

Añadir cambios:

```powershell
git add .
```

Crear un commit:

```powershell
git commit -m "Actualización del proyecto"
```

Subir cambios:

```powershell
git push origin main
```

Repositorio:

```text
https://github.com/Samuel-H875/Gesti-n-Banco
```

Comprobar el repositorio remoto:

```powershell
git remote -v
```

Corregir la dirección remota:

```powershell
git remote set-url origin https://github.com/Samuel-H875/Gesti-n-Banco.git
```

## Despliegue en Render

1. Sube los cambios a GitHub.
2. Entra en Render.
3. Selecciona **New + → Web Service**.
4. Conecta el repositorio `Gesti-n-Banco`.
5. Selecciona la rama `main`.
6. Selecciona el despliegue mediante Docker.
7. Verifica que el proyecto tenga el `Dockerfile` en la raíz.
8. Crea el servicio.
9. Revisa los logs de construcción y ejecución.
10. Abre la URL generada por Render.

La aplicación debe utilizar el puerto de la variable de entorno `PORT`.

## Problemas frecuentes

### Repository not found

Comprueba:

```powershell
git remote -v
```

Corrige:

```powershell
git remote set-url origin https://github.com/Samuel-H875/Gesti-n-Banco.git
```

### Puerto 8080 ocupado

Cierra la aplicación que está utilizando el puerto o cambia temporalmente la configuración del puerto.

### Error relacionado con MySQL

Comprueba que no existan configuraciones de conexión a MySQL en `application.properties` y que los repositorios no dependan de Spring Data JPA.

### No qualifying bean

Comprueba que el repositorio tenga:

```java
@Repository
```

También verifica que se encuentre dentro del paquete que Spring Boot escanea.

### Página no encontrada

Comprueba:

1. Que la aplicación esté ejecutándose.
2. Que la ruta exista en `BancoController.java`.
3. Que la plantilla esté dentro de `templates`.
4. Que el nombre del archivo coincida con el nombre utilizado en el controlador.

## Qué explicar durante el parcial

Durante la exposición puedes explicar:

1. El objetivo de NovaBank.
2. La estructura del proyecto.
3. La función de `BancoController`.
4. La función de las clases del modelo.
5. La diferencia entre controlador, modelo y repositorio.
6. La separación de roles.
7. La aplicación de herencia.
8. La aplicación de composición.
9. Por qué se utiliza almacenamiento en memoria.
10. Las limitaciones y mejoras futuras.

## Mejoras futuras

- Integrar MySQL o PostgreSQL.
- Guardar datos de forma permanente.
- Cifrar contraseñas.
- Implementar autenticación y autorización completas.
- Agregar historial de movimientos.
- Implementar transferencias.
- Añadir auditoría.
- Crear pruebas unitarias.
- Mejorar la seguridad y la accesibilidad.
