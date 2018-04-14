package Controller;

import Model.EntidadFinanciera;
import Model.Moneda;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdministrador implements Initializable {

    @FXML
    public TextField nombreCliente;

    @FXML
    public Button agregarCliente;

    @FXML
    public ComboBox clientesCuentas;

    @FXML
    public TextField saldoApertura;

    @FXML
    public ComboBox tipoCuenta;

    @FXML
    public ComboBox tipoMoneda;

    @FXML
    public Button agregarCuentas;

    public EntidadFinanciera entidad = new EntidadFinanciera();

    public void initialize(URL fxmlLoations, ResourceBundle resources){

        agregarCuentas.setOnAction(event -> {

            String idClienteSeleccionado = clientesCuentas.getSelectionModel().getSelectedItem().toString().substring(0,clientesCuentas.getSelectionModel().toString().indexOf("-"));
            BigDecimal saldoNuevo = new BigDecimal(saldoApertura.getText());
            String tipoCuentaSeleccionada = tipoCuenta.getSelectionModel().getSelectedItem().toString();
            Moneda monedaSeleccionada = Moneda.valueOf(tipoMoneda.getSelectionModel().getSelectedItem().toString());

            entidad.crearCuenta(idClienteSeleccionado,saldoNuevo,monedaSeleccionada,tipoCuentaSeleccionada);

        });

        agregarCliente.setOnAction(event -> {
            String nombCliente = nombreCliente.getText();

            entidad.crearCliente(nombCliente);
        });

    }

    public void datosDefecto() {

        tipoMoneda.setItems(FXCollections.observableArrayList(Moneda.values()));
        tipoCuenta.getItems().addAll("Ahorros", "Corriente");

    }

}
