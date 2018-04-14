package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CuentaCorriente extends Cuenta {

    private int opRealizadas;

    public CuentaCorriente(Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldoApertura) {
        super(fechaApertura, moneda, cliente,saldoApertura);
        this.opRealizadas = 0;

        cliente.agregarCuentaCorriente(this);
    }

    @Override
    public void cobrarComision() {

    }
}
