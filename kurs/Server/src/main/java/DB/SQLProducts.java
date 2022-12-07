package DB;

import StorOrg.Products;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class SQLProducts implements IProduct{
    private static SQLProducts instance;
    private Connect dbConnection;

    private SQLProducts() throws SQLException, ClassNotFoundException {
        dbConnection = Connect.getInstance();
    }

    public static synchronized SQLProducts getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLProducts();
        }
        return instance;
    }

    public boolean insert(Products obj) {
        String proc = "{call insert_storage(?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getStorage());
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

    public ArrayList<Products> get() {
        String str = "select typeName, storages.`type` from products\n" +
                "    join storages on storages.idstorage = products.idstorage;";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Products> prodList = new ArrayList<>();
        for (String[] items: result){
            Products products = new Products();
            products.setTypeProd(items[0]);
            products.setStorage(items[1]);
            prodList.add(products);
        }
        return prodList;
    }

    public ArrayList<Products> find(Products c) {
        String str = "select typeName, storages.`type` from products\n" +
                "    join storages on storages.idstorage = products.idstorage where storages.`type` = " +
                "\"" + c.getStorage() + "\";";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Products> prodList = new ArrayList<>();
        for (String[] items: result){
            Products products = new Products();
            products.setTypeProd(items[0]);
            products.setStorage(items[1]);
            prodList.add(products);
        }
        return prodList;
    }

    public boolean insertProducts(Products obj) {
        String proc = "{call insert_prod(?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setString(1, obj.getTypeProd());
            callableStatement.setString(2, obj.getStorage());
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
    public boolean registration(Products obj) {
        String proc = "{call insert_ttns(?,?,?,?,?,?)}";
        try (CallableStatement callableStatement = Connect.dbConnection.prepareCall(proc)) {
            callableStatement.setInt(1, obj.getIdclient());
            callableStatement.setString(2, obj.getTypeProd());
            callableStatement.setString(3, obj.getStorage());
            callableStatement.setString(4, obj.getName());
            callableStatement.setInt(5, obj.getBalance());
            callableStatement.setInt(6, obj.getV());
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
