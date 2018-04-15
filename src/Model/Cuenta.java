package Model;

import Gestores.GestorBD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public abstract class Cuenta {
    private static int nextIdCuenta;
    private int numeroCuenta;
    private Date fechaApertura;
    private Moneda moneda;
    private Cliente cliente;
    private BigDecimal saldo;


    //Constructor para extraer de la base de datos

    public Cuenta(Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldo) {
        this.numeroCuenta = nextIdCuenta;
        nextIdCuenta++;

        this.fechaApertura = fechaApertura;
        this.moneda = moneda;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public Cuenta(int numeroCuenta, Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.moneda = moneda;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public abstract void cobrarComision();

    private void pagoIntereses(){

    }

    private void obtenerMovimientos(Date fechaInicio, Date fechaFin){

    }
    public int getNumeroCuenta() {
        return numeroCuenta;
    }
}
