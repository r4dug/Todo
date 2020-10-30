package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Database.Config;
import sample.Database.DbHandler;
import sample.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListTaskController {

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    private TableView<Task> taskTableView;

    @FXML
    private TableColumn<Task, String> colTask;

    @FXML
    private TableColumn<Task, String> colDescription;

    @FXML
    private TableColumn<Task, String> colDate;

    @FXML
    void initialize() {

        // assert taskTableView != null : "fx:id=\"taskTableView\" was not injected: check your FXML file 'listTask.fxml'.";
        // colTask.setCellValueFactory(
        // new PropertyValueFactory<Task,String>("task"));

        colTask.setCellValueFactory(features -> features.getValue().taskColumn);
        colDescription.setCellValueFactory(features -> features.getValue().descriptionColumn);
        colDate.setCellValueFactory(features -> features.getValue().dateColumn);

        DbHandler dbHandler = new DbHandler();

        try {
            dbHandler.getDbConnection();
            buildData();
        } catch (SQLException | ClassNotFoundException ce) {
        }
    }

    public void buildData() {

        DbHandler dbHandler = new DbHandler();
        String query = "SELECT * FROM " + Config.TASKS_TABLE;

        try {
            PreparedStatement prepStatement = dbHandler.getDbConnection().prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.taskColumn.set(rs.getString("task"));
                task.descriptionColumn.set(rs.getString("description"));
                task.dateColumn.set(rs.getString("datecreated"));
                tasks.add(task);
            }
            taskTableView.setItems(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


  /*  public ObservableList addItems() {

        DbHandler dbHandler = new DbHandler();
        ObservableList options = FXCollections.observableArrayList();
        ResultSet rs = null;
        String query = "SELECT * FROM " + Config.TASKS_TABLE;

        options.clear();

        try {
            PreparedStatement prepStatement = dbHandler.getDbConnection().prepareStatement(query);
            rs = prepStatement.executeQuery();

            while (rs.next()) {
                options.add(rs.getString("task"));
            }
            prepStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return options;
    }*/

}
