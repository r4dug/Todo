package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.Database.Config;
import sample.Database.DbHandler;
import sample.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListTaskController {

    @FXML
    private ListView taskListView;

    @FXML
    void initialize() {


            taskListView.getItems().addAll(addItems());

    }
    public ObservableList addItems() {

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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return options;

}

    }
