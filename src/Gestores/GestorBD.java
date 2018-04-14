package Gestores;

import Model.Cliente;
import Model.CuentaAhorros;
import Model.CuentaCorriente;
import Model.Moneda;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Javier on 2/19/2018.
 */
public class GestorBD {

    private Connection conexion;
    private Statement estado;

    public GestorBD() {
       establecerConexionSuperUsuario();
    }

    public void establecerConexionSuperUsuario() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=BaseTarea5Diseno;integratedSecurity=true;";
            conexion = DriverManager.getConnection(connectionUrl);
            estado = conexion.createStatement();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public Statement getEstado() {
        return estado;

    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                conexion = null;
                estado = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void invocarAlerta(String mensaje, Alert.AlertType tipo) {

        Alert nuevaAlerta = new Alert(tipo);
        nuevaAlerta.setTitle("Error");
        nuevaAlerta.setContentText(mensaje);
        nuevaAlerta.showAndWait();

    }

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientesExtraidos = new ArrayList<>();

        return clientesExtraidos;
    }

    public int getLastValueCliente(){
        return 1;
    }

    public void insertarCliente(String nombreCompleto){

    }

    public void crearCuenta(Date fechaSistema, Cliente clienteEscogido, BigDecimal saldoApertura, Moneda tipoMoneda, String tipoCuenta){

    }

    public ArrayList<CuentaAhorros> getCuentasDeAhorro(Cliente clienteBuscar){
        ArrayList<CuentaAhorros> cuentaAhorros = new ArrayList<>();

        return cuentaAhorros;
    }

    public ArrayList<CuentaCorriente> getCuentasCorriente(Cliente clienteBuscar){
        ArrayList<CuentaCorriente> cuentaCorrientes = new ArrayList<>();

        return cuentaCorrientes;
    }
}
