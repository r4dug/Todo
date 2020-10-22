package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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


    @FXML
    void initialize() {

        String loginText = loginUsername.getText().trim();
        String loginPwd = loginPassword.getText().trim();

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

        loginButton.setOnAction(ev -> {
            if(!loginText.equals("") || !loginPwd.equals("")){
                loginUser(loginText,loginPwd);
            }else {
                System.out.println("Error login in user");
            }

        });

    }

    private void loginUser(String username, String password) {
        //Check in the db if the user exists
        //If true, take them to AddItemScreen

    }

}
