package DB;

import StorOrg.Admin;
import StorOrg.Worker;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLAdmin implements IAdmin {
    private static SQLAdmin instance;
    private Connect dbConnection;

    private SQLAdmin() throws SQLException, ClassNotFoundException {
        dbConnection = Connect.getInstance();
    }

    public static synchronized SQLAdmin getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLAdmin();
        }
        return instance;
    }

    public ArrayList<Admin> get() {
        String str = "select `keys`.login, `keys`.`password` from admins" +
                " join `keys` on `keys`.id_keys = admins.id_keys;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Admin> infList = new ArrayList<>();
        for (String[] items: result){
            Admin admin = new Admin();
            admin.setLogin(items[0]);
            admin.setPassword(items[1]);
            infList.add(admin);
        }
        return infList;
    }

    public void change(Worker obj) {

    }
}

