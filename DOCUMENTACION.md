# Gestión Banco - ampliación

## Interfaces
- **Cliente:** registro, creación de cuenta, consulta, depósitos y retiros.
- **Administrador:** panel de administración, registro y eliminación de empleados, consulta de totales.

> El proyecto usa listas en memoria; al reiniciar la aplicación los datos se pierden.

## Dos ejercicios del documento
1. **Herencia 1.2 — Empleados por tipo de contrato:** `ejercicios/herencia`. Se usa una clase abstracta y polimorfismo; la bonificación está en la clase base porque es común.
2. **Composición 2.4 — Formulario y campos:** `ejercicios/composicion`. El formulario crea internamente sus campos y controla su validación.

## Error ficticio para documentar
**Error simulado:** al registrar una cuenta, el sistema mostraba `Cliente no encontrado` aunque el cliente existía.

**Causa supuesta:** el formulario enviaba el nombre del cliente, pero el controlador buscaba el número de cliente.

**Solución supuesta:** modificar el `<option>` del formulario para enviar `numeroCliente` como `value` y validar la existencia antes de crear la cuenta.

**Prueba de solución:** registrar un cliente, crear una cuenta seleccionando su número y comprobar que la cuenta aparece en `/cuentas`.

Este error es ficticio y se incluye únicamente como ejemplo de documentación; no afirma que haya ocurrido en el proyecto original.
