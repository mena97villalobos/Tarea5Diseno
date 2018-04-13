package Model;

import Gestores.GestorBD;

import java.util.ArrayList;
import java.util.Date;

public abstract class Cuenta {
    private static ArrayList<Cuenta> cuentas = GestorBD.getCuentas();

    private static int nextIdCuenta;
    private int numeroCuenta;
    private Date fechaApertura;
    private Moneda moneda;
    private Cliente cliente;


    //Constructor para extraer de la base de datos
    public Cuenta(int numeroCuenta,Date fechaApertura, Moneda moneda, Cliente cliente) {
        this.numeroCuenta = nextIdCuenta;
        this.fechaApertura = fechaApertura;
        this.moneda = moneda;
        this.cliente = cliente;
    }


    public Cuenta(Date fechaApertura, Moneda moneda, Cliente cliente) {
        this.numeroCuenta = nextIdCuenta;
        nextIdCuenta++;

        this.fechaApertura = fechaApertura;
        this.moneda = moneda;
        this.cliente = cliente;
    }

    public abstract void cobrarComision();

    private void pagoIntereses(){

    }

    private void obtenerMovimientos(Date fechaInicio, Date fechaFin){

    }
}
