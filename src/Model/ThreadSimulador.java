package Model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSimulador implements Runnable {
    private Random ran = new Random();
    private int cantMovimientos = ran.nextInt(41);
    public Cliente cliente = null;
    public int numeroCuenta;

    @Override
    public void run() {
        //Generar un fecha random para la transaccion
        String fechaI = "January 2, 2010";
        String fechaF = "January 2, 2020";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date dateI = null;
        Date dateF = null;
        try {
            dateI = format.parse(fechaI);
            dateF = format.parse(fechaF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        while(cantMovimientos != 0){
            int tipoOp = ran.nextInt(4);
            BigDecimal montoTran = new BigDecimal(ran.nextInt(10000));
            long bound = ThreadLocalRandom.current().nextLong(dateI.getTime(), dateF.getTime());
            Date randomDate = new Date(bound);
            switch (tipoOp){
                case 1:
                    System.out.println("Caso Retiro");
                    cliente.retiro(numeroCuenta, montoTran);
                    break;
                case 2:
                    System.out.println("Caso Deposito");
                    cliente.deposito(numeroCuenta, montoTran);
                    break;
                case 3:
                    System.out.println("Caso Compra Comercio");
                    cliente.compra_comercio(numeroCuenta, montoTran);
                    break;
                case 4:
                    System.out.println("Caso Retiro Cajero");
                    cliente.retiro_cajero(numeroCuenta, montoTran);
                    break;
            }
            cantMovimientos --;
        }
    }
}
