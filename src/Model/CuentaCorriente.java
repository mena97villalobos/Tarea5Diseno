package Model;

import java.util.Date;

public class CuentaCorriente extends Cuenta {
    private int opRealizadas;

    public CuentaCorriente(Date fechaApertura, Moneda moneda, Cliente cliente) {
        super(fechaApertura, moneda, cliente);
    }

    @Override
    public void cobrarComision() {

    }
}
