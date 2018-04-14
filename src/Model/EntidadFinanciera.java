package Model;

import Gestores.GestorBD;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EntidadFinanciera {
    public static BigDecimal comisionCuentaAhorro;
    public static BigDecimal comisionCuentaCorriente;
    public static int cantOperacionesExentas;
    public static BigDecimal tasaInteresAhorros;
    public static BigDecimal tasaInteresCorriente;

    private void crearCliente(String nombreCompleto){
        GestorBD gestorBase = new GestorBD();
        new Cliente(nombreCompleto);

        gestorBase.insertarCliente(nombreCompleto); // TODO Hacer funcion en GESTORBD apenas este lista la base

    }
    private void crearCuenta(String identificacionCliente, BigDecimal saldoApertura,Moneda tipoMoneda,String tipoCuenta){
        GestorBD gestorBase = new GestorBD();

        Date fechaSistema = obtenerFechaSistema();
        Cliente clienteEscogido = filtrarCliente(identificacionCliente);

        if(tipoCuenta.equals("Corriente"))
            new CuentaCorriente(fechaSistema,tipoMoneda,clienteEscogido,saldoApertura);
        else
            new CuentaAhorros(fechaSistema,tipoMoneda,clienteEscogido,saldoApertura);

        gestorBase.crearCuenta(new java.sql.Date(fechaSistema.getTime()),clienteEscogido,saldoApertura,tipoMoneda);
    }

    private Cliente filtrarCliente(String idCliente){
        for(int i =0; i< Cliente.getClientes().size();i++){
            if(Cliente.getClientes().get(i).getId() == Integer.parseInt(idCliente))
                return Cliente.getClientes().get(i);
        }
        return null;
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
