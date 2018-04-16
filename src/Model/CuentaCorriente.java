package Model;

import Gestores.Singleton;

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
    public void cobrarComision(Date fechaTransaccion) {
        int opRealizadas = Singleton.getInstance().getGestor().getOpRealizadas(super.getNumeroCuenta());
        if(opRealizadas > EntidadFinanciera.cantOperacionesExentas){
            int diffOp = opRealizadas - EntidadFinanciera.cantOperacionesExentas;
            BigDecimal saldo = super.getSaldo();
            BigDecimal comision = EntidadFinanciera.comisionCuentaCorriente.multiply(new BigDecimal(diffOp));
            saldo = saldo.subtract(comision);
            super.setSaldo(saldo);
            Movimiento mov = new Movimiento(fechaTransaccion, comision, false, Operacion.COBRO_COMISION);
            agregarMovimientos(mov);
            Singleton.getInstance().getGestor().agregarMovimiento(Operacion.COBRO_COMISION,new java.sql.Date(fechaTransaccion.getTime()),comision,false,this);
            Singleton.getInstance().getGestor().modificarCuenta(this, "Corriente", false);
            Singleton.getInstance().getGestor().setMovimientosHechos(super.getNumeroCuenta());
        }
    }
    public int getOpRealizadas() {
        return opRealizadas;
    }

    public void setOperacionesRealizadas(){
        this.opRealizadas++;
    }

}
