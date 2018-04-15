package Controller;

import Model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
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
    public Checkbox deposito;

    @FXML
    public Checkbox comprar;

    @FXML
    public Checkbox cajeros;

    @FXML
    public Button guardarVariables;

    public void initialize(URL location, ResourceBundle resources) {

        guardarVariables.setOnAction(event -> {
            BigDecimal comisionCuentaAhorros = new BigDecimal(comisionAhorros.getText());
            BigDecimal comisionCuentaCorriente = new BigDecimal(comisionCorriente.getText());
            BigDecimal interesesCuentaAhorros = new BigDecimal(interesesAhorros.getText());
            BigDecimal interesesCuentaCorriente = new BigDecimal(interesesCorriente.getText());
            int cantOperacionesExentas = Integer.parseInt(numOperacionesExentas.getText());

            Singleton.getInstance().getGestor().guardarParametrosConfiguracion(comisionCuentaAhorros,comisionCuentaCorriente,interesesCuentaAhorros,interesesCuentaCorriente,cantOperacionesExentas);
        });

    }
}
