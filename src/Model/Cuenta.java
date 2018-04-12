package Model;

import java.util.Date;

public abstract class Cuenta {
    private int numeroCuenta;
    private Date fechaApertura;
    private Moneda moneda;
    private Cliente cliente;

    public abstract void cobrarComision();

    private void pagoIntereses(){

    }

    private void obtenerMovimientos(Date fechaInicio, Date fechaFin){

    }
}
