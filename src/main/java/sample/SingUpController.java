package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Database.DbHandler;
import sample.model.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpFirstname;

    @FXML
    private TextField signUpLastname;

    @FXML
    private TextField signUpUsername;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button submitButton;

    @FXML
    void initialize() {

        submitButton.setOnAction(actionEvent -> {
            createUser();
        });
    }

    private void createUser() {

        DbHandler databaseHandler = new DbHandler();

        String firstName = signUpFirstname.getText();
        String lastname = signUpLastname.getText();
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();

        User user = new User(firstName,lastname,username,password);

        databaseHandler.signUpUser(user);

    }
}
