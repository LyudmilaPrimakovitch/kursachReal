package DB;
 import StorOrg.Products;
 import StorOrg.Receive;
 import StorOrg.TTNs;

 import java.util.ArrayList;

public interface ITTN {
    ArrayList<TTNs> getByWorker(int c);
    ArrayList<TTNs> getByClient(int c);

    ArrayList<TTNs> getTtns();
    boolean bring(TTNs obj);
    }
