/**
 * Interfaz que define el contrato para los calculos monetarios de una compra.
 * Cumple con el requerimiento C (Interfaces).
 */
public interface Calculable {

    double calcularSubtotal();

    double calcularDescuento();

    double calcularTotal();
}
