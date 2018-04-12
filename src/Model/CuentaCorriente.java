package Model;

import java.util.Date;

public class CuentaCorriente extends Cuenta {
    private int opRealizadas;

    public CuentaCorriente(int numeroCuenta, Date fechaApertura, Moneda moneda, Cliente cliente) {
        super(numeroCuenta, fechaApertura, moneda, cliente);
    }

    @Override
    public void cobrarComision() {

    }
}
