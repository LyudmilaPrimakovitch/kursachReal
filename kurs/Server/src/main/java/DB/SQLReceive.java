package DB;

import StorOrg.Receive;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLReceive implements IReceive{
    private static SQLReceive instance;
    private Connect dbConnection;

    private SQLReceive() throws SQLException, ClassNotFoundException {
        dbConnection = Connect.getInstance();
    }

    public static synchronized SQLReceive getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new SQLReceive();
        }
        return instance;
    }

    public ArrayList<Receive> get() {
        String str = "select sum(payments.balance) as receive, storages.`type`\n" +
                "    from clients\n" +
                "    join `ttns` on `ttns`.idclient = clients.idclient\n" +
                "    join `products` on `products`.idproduct = `ttns`.idproduct\n" +
                "    join storages on `storages`.idstorage = products.idstorage\n" +
                "    join `payments` on `payments`.idpayment = clients.idpayment\n" +
                "    group by storages.`type`";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Receive> infList = new ArrayList<>();
        for (String[] items: result){
            Receive receive = new Receive();
            receive.setBalance(Integer.parseInt(items[0]));
            receive.setStorage(items[1]);
            infList.add(receive);
        }
        return infList;
    }

    public ArrayList<Receive> getChart() {
        String str = "select sum(payments.balance) as receive, storages.`storage`\n" +
                "    from clients\n" +
                "    join `ttns` on `ttns`.idclient = clients.idclient\n" +
                "    join `products` on `products`.idproduct = `ttns`.idproduct\n" +
                "    join storages on `storages`.idstorage = products.idstorage\n" +
                "    join `payments` on `payments`.idpayment = clients.idpayment\n" +
                "    group by storages.`storage`";
        ArrayList<String[]> result = dbConnection.getArrayResult(str);
        ArrayList<Receive> infList = new ArrayList<>();
        for (String[] items: result){
            Receive receive = new Receive();
            receive.setBalance(Integer.parseInt(items[0]));
            receive.setStorage(items[1]);
            infList.add(receive);
        }
        return infList;
    }
}
