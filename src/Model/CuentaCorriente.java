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

    //Constructor para extraer de la base
    public CuentaCorriente(int numeroCuenta,Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldoApertura, int operacionesRealizadas) {
        super(numeroCuenta,fechaApertura, moneda, cliente,saldoApertura);
        this.opRealizadas = operacionesRealizadas;
    }

    @Override
    public void cobrarComision() {

    }
    public int getOpRealizadas() {
        return opRealizadas;
    }

    public void setOperacionesRealizadas(){
        this.opRealizadas++;
    }

}
