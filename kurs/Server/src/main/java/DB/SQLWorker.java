package DB;

import StorOrg.Role;
import StorOrg.Worker;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;

public class SQLWorker implements IWorker {
    private static SQLWorker instance;
    private Connect dbConnection;

    private SQLWorker() throws SQLException, ClassNotFoundException {
        dbConnection = Connect.getInstance();
    }

    public static synchronized SQLWorker getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLWorker();
        }
        return instance;
    }

    public void findWorker(Worker obj) {

    }

    public boolean insert(Worker obj) {
        String proc = "{call insert_worker(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getFirstname());
            callableStatement.setString(2, obj.getLastname());
            callableStatement.setString(3, obj.getCategory());
            callableStatement.setString(4, obj.getLogin());
            callableStatement.setString(5, obj.getPassword());
            callableStatement.setString(6, obj.getStorage());
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

    public boolean changeWorker(Worker obj) {
        String proc = "{call change_worker(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getLastlogin());
            callableStatement.setString(2, obj.getFirstname());
            callableStatement.setString(3, obj.getLastname());
            callableStatement.setString(4, obj.getCategory());
            callableStatement.setString(5, obj.getLogin());
            callableStatement.setString(6, obj.getPassword());
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

    public boolean deleteWorker(Worker obj) {
        String proc = "{call delete_worker(?)}";
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

    public ArrayList<Worker> getWorker(Role r) {
        String str = "select `keys`.login, firstname, lastname, category, storages.`type`" +
                " from workers" +
                " join `keys` on `keys`.id_keys = workers.id_keys" +
                " join storages on storages.idstorage = workers.idstorage" +
                " where `keys`.id_keys = " + r.getId() + ";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Worker> workerList = new ArrayList<>();
        for (String[] items: result){
            Worker worker = new Worker();
            worker.setLogin(items[0]);
            worker.setFirstname(items[1]);
            worker.setLastname(items[2]);
            worker.setCategory(items[3]);
            worker.setStorage(items[4]);
            workerList.add(worker);
        }
        return workerList;
    }

    public Role getIdByWorker(Role obj) {
        String proc = "{call get_idworker_bykeys(?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getId());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            obj.setId(callableStatement.getInt(2));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Role getIdByClients(Role obj) {
        String proc = "{call get_idkeys_byclients(?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getId());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            obj.setId(callableStatement.getInt(2));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
