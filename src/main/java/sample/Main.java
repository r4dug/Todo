package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static String LOGINFXML_PATH = "/fxml/login.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
      //  Parent root = FXMLLoader.load(getClass().getResource(LOGINFXML_PATH));
       Parent root = FXMLLoader.load(getClass().getResource("/fxml/taskForm.fxml"));

        primaryStage.setTitle("TodoList Application");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
