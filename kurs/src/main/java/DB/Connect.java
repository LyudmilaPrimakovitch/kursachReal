package DB;

import java.sql.*;
import java.util.ArrayList;

public class Connect {
    private static Connect instance;

    private String dbHost = "localhost";
    private String dbPort = "3306";
    private String dbUser = "root";
    private String dbPass = "lp3842978";
    private String dbName = "warehouse";

    ArrayList<String[]> masResult;

    public static Connection dbConnection;
    private Statement statement;
    private ResultSet resultSet;

    public Connect ()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        statement = dbConnection.createStatement();
    }

    public void execute(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connect getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    public ArrayList<String[]> getArrayResult(String str) {
        masResult = new ArrayList<String[]>();
        try {
            resultSet = statement.executeQuery(str);
            int count = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String[] arrayString = new String[count];
                for (int i = 1;  i <= count; i++)
                    arrayString[i - 1] = resultSet.getString(i);

                masResult.add(arrayString);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return masResult;
    }
}
