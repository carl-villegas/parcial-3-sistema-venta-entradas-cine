/**
 * Excepcion propia para controlar situaciones en las que los datos ingresados
 * no cumplen las reglas del negocio.
 * Cumple con el requerimiento D (Manejo de Excepciones).
 */
public class CompraInvalidaException extends Exception {

    public CompraInvalidaException(String mensaje) {
        super(mensaje);
    }
}
