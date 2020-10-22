package sample.Database;

public class Config {

    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbPass = "password";
    protected String dbName = "todo";

    //Table names
    public static final String USERS_TABLE="users";
    public static final String TASKS_TABLE="tasks";

    //Column names
    //USERS
    public static final String USERS_ID="userid";
    public static final String USERS_FIRSTNAME="firstname";
    public static final String USERS_LASTNAME="lastname";
    public static final String USERS_USERNAME="username";
    public static final String USERS_PASSWORD="password";
    //TASKS
    public static final String TASKS_ID="taskid";
    public static final String TASKS_DATE="datecreated";
    public static final String TASKS_DESCRIPTION="description";


}
