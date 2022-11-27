package DB;

import java.sql.SQLException;

public class SQLFactory extends AbstractFactory {
    public SQLWorker getWorkers() throws SQLException, ClassNotFoundException {
        return SQLWorker.getInstance();
    }

    public SQLAuthorization getRole() throws SQLException, ClassNotFoundException {
        return SQLAuthorization.getInstance();
    }

    public SQLAdmin getAdmin() throws SQLException, ClassNotFoundException {
        return SQLAdmin.getInstance();
    }
}
