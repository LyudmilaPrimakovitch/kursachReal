package DB;

import StorOrg.Role;
import StorOrg.Worker;

import java.util.ArrayList;

public interface IWorker {
    void findWorker(Worker obj);
    boolean insert(Worker obj);
    boolean changeWorker(Worker obj);
    ArrayList<Worker> getWorker(Role r);
    Role getIdByWorker(Role obj);
    Role getIdByClients(Role obj);
}
