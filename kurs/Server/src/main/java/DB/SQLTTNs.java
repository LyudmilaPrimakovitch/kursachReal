package DB;

import StorOrg.Clients;
import StorOrg.Products;
import StorOrg.TTNs;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
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


    @Override
    public ArrayList<TTNs> getByWorker(int c) {
        int key=0;
        String proc = "{call find_workerbyk(?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, c);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            key=callableStatement.getInt(2);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "select  products.typeName,idttn, productName, productSize, idclient" +
                " from ttns" +
                " join `products` on `products`.`idproduct` = ttns.idproduct" +
                " where ttns.idworker = " + key + " and ttns.`status`='заявка'";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<TTNs> infList = new ArrayList<>();
        for (String[] items: result){
            TTNs ttNs = new TTNs();
            ttNs.setId(Integer.parseInt(items[1]));
            ttNs.setTypeProduct(items[0]);
            ttNs.setProdName(items[2]);
            ttNs.setSize(Integer.parseInt(items[3]));
            ttNs.setIdClient(Integer.parseInt(items[4]));
            infList.add(ttNs);
        }
        return infList;
    }

    @Override
    public ArrayList<TTNs> getByClient(int c) {
        int key=0;
        String proc = "{call find_clientbyk(?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, c);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            key=callableStatement.getInt(2);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "select  products.typeName,idttn, productName, productSize, status" +
                " from ttns" +
                " join `products` on `products`.`idproduct` = ttns.idproduct" +
                " where ttns.idclient = " + key;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<TTNs> infList = new ArrayList<>();
        for (String[] items: result){
            TTNs ttNs = new TTNs();
            ttNs.setId(Integer.parseInt(items[1]));
            ttNs.setTypeProduct(items[0]);
            ttNs.setProdName(items[2]);
            ttNs.setSize(Integer.parseInt(items[3]));
            ttNs.setStatus(items[4]);
            infList.add(ttNs);
        }
        return infList;
    }

    @Override
    public ArrayList<TTNs> getTtns() {
        String str = "select  idttn, productName, productSize, status, shelfLifeProd" +
                " from ttns" ;
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<TTNs> infList = new ArrayList<>();
        for (String[] items: result){
            TTNs ttNs = new TTNs();
            ttNs.setId(Integer.parseInt(items[0]));
            ttNs.setProdName(items[1]);
            ttNs.setSize(Integer.parseInt(items[2]));
            ttNs.setStatus(items[3]);
            ttNs.setTime(items[4]);
            infList.add(ttNs);
        }
        return infList;
    }

    @Override
    public boolean bring(TTNs obj) {
        String proc = "{call bring_ttns(?,?,?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getWorkerid());
            callableStatement.setString(2, obj.getTime());
            callableStatement.setInt(3, obj.getId());
            callableStatement.setString(4, obj.getStatus());
            callableStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ошибка" +e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
