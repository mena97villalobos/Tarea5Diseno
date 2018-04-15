package Model;

import Gestores.GestorBD;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//TODO No sabia donde ponerlo, maes para que puedan guardar tuanis las configuraciones, cambian el orden de la tabla configuracion con este orden: 1) comisionCuentaAhorro 2)comisionCuentaCorriente 3)tasaInteresAhorros 4)tasaInteresCorriente 5)cantOperacionesExentas
//TODO Cambiar en la tabla MONEDA: Donde dice "tipoMoneda", cambiarlo por "tipo"
//TODO Agregar un atributo al final de la tabla cuenta que se llame operacionesRealizadas

public class EntidadFinanciera {
    public static BigDecimal comisionCuentaAhorro;
    public static BigDecimal comisionCuentaCorriente;
    public static int cantOperacionesExentas;
    public static BigDecimal tasaInteresAhorros;
    public static BigDecimal tasaInteresCorriente;

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

    public void crearCliente(String nombreCompleto){

        new Cliente(nombreCompleto);

        Singleton.getInstance().getGestor().insertarCliente(nombreCompleto);

    }

    public void crearCuenta(String identificacionCliente, BigDecimal saldoApertura,Moneda tipoMoneda,String tipoCuenta){

        Date fechaSistema = obtenerFechaSistema();
        Cliente clienteEscogido = Cliente.filtrarCliente(identificacionCliente);

        if(tipoCuenta.equals("Corriente"))
            new CuentaCorriente(fechaSistema,tipoMoneda,clienteEscogido,saldoApertura);
        else
            new CuentaAhorros(fechaSistema,tipoMoneda,clienteEscogido,saldoApertura);

        Singleton.getInstance().getGestor().crearCuenta(new java.sql.Date(fechaSistema.getTime()),identificacionCliente,saldoApertura,tipoMoneda,tipoCuenta);
    }

    public java.util.Date obtenerFechaSistema(){
        java.util.Date fechaSistemaReal = null;
        try{
            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            java.util.Date objetoDate = new java.util.Date();
            String fechaSistema = formatoFecha.format(objetoDate);
            fechaSistemaReal = formatoFecha.parse(fechaSistema);

        }catch(Exception e){
            e.printStackTrace();
        }

        return fechaSistemaReal;
    }

}
