package DB;

import StorOrg.Role;
import StorOrg.Clients;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;
public class SQLClients  implements IClients{
    private static SQLClients instance;
    private Connect dbConnection;

    private SQLClients() throws SQLException, ClassNotFoundException {
        dbConnection = Connect.getInstance();
    }

    public static synchronized SQLClients getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLClients();
        }
        return instance;
    }

    @Override
    public ArrayList<Clients> findClient(Clients obj) {
        String str = "select `keys`.login, companyName, email, averageV, payments.payment" +
                " from clients" +
                " join `keys` on `keys`.`id_keys` = clients.id_keys" +
                " left join payments on payments.idpayment = clients.idpayment" +
                " where `keys`.login = \"" + obj.getLogin() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Clients> studList = new ArrayList<>();
        for (String[] items: result){
            Clients students = new Clients();
            students.setLogin(items[0]);
            students.setCompanyName(items[1]);
            students.setEmail(items[2]);
            students.setAverageV(items[3]);
            try {
                students.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
            studList.add(students);
        }
        return studList;
    }

    @Override
    public boolean deleteClient(Clients obj) {
        String proc = "{call delete_client(?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getLogin());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Clients> get() {
        String str = "select `keys`.login, companyName, email, averageV, payments.payment" +
                " from clients" +
                " join `keys` on `keys`.`id_keys` = clients.id_keys" +
                " left join payments on payments.idpayment = clients.idpayment;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Clients> infList = new ArrayList<>();
        for (String[] items: result){
            Clients clients = new Clients();
            clients.setLogin(items[0]);
            clients.setCompanyName(items[1]);
            clients.setEmail(items[2]);
            clients.setAverageV(items[3]);
            try {
                clients.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
            infList.add(clients);
        }
        return infList;
    }

    @Override
    public Clients getClient(Role r) {
        String str = "select `keys`.login, companyName, email, averageV, payments.payment" +
                " from clients" +
                " join `keys` on `keys`.`id_keys` = clients.id_keys" +
                " left join payments on payments.idpayment = clients.idpayment" +
                " where clients.id_keys = " + r.getId();
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        Clients clients = new Clients();
        for (String[] items: result){
            clients.setLogin(items[0]);
            clients.setCompanyName(items[1]);
            clients.setEmail(items[2]);
            clients.setAverageV(items[3]);
            try {
                clients.setPayment(Integer.parseInt(items[5]));
            } catch (NumberFormatException e) {
                System.out.println("null");
            }
        }
        return clients;
    }

    @Override
    public Role insert(Clients obj) {
        String proc = "{call insert_client(?,?,?,?,?)}";
        Role r = new Role();
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getCompanyName());
            callableStatement.setString(2, obj.getEmail());
            callableStatement.setString(3, obj.getLogin());
            callableStatement.setString(4, obj.getPassword());
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();
            r.setId(callableStatement.getInt(5));
            r.setRole("client");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
