package Model;

import Gestores.Singleton;

import java.math.BigDecimal;
import java.util.Date;

public class Movimiento {
    private static int nextID = obtenerUltimoIdMov();
    private int idOperacion;
    private Date fechaTransaccion;
    private BigDecimal monto;
    private boolean cobroExento;
    private Operacion operacion;

    public Movimiento(Date fechaTransaccion, BigDecimal monto, boolean cobroExento, Operacion operacion) {
        this.idOperacion = nextID;
        nextID ++;
        this.monto = monto;
        this.cobroExento = cobroExento;
        this.operacion = operacion;
        this.fechaTransaccion = fechaTransaccion;
    }

    // Constructor para obtener datos de la Base
    public Movimiento(int idOperacion, Date fechaTransaccion, BigDecimal monto, boolean cobroExento, Operacion operacion) {
        this.idOperacion = idOperacion;
        this.fechaTransaccion = fechaTransaccion;
        this.monto = monto;
        this.cobroExento = cobroExento;
        this.operacion = operacion;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public boolean isCobroExento() {
        return cobroExento;
    }

    public void setCobroExento(boolean cobroExento) {
        this.cobroExento = cobroExento;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    private static int obtenerUltimoIdMov(){
        return Singleton.getInstance().getGestor().getLastValueMov();
    }
}
