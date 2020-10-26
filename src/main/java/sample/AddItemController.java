package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animation.Shaker;

import java.io.IOException;
import java.net.URL;


public class AddItemController {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private URL location;

    @FXML
    private Label noTaskLabel;

    @FXML
    private ImageView addButton;

    @FXML
    public void initialize(){


        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();

/*            FadeTransition addItemTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), noTaskLabel);*/

            addButton.relocate(0,20);
            noTaskLabel.relocate(0,85);
            addButton.setOpacity(0);
            noTaskLabel.setOpacity(0);

/*            addItemTransition.setFromValue(1f);
            addItemTransition.setToValue(0f);
            addItemTransition.setCycleCount(1);
            addItemTransition.setAutoReverse(false);
            addItemTransition.play();
            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();*/

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/fxml/taskForm.fxml"));

                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
