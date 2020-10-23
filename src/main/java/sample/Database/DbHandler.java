package sample.Database;

import sample.model.User;

import java.sql.*;

public class DbHandler extends Config{

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException , SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

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
            String query= "SELECT * FROM " + Config.USERS_TABLE + " WHERE " + Config.USERS_USERNAME +
                    "=?" + " AND " + Config.USERS_PASSWORD + "=?";

            try {
                PreparedStatement prepStatement = getDbConnection().prepareStatement(query);
                prepStatement.setString(1,user.getUsername());
                prepStatement.setString(2,user.getPassword());
               result = prepStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }else {
            System.out.println("Please enter a valid username/password");
        }

        return result;
    }

}
