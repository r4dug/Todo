package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        loginButton.setOnAction(ev -> {
            System.out.println("Login clicked!");
        });

    }

}
