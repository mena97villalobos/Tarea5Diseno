package Model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cliente {
    private static int nextId = 1;
    private int id;
    private String nombreCompleto;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public Cliente(String nombreCompleto){
        this.id = nextId;
        nextId ++;
        this.nombreCompleto = nombreCompleto;
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
}
