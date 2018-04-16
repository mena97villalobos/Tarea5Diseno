package Model;

import Controller.ControllerSimulador;
import javafx.application.Platform;
import javafx.concurrent.Task;

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

    private Random ran;
    private int avanceFecha;
    private int cantMovimientos;
    private Cliente cliente;// = null;
    private boolean listaOperacionesExentas [];
    private int numeroCuenta;
    private Date dateActual;
    private Date dateUsado;
    private String tipoCuenta;
    private boolean cambioMes = false;
    public ControllerSimulador cs;

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
        Task task = new Task() {
            @Override
            protected Void call() throws Exception {
                String operacionRealizada = "";
                System.out.print("Cantidad de Movimientos: "+cantMovimientos);
                while(cantMovimientos != 0) {
                    Calendar sDate = Calendar.getInstance();
                    Calendar eDate = Calendar.getInstance();
                    sDate.setTime(dateUsado);
                    eDate.setTime(dateActual);
                    int difInMonths = sDate.get(Calendar.MONTH) - eDate.get(Calendar.MONTH);
                    //

                    if (difInMonths != 0) {
                        Cuenta cuenta = cliente.obtenerCuenta(tipoCuenta, numeroCuenta);
                        BigDecimal saldoActual = cuenta.getSaldo();
                        BigDecimal interesesAcumulados;
                        if (tipoCuenta.equals("Ahorros")) {
                            interesesAcumulados = saldoActual.multiply(EntidadFinanciera.tasaInteresAhorros);
                            cuenta.cobrarComision(dateActual);
                        } else {
                            interesesAcumulados = saldoActual.multiply(EntidadFinanciera.tasaInteresCorriente);
                            cuenta.cobrarComision(dateActual);
                        }
                        cuenta.pagoIntereses(interesesAcumulados, dateActual,tipoCuenta);
                        operacionRealizada += "Aplicacion de intereses a por: " + interesesAcumulados.toString() +
                                " fecha transaccion: " + dateActual.toString() + "\n";
                        operacionRealizada += "Aplicacion de cobro de comisión" + "\n";
                    }

                    int tipoOp = ran.nextInt(4);
                    BigDecimal montoTran = new BigDecimal(ran.nextInt(10000));
                    switch (tipoOp) {
                        case 0:
                            System.out.println("Caso Retiro");
                            cliente.retiro(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[0]);
                            operacionRealizada += "Operacion realizada: RETIRO monto: " + montoTran.toString() + " fecha: " + dateActual.toString() + "\n";
                            break;
                        case 1:
                            System.out.println("Caso Deposito");
                            cliente.deposito(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[1]);
                            operacionRealizada += "Operacion realizada: DEPOSITO monto: " + montoTran.toString() + " fecha: " + dateActual.toString() + "\n";
                            break;
                        case 2:
                            System.out.println("Caso Compra Comercio");
                            cliente.compra_comercio(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[2]);
                            operacionRealizada += "Operacion realizada: COMPRA COMERCIO monto: " + montoTran.toString() + " fecha: " + dateActual.toString() + "\n";
                            break;
                        case 3:
                            System.out.println("Caso Retiro Cajero");
                            cliente.retiro_cajero(numeroCuenta, montoTran, dateActual, tipoCuenta, listaOperacionesExentas[3]);
                            operacionRealizada += "Operacion realizada: RETIRO CAJERO monto: " + montoTran.toString() + " fecha: " + dateActual.toString() + "\n";
                            break;
                    }
                    dateUsado = dateActual;
                    dateActual = aumentarFecha(dateActual);
                    cantMovimientos--;
                    this.updateMessage(operacionRealizada);
                }
                this.updateMessage(operacionRealizada + "\n" + "Transacciones completadas");
                return null;
            }
        };
        Thread t = new Thread(task);
        cs.log.textProperty().bind(task.messageProperty());
        t.start();
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
