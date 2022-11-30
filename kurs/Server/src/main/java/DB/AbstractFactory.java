package DB;

import java.sql.SQLException;

public abstract class AbstractFactory {
    public abstract SQLWorker getWorkers() throws SQLException, ClassNotFoundException;
    public abstract SQLClients getClients() throws SQLException, ClassNotFoundException;
    public abstract SQLAuthorization getRole() throws SQLException, ClassNotFoundException;
//    public abstract SQLCourses getCourse() throws SQLException, ClassNotFoundException;
//    public abstract SQLTimetable getTimetable() throws SQLException, ClassNotFoundException;
//    public abstract SQLGroups getGroups() throws SQLException, ClassNotFoundException;
    public abstract SQLAdmin getAdmin() throws SQLException, ClassNotFoundException;
//    public abstract SQLReceive getReceive() throws SQLException, ClassNotFoundException;
}
