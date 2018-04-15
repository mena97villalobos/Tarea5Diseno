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
//TODO No sabia donde ponerlo, maes para que puedan guardar tuanis las configuraciones, cambian el orden de la tabla configuracion con este orden: 1) comisionCuentaAhorro 2)comisionCuentaCorriente 3)tasaInteresAhorros 4)tasaInteresCorriente 5)cantOperacionesExentas
//TODO Cambiar en la tabla MONEDA: Donde dice "tipoMoneda", cambiarlo por "tipo"
//TODO Agregar un atributo al final de la tabla cuenta que se llame operacionesRealizadas

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
        String sqlCuenta = "INSERT INTO CUENTA(fechaApertura,saldo,tipoCuenta,tipoMoneda,idCliente,operacionesRealizadas) VALUES(?,?,?,?,?,?)";
        int idMoneda = obtenerIdTipoMoneda(tipoMoneda.toString());
        try{
            PreparedStatement insercionCuenta = conexion.prepareStatement(sqlCuenta);

            insercionCuenta.setDate(1,fechaSistema);
            insercionCuenta.setBigDecimal(2,saldoApertura);
            insercionCuenta.setString(3,tipoCuenta);
            insercionCuenta.setInt(4,idMoneda);
            insercionCuenta.setInt(5,Integer.parseInt(clienteIdentificacion));

            if(tipoCuenta.equals("Ahorros"))
                insercionCuenta.setNull(6,Types.INTEGER);
            else
                insercionCuenta.setInt(6,0);

            insercionCuenta.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<CuentaAhorros> getCuentasDeAhorro(Cliente clienteBuscar){
        ArrayList<CuentaAhorros> cuentaAhorros = new ArrayList<>();
        String sqlAhorros = "SELECT NUMEROCUENTA,FECHAAPERTURA,SALDO,MONEDA.TIPO FROM CUENTA,MONEDA WHERE CUENTA.TIPOMONEDA = MONEDA.ID AND CUENTA.IDCLIENTE = ? AND TIPOCUENTA = 'Ahorros' ";

        try{
            PreparedStatement ejecutarCuentaAhorros = conexion.prepareStatement(sqlAhorros);
            ejecutarCuentaAhorros.setInt(1,clienteBuscar.getId());

            ResultSet cuentasAhorroObtenidas = ejecutarCuentaAhorros.executeQuery();

            while(cuentasAhorroObtenidas.next()){
                int numeroCuenta = Integer.parseInt(cuentasAhorroObtenidas.getString("NUMEROCUENTA"));
                Date fechaApertura = cuentasAhorroObtenidas.getDate("FECHAAPERTURA");
                Moneda tipoMoneda = Moneda.valueOf(cuentasAhorroObtenidas.getString("TIPO"));
                BigDecimal saldoActual = cuentasAhorroObtenidas.getBigDecimal("SALDO");

                cuentaAhorros.add(new CuentaAhorros(numeroCuenta,fechaApertura,tipoMoneda,clienteBuscar,saldoActual));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return cuentaAhorros;
    }

    public ArrayList<CuentaCorriente> getCuentasCorriente(Cliente clienteBuscar){
        ArrayList<CuentaCorriente> cuentaCorrientes = new ArrayList<>();

        String sqlCorriente = "SELECT NUMEROCUENTA,FECHAAPERTURA,SALDO,OPERACIONESREALIZADAS,MONEDA.TIPO FROM CUENTA,MONEDA WHERE CUENTA.TIPOMONEDA = MONEDA.ID AND CUENTA.IDCLIENTE = ? AND TIPOCUENTA = 'Corriente' ";

        try{
            PreparedStatement ejecutarCuentaCorriente = conexion.prepareStatement(sqlCorriente);
            ejecutarCuentaCorriente.setInt(1,clienteBuscar.getId());

            ResultSet cuentasCorrienteObtenidas = ejecutarCuentaCorriente.executeQuery();

            while(cuentasCorrienteObtenidas.next()){
                int numeroCuenta = Integer.parseInt(cuentasCorrienteObtenidas.getString("NUMEROCUENTA"));
                Date fechaApertura = cuentasCorrienteObtenidas.getDate("FECHAAPERTURA");
                Moneda tipoMoneda = Moneda.valueOf(cuentasCorrienteObtenidas.getString("TIPO"));
                BigDecimal saldoActual = cuentasCorrienteObtenidas.getBigDecimal("SALDO");
                int operacionesRealizadas = Integer.parseInt(cuentasCorrienteObtenidas.getString("OPERACIONESREALIZADAS"));

                cuentaCorrientes.add(new CuentaCorriente(numeroCuenta,fechaApertura,tipoMoneda,clienteBuscar,saldoActual,operacionesRealizadas));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return cuentaCorrientes;
    }

    public int obtenerIdTipoMoneda(String tipoMoneda){
        String sqlIdMoneda = "SELECT ID FROM MONEDA WHERE tipo = ?";
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

    public BigDecimal obtenerParametros(String parametroAEscoger){
        String sqlParametro = "SELECT "+parametroAEscoger+" FROM CONFIGURACION";
        BigDecimal parametro = new BigDecimal("1"); //Valor por defecto sino hubieran parametros en la base
        try{
            PreparedStatement ejecutarParametro = conexion.prepareStatement(sqlParametro);
            ResultSet parametroObtenido = ejecutarParametro.executeQuery();

            while(parametroObtenido.next()){
                parametro = parametroObtenido.getBigDecimal(parametroAEscoger);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  parametro;
    }
}
