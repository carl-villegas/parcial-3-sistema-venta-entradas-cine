import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz grafica sencilla en Java Swing para el registro de compras de entradas.
 * Cumple con los requerimientos B, C, D y E.
 */
public class VentanaCine extends JFrame {

    private JTextField txtNombreCliente;
    private JComboBox<String> cmbTipoEntrada;
    private JTextField txtCantidad;
    private JTextArea txtResumen;
    private JButton btnComprar;
    private JButton btnLimpiar;
    private JLabel lblEstado;

    public VentanaCine() {
        setTitle("CINE - Registro de entradas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Contenedor principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("CINE - Registro de entradas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Panel central para formulario y botones
        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));

        // Formulario con GridLayout
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(new TitledBorder("Datos de la Compra"));

        panelFormulario.add(new JLabel("Nombre del cliente:"));
        txtNombreCliente = new JTextField();
        panelFormulario.add(txtNombreCliente);

        panelFormulario.add(new JLabel("Tipo de entrada:"));
        cmbTipoEntrada = new JComboBox<>(new String[]{
                "Entrada general",
                "Entrada VIP"
        });
        panelFormulario.add(cmbTipoEntrada);

        panelFormulario.add(new JLabel("Cantidad de entradas:"));
        txtCantidad = new JTextField();
        panelFormulario.add(txtCantidad);

        panelCentro.add(panelFormulario, BorderLayout.NORTH);

        // Panel de botones con FlowLayout
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnComprar = new JButton("Registrar Compra");
        btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnComprar);
        panelBotones.add(btnLimpiar);

        panelCentro.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, BorderLayout.NORTH);

        // Panel inferior para el resumen
        JPanel panelResumen = new JPanel(new BorderLayout(5, 5));
        panelResumen.setBorder(new TitledBorder("Resultado de la Operacion"));

        txtResumen = new JTextArea(10, 38);
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollResumen = new JScrollPane(txtResumen);
        panelResumen.add(scrollResumen, BorderLayout.CENTER);

        lblEstado = new JLabel("Listo para registrar una compra.");
        lblEstado.setFont(new Font("SansSerif", Font.ITALIC, 11));
        panelResumen.add(lblEstado, BorderLayout.SOUTH);

        panelPrincipal.add(panelResumen, BorderLayout.CENTER);

        setContentPane(panelPrincipal);

        // Manejo de eventos mediante ActionListener
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarCompra();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    /**
     * Convierte y valida que el texto de la cantidad sea un numero entero.
     * Utiliza throw y throws.
     */
    private int convertirCantidad(String textoCantidad) throws CompraInvalidaException {
        if (textoCantidad == null || textoCantidad.trim().isEmpty()) {
            throw new CompraInvalidaException("Debe ingresar la cantidad de entradas.");
        }
        try {
            return Integer.parseInt(textoCantidad.trim());
        } catch (NumberFormatException ex) {
            throw new CompraInvalidaException("La cantidad de entradas debe ser un valor numerico entero.");
        }
    }

    private void procesarCompra() {
        boolean operacionExitosa = false;

        try {
            String nombre = txtNombreCliente.getText();
            int cantidad = convertirCantidad(txtCantidad.getText());
            String seleccion = (String) cmbTipoEntrada.getSelectedItem();

            // Polimorfismo mediante referencia de superclase abstracta (Entrada)
            Entrada entrada;
            if ("Entrada general".equals(seleccion)) {
                entrada = new EntradaGeneral(nombre, cantidad);
            } else {
                entrada = new EntradaVIP(nombre, cantidad);
            }

            // Polimorfismo mediante referencias de interfaces (Calculable e Imprimible)
            Calculable operacionCalculable = entrada;
            Imprimible operacionImprimible = entrada;

            double totalCalculado = operacionCalculable.calcularTotal();
            String resumenTexto = operacionImprimible.generarResumen();

            txtResumen.setText(resumenTexto);
            lblEstado.setText("Compra procesada. Total: " + (long) totalCalculado + " pesos.");
            operacionExitosa = true;

        } catch (CompraInvalidaException ex) {
            txtResumen.setText("");
            lblEstado.setText("Error en el registro: " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error en los datos ingresados",
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            // Bloque finally que se ejecuta siempre al finalizar el intento de registro
            if (!operacionExitosa) {
                txtNombreCliente.requestFocusInWindow();
            }
        }
    }

    private void limpiarCampos() {
        txtNombreCliente.setText("");
        cmbTipoEntrada.setSelectedIndex(0);
        txtCantidad.setText("");
        txtResumen.setText("");
        lblEstado.setText("Formulario reiniciado. Listo para registrar una compra.");
        txtNombreCliente.requestFocusInWindow();
    }
}
