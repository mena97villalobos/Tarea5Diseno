package Controller;

import Model.Cliente;
import Model.EntidadFinanciera;
import Gestores.Singleton;
import Model.ThreadSimulador;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSimulador implements Initializable {

    @FXML
    public ComboBox clientesSimulacion;

    @FXML
    public ComboBox cuentasCliente;

    @FXML
    public Button iniciarSimulacion;

    @FXML
    public Button detenerSimulacion;

    @FXML
    public DatePicker fechaInicio;

    @FXML
    public DatePicker fechaFin;

    @FXML
    public TextField idCuenta;

    @FXML
    public Button generarEstadoCuenta;

    @FXML
    public TextField comisionAhorros;

    @FXML
    public TextField comisionCorriente;

    @FXML
    public TextField interesesAhorros;

    @FXML
    public TextField interesesCorriente;

    @FXML
    public TextField numOperacionesExentas;

    @FXML
    public CheckBox retiros;

    @FXML
    public CheckBox deposito;

    @FXML
    public CheckBox comprar;

    @FXML
    public CheckBox cajeros;

    @FXML
    public Button guardarVariables;

    public void initialize(URL location, ResourceBundle resources) {

        guardarVariables.setOnAction(event -> {
            BigDecimal comisionCuentaAhorros = new BigDecimal(comisionAhorros.getText());
            BigDecimal comisionCuentaCorriente = new BigDecimal(comisionCorriente.getText());
            BigDecimal interesesCuentaAhorros = new BigDecimal(interesesAhorros.getText());
            BigDecimal interesesCuentaCorriente = new BigDecimal(interesesCorriente.getText());
            int cantOperacionesExentas = Integer.parseInt(numOperacionesExentas.getText());

            EntidadFinanciera.setComisionCuentaAhorro(comisionCuentaAhorros);
            EntidadFinanciera.setComisionCuentaCorriente(comisionCuentaCorriente);
            EntidadFinanciera.setTasaInteresAhorros(interesesCuentaAhorros);
            EntidadFinanciera.setTasaInteresCorriente(interesesCuentaCorriente);
            EntidadFinanciera.setCantOperacionesExentas(cantOperacionesExentas);

            Singleton.getInstance().getGestor().guardarParametrosConfiguracion(comisionCuentaAhorros, comisionCuentaCorriente, interesesCuentaAhorros, interesesCuentaCorriente, cantOperacionesExentas);

            limpiarVariables();
        });

        clientesSimulacion.setOnAction(event -> {
            String idCliente = clientesSimulacion.getSelectionModel().getSelectedItem().toString().substring(0, clientesSimulacion.getSelectionModel().getSelectedItem().toString().indexOf("-"));
            Cliente clienteEncontrado = Cliente.filtrarCliente(idCliente);

            cuentasCliente.setItems(FXCollections.observableArrayList(clienteEncontrado.getNumeroCuentas()));

        });

        iniciarSimulacion.setOnAction(event -> {
            boolean operacionesExentas[] = {retiros.isSelected(), deposito.isSelected(), comprar.isSelected(), cajeros.isSelected()};
            String idCliente = clientesSimulacion.getSelectionModel().getSelectedItem().toString().substring(0, clientesSimulacion.getSelectionModel().getSelectedItem().toString().indexOf("-"));
            int numeroCuenta = Integer.parseInt(cuentasCliente.getSelectionModel().getSelectedItem().toString().substring(0, cuentasCliente.getSelectionModel().getSelectedItem().toString().indexOf("-")));
            String tipoCuenta = cuentasCliente.getSelectionModel().getSelectedItem().toString().substring(cuentasCliente.getSelectionModel().getSelectedItem().toString().indexOf("-"), cuentasCliente.getSelectionModel().getSelectedItem().toString().length());


            Cliente clienteSimular = Cliente.filtrarCliente(idCliente);

            ThreadSimulador ts = new ThreadSimulador(clienteSimular, operacionesExentas, numeroCuenta, Singleton.getInstance().getGestor().obtenerFechaSistema(), tipoCuenta);

            //ts.run();
        });



    }

    public void limpiarVariables() {
        comisionAhorros.clear();
        comisionCorriente.clear();
        interesesAhorros.clear();
        interesesCorriente.clear();
        numOperacionesExentas.clear();
    }

    public void datosDefecto() {
        clientesSimulacion.setItems(FXCollections.observableArrayList(Cliente.getNombresClientes()));
    }

}
