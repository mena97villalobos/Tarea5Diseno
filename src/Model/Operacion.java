package Model;

import java.util.ArrayList;

public enum Operacion {
    RETIRO(1), //1
    DEPOSITO(2), //2
    PAGO_INTERESES(3), //3
    COBRO_COMISION(4), //4
    COMPRA_COMERCIO(5), //5
    RETIRO_CAJERO(6); //6

    private final int id;

    Operacion(int id) {
        this.id = id;
    }

    public static Operacion getByID(int id){
        for (Operacion operacion : Operacion.values()) {
            if(operacion.id == id)
                return operacion;
        }
        return RETIRO;
    }

}
