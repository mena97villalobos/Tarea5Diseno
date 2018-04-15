package Model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSimulador implements Runnable {

    public static void main(String[] args){
        boolean a[] = {true, false};
        ThreadSimulador ts = new ThreadSimulador(null, a, 1, new Date(), "Ahorros");
        ts.run();
    }

    private Random ran;
    private int avanceFecha;
    private int cantMovimientos;// = ran.nextInt(41);

    private Cliente cliente;// = null;
    private boolean listaOperacionesExentas [];
    private int numeroCuenta;
    private Date dateActual;
    private Date dateUsado;
    private String tipoCuenta;
    private boolean cambioMes = false;

    public ThreadSimulador(Cliente cliente, boolean operacionesExentas[], int numeroCuenta, Date fechaInicio, String tipoCuenta){
        this.ran = new Random();
        this.cantMovimientos = ran.nextInt(41);
        this.avanceFecha = ran.nextInt(5);
        this.cliente = cliente;
        this.listaOperacionesExentas = operacionesExentas;
        this.numeroCuenta = numeroCuenta;
        this.dateActual = fechaInicio;
        this.dateUsado = fechaInicio;
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public void run() {
        while(this.cantMovimientos != 0){

            //Calculo diferencia entre fechas
            Calendar sDate = Calendar.getInstance();
            Calendar eDate = Calendar.getInstance();
            sDate.setTime(dateUsado);
            eDate.setTime(dateActual);
            int difInMonths = sDate.get(Calendar.MONTH) - eDate.get(Calendar.MONTH);
            //

            if(difInMonths != 0){
                Cuenta cuenta = cliente.obtenerCuenta(tipoCuenta, numeroCuenta);
                BigDecimal saldoActual = cuenta.getSaldo();
                BigDecimal interesesAcumulados;
                if(tipoCuenta.equals("Ahorros")){
                    System.out.println("Calcular intereses cuenta ahooros");
                    interesesAcumulados = saldoActual.multiply(EntidadFinanciera.tasaInteresAhorros);
                }
                else{
                    System.out.println("Calcular intereses cuenta corriente");
                    interesesAcumulados = saldoActual.multiply(EntidadFinanciera.tasaInteresCorriente);
                }
                cuenta.pagoIntereses(interesesAcumulados, dateActual);
            }

            int tipoOp = this.ran.nextInt(4);
            BigDecimal montoTran = new BigDecimal(ran.nextInt(10000));
            switch (tipoOp) {
                case 0:
                    System.out.println("Caso Retiro");
                    this.cliente.retiro(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[0]);
                    break;
                case 1:
                    System.out.println("Caso Deposito");
                    this.cliente.deposito(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[1]);
                    break;
                case 2:
                    System.out.println("Caso Compra Comercio");
                    this.cliente.compra_comercio(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[2]);
                    break;
                case 3:
                    System.out.println("Caso Retiro Cajero");
                    this.cliente.retiro_cajero(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[3]);
                    break;
            }
            dateUsado = dateActual;
            dateActual = aumentarFecha(dateActual);
            this.cantMovimientos--;
        }
    }

    public Date aumentarFecha(Date fechaInicio){
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(fechaInicio);
            c.add(Calendar.DATE, this.avanceFecha);
        }catch(Exception e){
            e.printStackTrace();
        }
        return c.getTime();
    }
}
