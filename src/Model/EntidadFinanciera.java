package Model;

import Gestores.Singleton;

import java.math.BigDecimal;
import java.util.Date;

public class EntidadFinanciera {

    public static BigDecimal comisionCuentaAhorro = getConfiguracion(1);
    public static BigDecimal comisionCuentaCorriente = getConfiguracion(2);
    public static BigDecimal tasaInteresAhorros = getConfiguracion(3).divide(new BigDecimal("100"));
    public static BigDecimal tasaInteresCorriente = getConfiguracion(4).divide(new BigDecimal("100"));
    public static int cantOperacionesExentas = getConfiguracion(5).intValueExact();


    public static void setComisionCuentaAhorro(BigDecimal comisionCuentaAhorro) {
        EntidadFinanciera.comisionCuentaAhorro = comisionCuentaAhorro;
    }

    public static void setComisionCuentaCorriente(BigDecimal comisionCuentaCorriente) {
        EntidadFinanciera.comisionCuentaCorriente = comisionCuentaCorriente;
    }

    public static void setCantOperacionesExentas(int cantOperacionesExentas) {
        EntidadFinanciera.cantOperacionesExentas = cantOperacionesExentas;
    }

    public static void setTasaInteresAhorros(BigDecimal tasaInteresAhorros) {
        EntidadFinanciera.tasaInteresAhorros = tasaInteresAhorros;
    }

    public static void setTasaInteresCorriente(BigDecimal tasaInteresCorriente) {
        EntidadFinanciera.tasaInteresCorriente = tasaInteresCorriente;
    }

    public void crearCliente(String nombreCompleto) {

        new Cliente(nombreCompleto);

        Singleton.getInstance().getGestor().insertarCliente(nombreCompleto);

    }

    public void crearCuenta(String identificacionCliente, BigDecimal saldoApertura, Moneda tipoMoneda, String tipoCuenta) {

        Date fechaSistema = Singleton.getInstance().getGestor().obtenerFechaSistema();
        Cliente clienteEscogido = Cliente.filtrarCliente(identificacionCliente);

        if (tipoCuenta.equals("Corriente"))
            new CuentaCorriente(fechaSistema, tipoMoneda, clienteEscogido, saldoApertura);
        else
            new CuentaAhorros(fechaSistema, tipoMoneda, clienteEscogido, saldoApertura);

        Singleton.getInstance().getGestor().crearCuenta(new java.sql.Date(fechaSistema.getTime()), identificacionCliente, saldoApertura, tipoMoneda, tipoCuenta);
    }

    private static BigDecimal getConfiguracion(int numero) {
        BigDecimal parametroExtraido = null;
        switch (numero) {
            case 1:
                parametroExtraido = Singleton.getInstance().getGestor().obtenerParametros("COMISIONCUENTAAHORRO");
                break;
            case 2:
                parametroExtraido = Singleton.getInstance().getGestor().obtenerParametros("COMISIONCUENTACORRIENTE");
                break;
            case 3:
                parametroExtraido = Singleton.getInstance().getGestor().obtenerParametros("TASAINTERESAHORROS");
                break;
            case 4:
                parametroExtraido = Singleton.getInstance().getGestor().obtenerParametros("TASAINTERESCORRIENTE");
                break;
            case 5:
                parametroExtraido = Singleton.getInstance().getGestor().obtenerParametros("CANTOPERACIONESEXENTAS");
                break;

        }
        return parametroExtraido;
    }

}
