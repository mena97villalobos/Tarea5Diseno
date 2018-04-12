package Model;

import java.util.Date;

public class CuentaAhorros extends Cuenta {


    public CuentaAhorros(int numeroCuenta, Date fechaApertura, Moneda moneda, Cliente cliente) {
        super(numeroCuenta, fechaApertura, moneda, cliente);
    }

    @Override
    public void cobrarComision() {

    }
}
