# PARCIAL 3 - LOGICA Y ALGORITMOS II

## Ejercicio: Sistema de venta de entradas para un cine

## 1. PLANTEAMIENTO DEL PROBLEMA

Un cine necesita desarrollar un pequeno sistema que permita registrar compras de entradas para sus funciones.

Actualmente, el cine ofrece dos tipos de entradas:

- Entrada general: tiene un precio fijo por unidad y permite acceder a una funcion en una sala convencional.
- Entrada VIP: tiene un precio superior, ofrece beneficios adicionales y permite acceder a una sala especial.

El sistema debe permitir registrar el nombre del cliente, seleccionar el tipo de entrada y especificar la cantidad de entradas que desea comprar.

Una vez ingresada la informacion, el sistema debe calcular el valor de la compra, aplicar los descuentos correspondientes y mostrar un resumen con los datos de la operacion.

Adicionalmente, el cine necesita una interfaz grafica sencilla que permita realizar las compras sin necesidad de interactuar directamente con la consola.

## 2. REQUERIMIENTOS FUNCIONALES

El programa debe permitir:

1. Ingresar el nombre del cliente.
2. Seleccionar el tipo de entrada (General o VIP).
3. Ingresar la cantidad de entradas que desea comprar.
4. Calcular el subtotal de la compra.
5. Aplicar el descuento correspondiente segun el tipo de entrada.
6. Calcular el valor total que debe pagar el cliente.
7. Mostrar un resumen de la compra que incluya el nombre del cliente, tipo de entrada, cantidad, precio unitario, subtotal, descuento y total a pagar.
8. Informar al usuario cuando se presente algun error durante el registro de la compra.

## 3. REGLAS DEL NEGOCIO

Para el desarrollo del sistema se deben considerar las siguientes reglas:

### Entrada general

- Precio por unidad: 15000 pesos.
- Descuento: 0 por ciento.
- Beneficios adicionales: No aplica.

### Entrada VIP

- Precio por unidad: 30000 pesos.
- Descuento: 10 por ciento.
- Beneficios adicionales: Acceso a sala VIP y sillas reclinables.

### Otras condiciones

- El descuento VIP se aplica sobre el subtotal de la compra.
- La cantidad de entradas debe ser mayor que cero.
- No se pueden comprar mas de 10 entradas en una sola operacion.
- El nombre del cliente no puede estar vacio.
- Si los datos ingresados no cumplen las reglas, el sistema debe informar el problema y evitar procesar la compra.

## 4. REQUERIMIENTOS TECNICOS

El desarrollo debe integrar obligatoriamente los siguientes conceptos:

### A. HERENCIA Y GENERALIZACION

Utilizar herencia para representar los diferentes tipos de entradas, identificando las caracteristicas y comportamientos que tienen en comun y aquellos que son propios de cada tipo.

### B. POLIMORFISMO Y CLASES ABSTRACTAS

Utilizar clases y metodos abstractos, sobrescritura de metodos mediante Override y polimorfismo mediante referencias de superclase que puedan trabajar con objetos de diferentes subclases.

### C. INTERFACES

Implementar interfaces que permitan definir contratos de comportamiento para las diferentes clases. Se debe utilizar implementacion de multiples interfaces y demostrar polimorfismo mediante interfaces.

### D. MANEJO DE EXCEPCIONES

Implementar manejo de excepciones utilizando try, catch, finally, throw y throws. Tambien se debe crear al menos una excepcion propia para controlar situaciones en las que los datos ingresados no cumplan las reglas del negocio.

Se debe contemplar adicionalmente el ingreso de datos no numericos en los campos que requieren valores numericos.

### E. INTERFAZ GRAFICA CON JAVA SWING

Desarrollar una ventana grafica sencilla que permita registrar las compras de entradas, ingresar los datos necesarios, seleccionar el tipo de entrada, ejecutar la operacion y visualizar los resultados.

La interfaz debe utilizar componentes, contenedores, administradores de distribucion (Layout Managers) y manejo de eventos mediante Listeners.

No es necesario desarrollar una interfaz grafica avanzada. Se busca una ventana pequena, organizada y funcional.

## 5. RESULTADO ESPERADO

Al ejecutar el programa, el usuario debe encontrar una ventana similar a la siguiente:

```text
CINE - Registro de entradas

Nombre del cliente: Juan Perez
Tipo de entrada: Entrada VIP
Cantidad de entradas: 2

RESUMEN DE COMPRA

Cliente: Juan Perez
Tipo: Entrada VIP
Cantidad: 2
Precio unitario: 30000 pesos
Subtotal: 60000 pesos
Descuento (10 por ciento): 6000 pesos
Total a pagar: 54000 pesos
```

El diseno de la ventana puede ser diferente, siempre que cumpla con los requerimientos establecidos.

El sistema debe permitir realizar compras correctamente y mostrar mensajes claros cuando se presenten errores.

El proyecto debe compilar correctamente.