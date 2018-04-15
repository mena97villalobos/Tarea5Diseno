package Controller;

import Model.ThreadSimulador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("../View/simulador.fxml").openStream());
        ControllerSimulador controladorSimulador = loader.getController();
        controladorSimulador.datosDefecto();

        primaryStage.setTitle("Simulador");
        primaryStage.setScene(new Scene(root, 1033, 360));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
