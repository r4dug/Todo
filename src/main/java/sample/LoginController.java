package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Database.DbHandler;
import sample.animation.Shaker;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button loginSingupButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private URL location;

    private DbHandler databaseHandler;


    @FXML
    void initialize() {

        databaseHandler = new DbHandler();

        loginButton.setOnAction(actionEvent -> {

            String loginUser = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();
            User user = new User();
            user.setUsername(loginUser);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;

            try {
                while (userRow.next()) {
                    counter++;
                    String name = userRow.getString("firstname");
                    System.out.println("Welcome " + name);
                }
                if (counter == 1) {
                    System.out.println("Login successful!");
                } else {
                    Shaker userNameShaker = new Shaker(loginUsername);
                    Shaker passwordShaker = new Shaker(loginPassword);
                    userNameShaker.shake();
                    passwordShaker.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        loginSingupButton.setOnAction(event -> {

            loginSingupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/singup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });
    }


}
