# NovaBank — Gestión de Banco

Aplicación web académica desarrollada con **Java 21, Spring Boot, Spring MVC y Thymeleaf**. El sistema simula la gestión básica de clientes, cuentas bancarias, operaciones de depósito/retiro y administración de empleados y cargos.

## Funcionalidades

### Cliente

- Registro de cliente.
- Creación automática de una cuenta de ahorros inicial con saldo `0`.
- Inicio de sesión mediante correo y contraseña.
- Consulta de datos personales, cuentas y saldo total.
- Depósitos y retiros.
- Actualización de nombre y teléfono.
- Cierre de sesión.

### Administrador

- Consulta de clientes, cuentas, empleados y cargos.
- Creación, edición y eliminación de cuentas.
- Activación o desactivación de cuentas.
- Edición de datos de clientes.
- Registro y eliminación de empleados.
- Creación, edición y eliminación de cargos.
- Actualización del salario y cargo de empleados cuando se modifica un empleo.

## Tecnologías

- Java 21
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Security Crypto (`BCryptPasswordEncoder`)
- Maven
- HTML y CSS
- Git y GitHub
- Docker/Render como opciones de despliegue

## Arquitectura

El proyecto utiliza una organización sencilla por responsabilidades:

```text
src/main/java/co/edu/unicordoba/Gestion_Banco/
├── GestionBancoApplication.java
├── config/
│   └── SecurityConfig.java
├── controller/
│   └── BancoController.java
├── interfaces/
│   └── OperacionesBancarias.java
├── model/
│   ├── Persona.java
│   ├── Cliente.java
│   ├── Empleado.java
│   ├── Empleo.java
│   └── CuentaBancaria.java
├── repository/
│   ├── ClienteRepository.java
│   ├── CuentaRepository.java
│   ├── EmpleadoRepository.java
│   └── EmpleoRepository.java
└── ejercicios/
    ├── herencia/
    └── composicion/
```

- **Model:** representa los datos y reglas básicas del dominio.
- **Controller:** recibe las solicitudes HTTP y coordina la lógica de la aplicación.
- **Repository:** administra listas en memoria.
- **Templates:** páginas HTML procesadas con Thymeleaf.
- **Static:** estilos CSS.
- **Config:** configuración de Spring Security.

## Requisitos

- JDK 21.
- Git.
- Maven Wrapper incluido (`mvnw` y `mvnw.cmd`).
- IDE opcional: IntelliJ IDEA, Eclipse o Visual Studio Code.

Verificación:

```powershell
java -version
git --version
```

## Ejecución en Windows

Desde la carpeta que contiene `pom.xml`:

```powershell
.\mvnw.cmd clean spring-boot:run
```

Abrir en el navegador:

```text
http://localhost:8080
```

Para detener el servidor:

```text
Ctrl + C
```

## Credenciales de administrador

El acceso administrativo está definido actualmente en el controlador:

```text
Correo: admin@banco.com
Contraseña: Admin123*
```

Estas credenciales son únicamente para demostración académica. Antes de un uso real deben trasladarse a una configuración segura y nunca quedar escritas directamente en el código.

## Flujo de uso

1. Entrar a la página inicial.
2. Registrar un cliente desde la opción de registro.
3. Iniciar sesión con el correo y contraseña registrados.
4. Consultar el panel, la cuenta y el saldo.
5. Realizar un depósito o retiro.
6. Actualizar los datos permitidos del perfil.
7. Cerrar sesión.
8. Para probar la administración, iniciar sesión con las credenciales de administrador.

## Reglas importantes del sistema

- El documento y el correo no pueden repetirse durante el registro.
- Cada registro crea una cuenta de ahorros inicial.
- El depósito requiere una cantidad positiva.
- Un retiro solo se realiza si la cuenta está activa, la cantidad es positiva y existe saldo suficiente.
- El cliente solo puede operar sobre sus propias cuentas.
- Las rutas administrativas comprueban la existencia del atributo de sesión `admin`.
- Los datos se guardan en listas en memoria, no en MySQL.

## Rutas principales

| Método | Ruta | Función |

| GET | `/` | Página inicial |
| GET | `/login` | Formulario de inicio de sesión |
| GET | `/registro` | Formulario de registro |
| POST | `/registro` | Registrar cliente y crear cuenta |
| POST | `/login` | Validar acceso |
| GET | `/panel` | Panel del cliente |
| POST | `/operacion` | Depósito o retiro |
| POST | `/perfil` | Actualizar nombre y teléfono |
| GET | `/admin` | Panel administrativo |
| GET | `/logout` | Cerrar sesión |

También existen rutas POST administrativas para crear, editar y eliminar cuentas, clientes, empleados y empleos.

## Pruebas recomendadas

- Registro con correo repetido.
- Registro con documento repetido.
- Inicio de sesión con contraseña incorrecta.
- Depósito con valor cero o negativo.
- Retiro superior al saldo disponible.
- Operación sobre una cuenta perteneciente a otro cliente.
- Acceso a `/admin` sin sesión administrativa.
- Desactivación de una cuenta y posterior intento de depósito.
- Creación de un empleado con un cargo existente.
- Eliminación de un cargo que está siendo utilizado.

## Limitaciones actuales

- Almacenamiento temporal en memoria.
- No existe historial de movimientos.
- La autenticación administrativa está codificada en `BancoController`.
- No hay una base de datos persistente.

## GitHub

Repositorio:

[Repositorio en GitHub](https://github.com/Samuel-H875/Gesti-n-Banco)

Comandos básicos:

```powershell
git status
git add .
git commit -m "Actualización de documentación"
git push origin main
```

## Despliegue en Render

1. Subir el proyecto a GitHub.
2. Crear un **Web Service** en Render.
3. Conectar el repositorio y la rama `main`.
4. Seleccionar Docker si el repositorio contiene un `Dockerfile`.
5. Configurar el servicio para escuchar el puerto definido por la variable `PORT`.
6. Revisar los logs de construcción y ejecución.
7. Abrir la URL pública generada.

Si el proyecto conserva `server.port=8080`, conviene adaptar la configuración para que Render pueda asignar dinámicamente el puerto:

```properties
server.port=${PORT:8080}
```

## Conceptos de POO incluidos

- **Herencia:** `Cliente` y `Empleado` heredan de `Persona`; las clases `Asalariado`, `PorHoras` y `Comisionista` heredan de `EmpleadoEjercicio`.
- **Composición:** `Formulario` contiene objetos internos de tipo `Campo`.
- **Encapsulamiento:** los atributos de las clases se manejan mediante métodos getter y setter.
- **Abstracción:** `EmpleadoEjercicio` es una clase abstracta y `OperacionesBancarias` define un contrato de operaciones.
