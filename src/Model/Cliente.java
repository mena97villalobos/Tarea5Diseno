package Model;

import Gestores.GestorBD;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cliente {

    private static ArrayList<Cliente> clientes = GestorBD.getClientes();

    private static int nextId = GestorBD.getLastValueCliente(); // Esta funcion busca en la base si hay clientes y extrae la ultima identificacion, si no hay clientes deberia poner el valor que este en la semilla de esa tabla, se asume que deberia ser 1, en caso de no ser asi, buscar algo que devuelva el numero donde quedo la semilla
    private int id;
    private String nombreCompleto;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public Cliente(String nombreCompleto){
        this.id = nextId;
        nextId ++;
        this.nombreCompleto = nombreCompleto;

        clientes.add(this);
    }

    //Constructor para jalar datos de la base
    public Cliente(int id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
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

    private void retiro(int numeroCuenta, BigDecimal monto){

    }

    private void deposito(){

    }

    public static ArrayList<Cliente> getClientes(){
        return clientes;
    };
}
