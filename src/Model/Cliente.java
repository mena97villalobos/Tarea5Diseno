package Model;

import Gestores.Singleton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Cliente {

    private static ArrayList<Cliente> clientes = obtenerClientes();

    private static int nextId = obtenerUltimoIdCliente(); // Esta funcion busca en la base si hay clientes y extrae la ultima identificacion, si no hay clientes deberia poner el valor que este en la semilla de esa tabla, se asume que deberia ser 1, en caso de no ser asi, buscar algo que devuelva el numero donde quedo la semilla
    private int id;
    private String nombreCompleto;

    private ArrayList<CuentaAhorros> cuentasAhorros = new ArrayList<>();
    private ArrayList<CuentaCorriente> cuentasCorriente = new ArrayList<>();

    public Cliente(String nombreCompleto){
        this.id = nextId;
        nextId ++;
        this.nombreCompleto = nombreCompleto;

        clientes.add(this);
    }

    //Constructor para extraer datos de la base
    public Cliente(int id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.cuentasAhorros = getCuentasAhorros();
        this.cuentasCorriente = getCuentasCorriente();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void retiro(int numeroCuenta, BigDecimal monto,Date fechaTransaccion){

    }

    public void deposito(int numeroCuenta, BigDecimal monto,Date fechaTransaccion){

    }

    public void compra_comercio(int numeroCuenta, BigDecimal monto,Date fechaTransaccion){

    }

    public void retiro_cajero(int numeroCuenta, BigDecimal monto,Date fechaTransaccion){

    }

    public static ArrayList<Cliente> getClientes(){
        return clientes;
    };

    private static ArrayList<Cliente> obtenerClientes(){
        return Singleton.getInstance().getGestor().getClientes();
    }

    private static int obtenerUltimoIdCliente(){
        return Singleton.getInstance().getGestor().getLastValueCliente();
    }

    public void agregarCuentaAhorros(CuentaAhorros cuentaAhorro){
        cuentasAhorros.add(cuentaAhorro);
    }

    public void agregarCuentaCorriente(CuentaCorriente cuentaCorriente){
        cuentasCorriente.add(cuentaCorriente);
    }

    public ArrayList<CuentaAhorros> getCuentasAhorros(){

        ArrayList<CuentaAhorros> cuentasAhorroCliente = Singleton.getInstance().getGestor().getCuentasDeAhorro(this);
        return cuentasAhorroCliente;
    }

    public ArrayList<CuentaCorriente> getCuentasCorriente() {

        ArrayList<CuentaCorriente> cuentasCorrienteCliente = Singleton.getInstance().getGestor().getCuentasCorriente(this);

        return cuentasCorrienteCliente;
    }

    public static ArrayList<String> getNombresClientes(){
        ArrayList<String> nombreClientes = new ArrayList<>();

        for(int i=0; i<clientes.size();i++){
            nombreClientes.add(clientes.get(i).getId()+"-"+clientes.get(i).getNombreCompleto());
        }
        return nombreClientes;
    }

    public static Cliente filtrarCliente(String idCliente){
        for(int i =0; i< clientes.size();i++){
            if(clientes.get(i).getId() == Integer.parseInt(idCliente))
                return clientes.get(i);
        }
        return null;
    }

    public ArrayList<String> getNumeroCuentas(){
        ArrayList<String> numeroCuentas = new ArrayList<>();

        for(int i =0;i< cuentasAhorros.size();i++){
            numeroCuentas.add(cuentasAhorros.get(i).getNumeroCuenta()+"-Ahorros");
        }
        for(int i =0;i< cuentasCorriente.size();i++){
            numeroCuentas.add(cuentasCorriente.get(i).getNumeroCuenta()+"-Corriente");
        }
        return numeroCuentas;
    }

}
