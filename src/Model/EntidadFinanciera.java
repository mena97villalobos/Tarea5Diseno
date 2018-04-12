package Model;

import java.math.BigDecimal;

public class EntidadFinanciera {
    public static BigDecimal comisionCuentaAhorro;
    public static BigDecimal comisionCuentaCorriente;
    public static int cantOperacionesExentas;
    public static BigDecimal tasaInteresAhorros;
    public static BigDecimal tasaInteresCorriente;

    private void crearCliente(String nombreCompleto){
        new Cliente(nombreCompleto);

    }
    private void crearCuenta(){

    }
}
