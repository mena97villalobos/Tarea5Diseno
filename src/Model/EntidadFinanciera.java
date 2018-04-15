package Model;

import Gestores.GestorBD;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EntidadFinanciera {

    public static BigDecimal comisionCuentaAhorro = getConfiguracion(1);
    public static BigDecimal comisionCuentaCorriente = getConfiguracion(2);
    public static BigDecimal tasaInteresAhorros = getConfiguracion(3);
    public static BigDecimal tasaInteresCorriente = getConfiguracion(4);
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

        Date fechaSistema = obtenerFechaSistema();
        Cliente clienteEscogido = Cliente.filtrarCliente(identificacionCliente);

        if (tipoCuenta.equals("Corriente"))
            new CuentaCorriente(fechaSistema, tipoMoneda, clienteEscogido, saldoApertura);
        else
            new CuentaAhorros(fechaSistema, tipoMoneda, clienteEscogido, saldoApertura);

        Singleton.getInstance().getGestor().crearCuenta(new java.sql.Date(fechaSistema.getTime()), identificacionCliente, saldoApertura, tipoMoneda, tipoCuenta);
    }

    public java.util.Date obtenerFechaSistema() {
        java.util.Date fechaSistemaReal = null;
        try {
            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            java.util.Date objetoDate = new java.util.Date();
            String fechaSistema = formatoFecha.format(objetoDate);
            fechaSistemaReal = formatoFecha.parse(fechaSistema);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fechaSistemaReal;
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
