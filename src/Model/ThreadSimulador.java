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

    private Random ran;
    private int avanceFecha;
    private int cantMovimientos;// = ran.nextInt(41);

    private Cliente cliente;// = null;
    private boolean listaOperacionesExentas [];
    private int numeroCuenta;
    private Date dateInicio;


    public ThreadSimulador(Cliente cliente, boolean operacionesExentas [], int numeroCuenta,Date fechaInicio){
        this.ran = new Random();
        this.cantMovimientos = ran.nextInt(41);
        this.avanceFecha = ran.nextInt();//TODO Hacer un calculo para ver de cuanto en cuanto lo voy aumentando
        this.cliente = cliente; //TODO Hacer algo no se, para que la vara caiga a final o inicio de mes, sin romper la linealidad.
        this.listaOperacionesExentas = operacionesExentas;
        this.numeroCuenta = numeroCuenta;
        this.dateInicio = fechaInicio;
    }

    @Override
    public void run() {
        //Generar un fecha random para la transaccion
      /*  DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date dateInicio = null;
        Date dateFin = null;

        try {
            dateInicio = format.parse(this.fechaInicio);
            dateFin = format.parse(this.fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        while(this.cantMovimientos != 0){
            int tipoOp = this.ran.nextInt(4);

                BigDecimal montoTran = new BigDecimal(ran.nextInt(10000));
                //  long bound = ThreadLocalRandom.current().nextLong(dateInicio.getTime(), dateFin.getTime());
                //  Date randomDate = new Date(bound);

                switch (tipoOp) {
                    case 0:
                        System.out.println("Caso Retiro");
                        this.cliente.retiro(numeroCuenta, montoTran, dateInicio);
                        break;
                    case 1:
                        System.out.println("Caso Deposito");
                        this.cliente.deposito(numeroCuenta, montoTran, dateInicio);
                        break;
                    case 2:
                        System.out.println("Caso Compra Comercio");
                        this.cliente.compra_comercio(numeroCuenta, montoTran, dateInicio);
                        break;
                    case 3:
                        System.out.println("Caso Retiro Cajero");
                        this.cliente.retiro_cajero(numeroCuenta, montoTran, dateInicio);
                        break;
                }

                dateInicio = aumentarFecha(dateInicio);
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
