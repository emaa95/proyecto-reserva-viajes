# ✈️ Gestor de Vuelos - Proyecto Final de Programación en Java
## Estudiantes: 
- Chiletti, Emanuel,
- Mazzucchelli Mariano,
- Tappero, Maria Luz.

## 📌 Descripción

Este sistema de gestión de vuelos fue desarrollado como proyecto final de la materia de Programación. El objetivo es ofrecer una solución interactiva y orientada a objetos que permita gestionar de forma eficiente los vuelos, clientes, vendedores, paquetes turísticos y reservas.  
El sistema incluye menús interactivos en consola para facilitar la navegación y el uso del programa por parte del usuario final.

---

## 🧩 Estructura del Proyecto

El sistema está compuesto por las siguientes **clases principales**:

- **Persona**: Clase base de la cual heredan otras clases como Cliente y Vendedor.
- **Cliente**: Hereda de Persona. Contiene los datos del pasajero.
- **Vendedor**: Hereda de Persona. Representa a los empleados encargados de gestionar ventas.
- **PaqueteTuristico**: Representa un conjunto de servicios ofrecidos al cliente (vuelos, fechas, destinos, etc.).
- **Vuelo**: Contiene la información de vuelos (origen, destino, hora, precio, etc.).
- **Reserva**: Representa una reserva realizada por un cliente, enlazando con un vuelo o paquete.

---

## 🧭 Menús del Sistema

El programa funciona a través de menús interactivos por consola:

1. **Menú Principal**
   - Acceso a todos los submenús del sistema.
   - Opciones: Clientes, Vendedor, Informes, Paquetes, Reservas, Vuelos, Salir.

2. **Menú Cliente**
   - Alta, baja y modificación de clientes.
   - Listado de todos los clientes.

3. **Menú Vendedor**
   - Alta, baja, modificación de vendedores.
   - Listado de todos los vendedores.

4. **Menú Vuelo**
   - Carga de nuevos vuelos.
   - Modificación, eliminación de vuelos.
   - Listado de todos los vuelos.
   - Búsqueda de vuelo por número.

5. **Menú Paquetes**
   - Carga de nuevos paquetes.
   - Modificación y eliminación de paquetes.
   - Listado de todos los paquetes.

6. **Menú Reservas**
   - Carga de nuevas reservas.
   - Edición de reservas.
   - Listado de todas las reservas.

7. **Menú Informes**
   - Muestra estadísticas o listados especiales, como:
     - Listado de reservas por precio ascendente.
     - Listado de reservas por precio descendente.
     - Busqueda de vuelos por número.
     - Lista de reservas por estado (CANCELADA, CONFIRMADA, RECHAZADA, PENDIENTE, PAGADA)
     - Listar paquetes por destino.
     - Mostrar estado actual de todas las reservas.

---

## 🧑‍💻 Instructivo de Uso

### ▶️ Requisitos

- Tener instalado **Java 17** (o compatible).
- Tener un IDE como **Visual Studio Code**, compilar y ejecutar desde consola.

### ▶️ Cómo ejecutar el sistema

1. Clonar o descargar el repositorio del proyecto.

```bash
   git clone https://github.com/emaa95/proyecto-reserva-viajes.git
```

2. Compilar todas las clases del sistema.

3. Ejecutar la clase `App` para iniciar el menú principal.


## 💡 Casos de Uso

---

##### ✅ Caso de Uso 1: Registrar un nuevo cliente

**Flujo principal**:
1. El usuario selecciona la opción `Clientes` en el menú principal.
2. Elige la opción `Registrar un cliente`.
3. El sistema solicita los datos del cliente (nombre, apellido, DNI, teléfono, email).
4. El usuario ingresa los datos requeridos.
5. El sistema confirma el registro del nuevo cliente y vuelve al menú.

En dicho menú se puede editar, eliminar y obtener un listado de clientes.

---

##### ✅ Caso de Uso 2: Registrar un nuevo vendedor

**Flujo principal**:
1. El usuario selecciona la opción `Vendedores` en el menú principal.
2. Elige la opción `Registrar un vendedor`.
3. El sistema solicita los datos del vendedor (DNI, nombre, apellido, legajo, condición laboral).
4. El usuario ingresa los datos requeridos.
5. El sistema confirma el registro del nuevo vendedor y vuelve al menú.

En dicho menú se puede editar, eliminar y obtener un listado de vendedores.

---

##### ✅ Caso de Uso 3: Crear un nuevo vuelo

**Flujo principal**:
1. El usuario selecciona la opción `Vuelos` en el menú principal.
2. Elige la opción `Registrar nuevo vuelo`.
3. El sistema solicita los datos del vuelo (origen, destino, fecha, hora, precio base, duración, cantidad de asientos, etc.).
4. El usuario ingresa los datos.
5. El sistema confirma que el vuelo fue agregado correctamente.

En dicho menú se puede editar, eliminar, obtener un listado de vuelos y buscar vuelos por ID.

---

##### ✅ Caso de Uso 4: Registrar un paquete turístico

**Flujo principal**:
1. El usuario selecciona la opción `Paquetes` en el menú principal.
2. Elige la opción `Registrar un paquete`.
3. El sistema solicita los datos del paquete (descripción, fecha de salida, fecha de regreso, destinos, vuelos incluidos, precio total, etc.).
4. El usuario ingresa los datos y confirma.
5. El sistema guarda el paquete.

En dicho menú se puede editar, eliminar y obtener un listado de paquetes.

---

##### ✅ Caso de Uso 5: Realizar una reserva

**Flujo principal**:
1. El usuario selecciona la opción `Reservas` en el menú principal.
2. Elige la opción `Nueva reserva`.
3. El sistema solicita los datos de la reserva (DNI del cliente, legajo del vendedor, cantidad de pasajeros, selección de vuelo o paquete).
4. El sistema genera y muestra los datos de la reserva, incluyendo precio final y método de pago.
5. El usuario confirma.
6. El sistema guarda la reserva.

En dicho menú se puede editar reservas y obtener un listado completo.

---

##### ✅ Caso de Uso 6: Menú de informes

**Flujo principal**:
1. El usuario selecciona la opción `Informes` desde el menú principal.
2. Elige el tipo de informe deseado:
   - `Listar reservas ordenadas por precio ascendente`
   - `Listar reservas ordenadas por precio descendente`
   - `Buscar vuelo por número (búsqueda binaria)`
   - ` Listar reservas por estado  (CANCELADA, CONFIRMADA, RECHAZADA, PENDIENTE, PAGADA)`
   - `Listar paquetes por destino`
   - `Buscar vuelo por número`
   - `Mostrar estado actual de todas las reservas`
4. Por ejemplo, seleccionar la opción de listar paquetes por destino y sistema solicita un criterio de búsqueda (ejemplo: destino = Miami).
5. El sistema muestra un informe detallado con los resultados que coincidan.
---

