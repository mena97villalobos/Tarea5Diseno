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
        Parent root = FXMLLoader.load(getClass().getResource("../View/simulador.fxml"));
        primaryStage.setTitle("Simulador");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ThreadSimulador ts = new ThreadSimulador();
//        ts.run();
        launch(args);
    }
}
