package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Javier on 4/13/2018.
 */
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
    public Button agregarCuentas;

    public void initialize(URL fxmlLoations, ResourceBundle resources){

    }



}
