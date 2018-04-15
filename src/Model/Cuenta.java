package Model;

import Gestores.Singleton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public abstract class Cuenta {
    private static int nextIdCuenta;
    private ArrayList<Movimiento> movimientos = new ArrayList();
    private int numeroCuenta;
    private Date fechaApertura;
    private Moneda moneda;
    private Cliente cliente;
    private BigDecimal saldo;




    public Cuenta(Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldo) {
        this.numeroCuenta = nextIdCuenta;
        nextIdCuenta++;

        this.fechaApertura = fechaApertura;
        this.moneda = moneda;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    //Constructor para extraer de la base de datos
    public Cuenta(int numeroCuenta, Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.moneda = moneda;
        this.cliente = cliente;
        this.saldo = saldo;
        this.movimientos = getMovimientos();
    }

    public void agregarMovimientos(Movimiento movimiento){
        this.movimientos.add(movimiento);
    }

    public abstract void cobrarComision();

    private void pagoIntereses(){

    }

    private void obtenerMovimientos(Date fechaInicio, Date fechaFin){

    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public ArrayList<Movimiento> getMovimientos(){

        ArrayList<Movimiento> movimientos = Singleton.getInstance().getGestor().getMovimientosCuenta(this);
        return movimientos;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo){
        this.saldo = saldo;
    }
}
