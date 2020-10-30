package sample.Database;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import sample.model.Task;
import sample.model.User;

import javax.xml.transform.Result;
import java.sql.*;

public class DbHandler extends Config {

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {

        String insert = "INSERT INTO " + Config.USERS_TABLE + "(" + Config.USERS_FIRSTNAME + "," +
                Config.USERS_LASTNAME + "," + Config.USERS_USERNAME + "," + Config.USERS_PASSWORD +
                ")" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement prepStatement = getDbConnection().prepareStatement(insert);
            prepStatement.setString(1, user.getFirstname());
            prepStatement.setString(2, user.getLastname());
            prepStatement.setString(3, user.getUsername());
            prepStatement.setString(4, user.getPassword());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {

        ResultSet result = null;

        if (!user.getUsername().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Config.USERS_TABLE + " WHERE " + Config.USERS_USERNAME +
                    "=?" + " AND " + Config.USERS_PASSWORD + "=?";

            try {
                PreparedStatement prepStatement = getDbConnection().prepareStatement(query);
                prepStatement.setString(1, user.getUsername());
                prepStatement.setString(2, user.getPassword());
                result = prepStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Please enter a valid username/password");
        }

        return result;
    }

    public void addTask(Task task) {

        String insert = "INSERT INTO " + Config.TASKS_TABLE + "(" + Config.TASKS_TASK + "," +
                Config.TASKS_DATE + "," + Config.TASKS_DESCRIPTION + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement prepStatement = getDbConnection().prepareStatement(insert);
            //aici trebuie luat id user-ului care e logat
            prepStatement.setString(1, task.getTask());
            prepStatement.setString(2, task.getDateCreated());
            prepStatement.setString(3, task.getDescription());

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet showTasks(Task task) {
        ResultSet result = null;

        String query = "SELECT * FROM " + Config.TASKS_TABLE;

        try {
            PreparedStatement prepStatement = getDbConnection().prepareStatement(query);
            prepStatement.setString(1, task.getTask());
            prepStatement.setString(2, task.getDateCreated());
            prepStatement.setString(2, task.getDescription());
            result = prepStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return result;
    }


}

    /*public TableView queryToTable() throws SQLException, ClassNotFoundException {
        TableView result = new TableView();
        ObservableList data = FXCollections.observableArrayList();
        DbHandler databaseHandler = new DbHandler();

        //SQL FOR SELECTING ALL TASKS
        String query= "SELECT * FROM " + Config.TASKS_TABLE;
        //ResultSet
        ResultSet rs = databaseHandler.getDbConnection().createStatement().executeQuery(query);


            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                result.getColumns().addAll(col);
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++)
                    row.add(rs.getString(i));
                data.add(row);
            }

        return result;
    }*/



