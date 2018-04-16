package Model;

import Gestores.GestorBD;
import Gestores.Singleton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CuentaAhorros extends Cuenta {

    public CuentaAhorros(Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldoApertura) {
        super(fechaApertura, moneda, cliente,saldoApertura);


        cliente.agregarCuentaAhorros(this);
    }

    public CuentaAhorros(int numeroCuenta,Date fechaApertura, Moneda moneda, Cliente cliente, BigDecimal saldoActual) {
        super(numeroCuenta,fechaApertura, moneda, cliente,saldoActual);

    }

    @Override
    public void cobrarComision(Date fechaTran) {
        BigDecimal saldo = super.getSaldo();
        saldo = saldo.subtract(EntidadFinanciera.comisionCuentaAhorro);
        super.setSaldo(saldo);
        Movimiento mov = new Movimiento(fechaTran, EntidadFinanciera.comisionCuentaAhorro, true, Operacion.COBRO_COMISION);
        agregarMovimientos(mov);
        Singleton.getInstance().getGestor().agregarMovimiento(Operacion.COBRO_COMISION,new java.sql.Date(fechaTran.getTime()), EntidadFinanciera.comisionCuentaAhorro,true,this);
        Singleton.getInstance().getGestor().modificarCuenta(this, "Ahorros", true);
    }

}
