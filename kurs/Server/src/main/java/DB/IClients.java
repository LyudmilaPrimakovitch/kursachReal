package DB;

import StorOrg.Products;
import StorOrg.Role;
import StorOrg.Clients;
import java.util.ArrayList;

public interface IClients {
    ArrayList<Clients> findClient(Clients obj);
    Role insert(Clients obj);
    boolean deleteClient(Clients obj);
    ArrayList<Clients> get();
    Clients getClient(Role r);
}
