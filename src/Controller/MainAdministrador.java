package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Javier on 4/13/2018.
 */
public class MainAdministrador  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("../View/Administrador.fxml").openStream());

        ControllerAdministrador controladorAdministrador = loader.getController();
        controladorAdministrador.datosDefecto();

        primaryStage.setTitle("Administrador");
        primaryStage.setScene(new Scene(root, 722, 286));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
