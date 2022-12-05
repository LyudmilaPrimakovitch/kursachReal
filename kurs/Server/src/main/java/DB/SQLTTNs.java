package DB;

import StorOrg.TTNs;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLTTNs implements ITTN{
    private static SQLTTNs instance;
    private Connect dbConnection;

    private SQLTTNs() throws SQLException, ClassNotFoundException {
        dbConnection = Connect.getInstance();
    }

    public static synchronized SQLTTNs getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLTTNs();
        }
        return instance;
    }
}
