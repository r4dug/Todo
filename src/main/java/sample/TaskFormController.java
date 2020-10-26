package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Database.DbHandler;
import sample.model.Task;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskFormController {

    @FXML
    private AnchorPane taskFormAnchorPane;

    @FXML
    private TextField taskField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button saveTaskButton;

    @FXML
    private Button showTaskButton;

    //Date formatting
    LocalDateTime currentDateTime = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    String formattedDate = currentDateTime.format(formatter);

    @FXML
    void initialize() {

            saveTaskButton.setOnAction(actionEvent -> {
                    
                createTask();

            });

            showTaskButton.setOnAction(actionEvent -> {

                try {
                    AnchorPane listTaskAnchorPane = FXMLLoader.load(getClass().getResource("/fxml/listTask.fxml"));

                    taskFormAnchorPane.getChildren().setAll(listTaskAnchorPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showTasks();
            });
    }

    private void showTasks() {



    }

    private void createTask() {

        DbHandler databaseHandler = new DbHandler();
        String taskInput = taskField.getText();
        String descriptionInput = descriptionField.getText();

        Task task = new Task(taskInput,formattedDate,descriptionInput);

        databaseHandler.addTask(task);

    }


}
