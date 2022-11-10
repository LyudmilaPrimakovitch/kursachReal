package DB;

import java.sql.SQLException;

public abstract class AbstractFactory {
    public abstract SQLWorker getWorkers() throws SQLException, ClassNotFoundException;
    public abstract SQLAuthorization getRole() throws SQLException, ClassNotFoundException;
   public abstract SQLAdmin getAdmin() throws SQLException, ClassNotFoundException;
}
