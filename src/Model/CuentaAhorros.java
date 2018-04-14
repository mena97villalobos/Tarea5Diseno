package Model;

import Gestores.GestorBD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CuentaAhorros extends Cuenta {

    public CuentaAhorros(Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldoApertura) {
        super(fechaApertura, moneda, cliente,saldoApertura);


        cliente.agregarCuentaAhorros(this);
    }

    @Override
    public void cobrarComision() {

    }

}
