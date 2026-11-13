# Guía y Explicación Detallada - Parcial 3: Sistema de Venta de Entradas para un Cine

Este documento explica **qué se hizo, cómo funciona cada parte del código y por qué se diseñó de esa manera**, cubriendo el 100% de los requerimientos funcionales, reglas del negocio y los 5 requerimientos técnicos (**A, B, C, D y E**) del parcial.

---

## 1. Estructura de Archivos del Proyecto (`parcial 3`)

Todos los archivos `.java` se encuentran en la raíz de `parcial 3` para que compilen directamente junto con `Main.java` (tal como lo exige el repositorio de GitHub Classroom):

```text
parcial 3/
├── Calculable.java              (Interfaz para cálculos monetarios)
├── Imprimible.java              (Interfaz para generación de resumen y beneficios)
├── CompraInvalidaException.java (Excepción personalizada del negocio)
├── Entrada.java                 (Superclase abstracta que implementa ambas interfaces)
├── EntradaGeneral.java          (Subclase concreta para sala convencional)
├── EntradaVIP.java              (Subclase concreta para sala VIP)
├── VentanaCine.java             (Interfaz gráfica en Java Swing)
├── Main.java                    (Punto de entrada del programa)
└── EXPLICACION_PARCIAL_3.md     (Esta guía explicativa)
```

---

## 2. Explicación Clase por Clase (¿Qué, Cómo y Por Qué?)

### 1. Interfaces: `Calculable.java` e `Imprimible.java` (Requerimiento Técnico C)
* **¿Qué pide el parcial?**
  *"Implementar interfaces que permitan definir contratos de comportamiento para las diferentes clases. Se debe utilizar implementación de múltiples interfaces y demostrar polimorfismo mediante interfaces."*
* **¿Qué hicimos y por qué?**
  Creamos dos interfaces separadas según su responsabilidad:
  1. **`Calculable.java`**: Define el contrato matemático que cualquier compra debe cumplir:
     * `double calcularSubtotal();`
     * `double calcularDescuento();`
     * `double calcularTotal();`
  2. **`Imprimible.java`**: Define el contrato de presentación de datos al usuario:
     * `String obtenerTipoEntrada();`
     * `String obtenerBeneficios();`
     * `String generarResumen();`
* **¿Por qué dos interfaces?**
  Porque así la clase `Entrada` puede declararse como:
  ```java
  public abstract class Entrada implements Calculable, Imprimible
  ```
  Demostrando **implementación de múltiples interfaces** en Java (algo que no se puede hacer con clases, pues Java solo permite herencia simple de clases pero múltiple de interfaces).

---

### 2. Superclase Abstracta: `Entrada.java` (Requerimientos Técnicos A y B)
* **¿Qué es?** Es la clase base (`abstract class Entrada`) que generaliza lo que todas las entradas del cine tienen en común.
* **¿Por qué es `abstract`?**
  Porque en el cine no se vende una "Entrada" genérica; siempre debe ser `EntradaGeneral` o `EntradaVIP`. Al ser abstracta, Java impide instanciarla directamente (`new Entrada(...)` no es válido) y nos permite declarar métodos `abstract` que obligan a las subclases a definir sus propias reglas.
* **Atributos comunes (Encapsulamiento):**
  * `private String nombreCliente;`
  * `private int cantidad;`
  * `private double precioUnitario;`
  Son `private` para proteger el estado del objeto y se acceden mediante métodos `get`.
* **Validación de reglas del negocio (`validarDatos`):**
  Se ejecuta en el constructor antes de asignar los atributos:
  1. Si `nombreCliente` está vacío → lanza `throw new CompraInvalidaException("El nombre del cliente no puede estar vacio.")`.
  2. Si `cantidad <= 0` → lanza `throw new CompraInvalidaException("La cantidad de entradas debe ser mayor que cero.")`.
  3. Si `cantidad > 10` → lanza `throw new CompraInvalidaException("No se pueden comprar mas de 10 entradas en una sola operacion.")`.
* **Métodos concretos vs. abstractos:**
  * **Concretos (heredados por todas las subclases sin repetir código):**
    * `calcularSubtotal()`: Retorna `cantidad * precioUnitario`.
    * `calcularTotal()`: Retorna `calcularSubtotal() - calcularDescuento()`.
    * `generarResumen()`: Arma el texto con el formato exacto del resultado esperado (Cliente, Tipo, Cantidad, Precio unitario, Subtotal, Descuento, Beneficios adicionales y Total a pagar).
  * **Abstractos (cada subclase los sobrescribe con `@Override`):**
    * `public abstract double calcularDescuento();`
    * `public abstract int obtenerPorcentajeDescuento();`
    * `public abstract String obtenerTipoEntrada();`
    * `public abstract String obtenerBeneficios();`

---

