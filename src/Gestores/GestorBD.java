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
        String sqlClientes = "SELECT * FROM CLIENTE";
        try{
            PreparedStatement obtenerClientes = conexion.prepareStatement(sqlClientes);
            ResultSet clientesObtenidos = obtenerClientes.executeQuery();

            while(clientesObtenidos.next()){
                int idCliente = Integer.parseInt(clientesObtenidos.getString("ID"));
                String nombreCliente = clientesObtenidos.getString("NOMBREAPELLIDOS");

                clientesExtraidos.add(new Cliente(idCliente,nombreCliente));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientesExtraidos;
    }

    public int getLastValueCliente(){
        int nextId = 0;
        String sqlUltimoValor = "SELECT MAX(id)+1 as ULTIMOID FROM CLIENTE";

        try{
            PreparedStatement ejecutarUltimoValor = conexion.prepareStatement(sqlUltimoValor);
            ResultSet siguienteId = ejecutarUltimoValor.executeQuery();

            while(siguienteId.next()){
                nextId = Integer.parseInt(siguienteId.getString("ULTIMOID"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return nextId;
    }

    public void insertarCliente(String nombreCompleto){
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO CLIENTE (nombreApellidos) VALUES (?)");
            statement.setString(1, nombreCompleto);
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void crearCuenta(Date fechaSistema, String clienteIdentificacion, BigDecimal saldoApertura, Moneda tipoMoneda, String tipoCuenta){
        String sqlCuenta = "INSERT INTO CUENTA(fechaApertura,saldo,tipoCuenta,tipoMoneda,idCliente) VALUES(?,?,?,?,?)";
        int idMoneda = obtenerIdTipoMoneda(tipoMoneda.toString());
        try{
            PreparedStatement insercionCuenta = conexion.prepareStatement(sqlCuenta);

            insercionCuenta.setDate(1,fechaSistema);
            insercionCuenta.setBigDecimal(2,saldoApertura);
            insercionCuenta.setString(3,tipoCuenta);
            insercionCuenta.setInt(4,idMoneda);
            insercionCuenta.setInt(5,Integer.parseInt(clienteIdentificacion));

            insercionCuenta.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<CuentaAhorros> getCuentasDeAhorro(Cliente clienteBuscar){
        ArrayList<CuentaAhorros> cuentaAhorros = new ArrayList<>();

        return cuentaAhorros;
    }

    public ArrayList<CuentaCorriente> getCuentasCorriente(Cliente clienteBuscar){
        ArrayList<CuentaCorriente> cuentaCorrientes = new ArrayList<>();

        return cuentaCorrientes;
    }

    public int obtenerIdTipoMoneda(String tipoMoneda){
        String sqlIdMoneda = "SELECT ID FROM MONEDA WHERE tipoMoneda = ?";
        int idEncontrado = 0;
        try{
            PreparedStatement obtenerId = conexion.prepareStatement(sqlIdMoneda);
            obtenerId.setString(1,tipoMoneda);
            ResultSet tupleId = obtenerId.executeQuery();

            while(tupleId.next()){
                idEncontrado = Integer.parseInt(tupleId.getString("ID"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return idEncontrado;
    }

    public void  guardarParametrosConfiguracion(BigDecimal comisionCuentaAhorros,BigDecimal comisionCuentaCorriente,BigDecimal interesesCuentaAhorros,BigDecimal interesesCuentaCorriente,int cantOperacionesExentas){
        String sqlConfiguracion = "";

        if(existenParametros())
            sqlConfiguracion = "UPDATE CONFIGURACION SET COMISIONCUENTAAHORRO = ?, COMISIONCUENTACORRIENTE = ?, TASAINTERESAHORROS = ?, TASAINTERESCORRIENTE = ?, CANTOPERACIONESEXENTAS =?";
        else
            sqlConfiguracion = "INSERT INTO CONFIGURACION VALUES (?,?,?,?,?)";

        try{
            PreparedStatement cambiarParametros = conexion.prepareStatement(sqlConfiguracion) ;
            cambiarParametros.setBigDecimal(1,comisionCuentaAhorros);
            cambiarParametros.setBigDecimal(2,comisionCuentaCorriente);
            cambiarParametros.setBigDecimal(3,interesesCuentaAhorros);
            cambiarParametros.setBigDecimal(4,interesesCuentaCorriente);
            cambiarParametros.setInt(5,cantOperacionesExentas);

            cambiarParametros.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean existenParametros(){
        String sqlParametros = "SELECT  * FROM CONFIGURACION";

        try{
            PreparedStatement verificarParametros = conexion.prepareStatement(sqlParametros);
            ResultSet parametrosObtenidos = verificarParametros.executeQuery();


            if(parametrosObtenidos.next())
                return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
