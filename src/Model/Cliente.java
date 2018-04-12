package Model;

import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nombreCompleto;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

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

    private void retiro(){

    }

    private void deposito(){

    }
}
