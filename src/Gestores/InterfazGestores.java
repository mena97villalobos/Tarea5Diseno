package Gestores;

import Model.*;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public interface InterfazGestores {
    void establecerConexionSuperUsuario();

    Connection getConexion();

    Statement getEstado();

    void cerrarConexion();

    static void invocarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert nuevaAlerta = new Alert(tipo);
        nuevaAlerta.setTitle("Error");
        nuevaAlerta.setContentText(mensaje);
        nuevaAlerta.showAndWait();
    }

    ArrayList<Cliente> getClientes();

    int getLastValueCliente();

    void insertarCliente(String nombreCompleto);

    void crearCuenta(java.sql.Date fechaSistema, String clienteIdentificacion, BigDecimal saldoApertura, Moneda tipoMoneda, String tipoCuenta);

    ArrayList<CuentaAhorros> getCuentasDeAhorro(Cliente clienteBuscar);

    ArrayList<CuentaCorriente> getCuentasCorriente(Cliente clienteBuscar);

    int obtenerIdTipoMoneda(String tipoMoneda);

    void  guardarParametrosConfiguracion(BigDecimal comisionCuentaAhorros,BigDecimal comisionCuentaCorriente,BigDecimal interesesCuentaAhorros,BigDecimal interesesCuentaCorriente,int cantOperacionesExentas);

    boolean existenParametros();

    BigDecimal obtenerParametros(String parametroAEscoger);

    java.util.Date obtenerFechaSistema();

    int getLastValueMov();

    ArrayList<Movimiento> getMovimientosCuenta(Cuenta cuenta);

    void modificarCuenta(Cuenta cuenta, String tipoCuenta, boolean esExento);

    void agregarMovimiento(Operacion tipoOperacion, java.sql.Date fechaTransaccion, BigDecimal monto, boolean esExento, Cuenta cuenta);

    ArrayList<Movimiento> verEstadoCuenta(int idCuenta, java.util.Date fechaInicio, java.util.Date fechaFin);

    int getOpRealizadas(int idCuenta);

    void setMovimientosHechos(int idCuenta);

}
