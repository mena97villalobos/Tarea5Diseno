package Model;

import java.util.Date;

public class CuentaAhorros extends Cuenta {


    public CuentaAhorros(Date fechaApertura, Moneda moneda, Cliente cliente) {
        super(fechaApertura, moneda, cliente);
    }

    @Override
    public void cobrarComision() {

    }
}