### 3. Subclases: `EntradaGeneral.java` y `EntradaVIP.java` (Requerimientos A y B)
* **`EntradaGeneral extends Entrada`**:
  * Precio fijo: `15000.0` pesos (`super(nombreCliente, cantidad, PRECIO_GENERAL)`).
  * Sobrescribe (`@Override`) `calcularDescuento()` retornando `0.0` (0 por ciento).
  * Sobrescribe (`@Override`) `obtenerTipoEntrada()` retornando `"Entrada general"`.
  * Sobrescribe (`@Override`) `obtenerBeneficios()` retornando `"No aplica"`.
* **`EntradaVIP extends Entrada`**:
  * Precio fijo: `30000.0` pesos (`super(nombreCliente, cantidad, PRECIO_VIP)`).
  * Sobrescribe (`@Override`) `calcularDescuento()` retornando `calcularSubtotal() * 0.10` (10% sobre el subtotal de la compra).
  * Sobrescribe (`@Override`) `obtenerTipoEntrada()` retornando `"Entrada VIP"`.
  * Sobrescribe (`@Override`) `obtenerBeneficios()` retornando `"Acceso a sala VIP y sillas reclinables"`.

---

### 4. Excepción Propia y Manejo de Errores: `CompraInvalidaException.java` (Requerimiento D)
* **¿Qué pide el parcial?**
  *"Implementar manejo de excepciones utilizando `try`, `catch`, `finally`, `throw` y `throws`. También se debe crear al menos una excepción propia... Se debe contemplar adicionalmente el ingreso de datos no numéricos."*
* **¿Dónde se usa cada palabra clave?**
  * **`CompraInvalidaException extends Exception`**: Es nuestra excepción personalizada.
  * **`throws`**: En las firmas `public Entrada(...) throws CompraInvalidaException`, `private void validarDatos(...) throws CompraInvalidaException` y `private int convertirCantidad(...) throws CompraInvalidaException`, indicando que esos métodos pueden propagar esta excepción.
  * **`throw`**: Dentro de `validarDatos` y `convertirCantidad` (`throw new CompraInvalidaException(...)`) cuando un dato incumple una regla o no es numérico (`NumberFormatException`).
  * **`try` y `catch`**: En `procesarCompra()` dentro de `VentanaCine.java` para intentar registrar la compra y, si ocurre un error, detener el proceso y mostrar un cuadro de diálogo (`JOptionPane.showMessageDialog`) informando al usuario.
  * **`finally`**: Al final de `procesarCompra()`, un bloque `finally` que se ejecuta siempre (haya o no error) para devolver el foco a la caja de texto si la compra no fue exitosa.

---

### 5. Interfaz Gráfica: `VentanaCine.java` y `Main.java` (Requerimiento E)
* **¿Qué pide el parcial?**
  *"Desarrollar una ventana gráfica sencilla... La interfaz debe utilizar componentes, contenedores, administradores de distribución (Layout Managers) y manejo de eventos mediante Listeners."*
* **¿Cómo está construida `VentanaCine`?**
  * **Contenedores:** `JFrame` (ventana), varios `JPanel` para agrupar secciones y un `JScrollPane` para el área de resumen.
  * **Layout Managers (Administradores de distribución):**
    * `BorderLayout`: Organiza la ventana en norte (título/formulario) y centro (resumen).
    * `GridLayout(3, 2, 10, 10)`: Organiza las etiquetas y cajas de texto del formulario en una cuadrícula de 3 filas por 2 columnas.
    * `FlowLayout`: Alinea los botones `"Registrar Compra"` y `"Limpiar"` horizontalmente en el centro.
  * **Componentes:** `JLabel`, `JTextField`, `JComboBox<String>`, `JButton` y `JTextArea`.
  * **Listeners (Manejo de eventos):** Se implementan objetos `new ActionListener()` con `@Override public void actionPerformed(ActionEvent e)` asociados a los botones `btnComprar` y `btnLimpiar`.
* **¿Cómo se demuestra el doble Polimorfismo en `procesarCompra()`?**
  1. **Polimorfismo por superclase abstracta:**
     ```java
     Entrada entrada;
     if ("Entrada general".equals(seleccion)) {
         entrada = new EntradaGeneral(nombre, cantidad);
     } else {
         entrada = new EntradaVIP(nombre, cantidad);
     }
     ```
  2. **Polimorfismo por interfaces:**
     ```java
     Calculable operacionCalculable = entrada;
     Imprimible operacionImprimible = entrada;

     double totalCalculado = operacionCalculable.calcularTotal();
     String resumenTexto = operacionImprimible.generarResumen();
     ```
     Aquí el mismo objeto es tratado polimórficamente a través de su superclase `Entrada`, a través de la interfaz `Calculable` y a través de la interfaz `Imprimible`.
